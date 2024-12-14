package org.example.finalprojectnew;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.HashSet;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> userCategory;

    @FXML
    private Button loginButton;

    @FXML
    private Button addUserButton;

    private static final String USER_DATA_FILE = "user_data.dat";
    private HashSet<String> existingUsernames = new HashSet<>();

    @FXML
    public void initialize() {
        // Load existing users from the binary file
        loadUserData();

        // Set button actions
        loginButton.setOnAction(event -> handleLogin());
        addUserButton.setOnAction(event -> handleAddUser());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String category = userCategory.getValue();

        if (username.isEmpty() || password.isEmpty() || category == null) {
            showAlert("Login Error", "Please fill in all fields.");
            return;
        }

        if (isUserValid(username, password, category)) {
            switch (category) {
                case "Seller":
                    changeScene("/Fahim/SellerDashboardView.fxml");
                    break;
                case "Veterinarian":
                    changeScene("/Fahim/VeterinarianDashboardView.fxml");
                    break;
                default:
                    showAlert("Access Denied", "This category does not have a specific dashboard.");
                    break;
            }
        } else {
            showAlert("Login Error", "Invalid credentials. Please try again.");
        }
    }

    private void changeScene(String fxmlFile) {
        try {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Unable to load the dashboard scene.");
        }
    }

    private void handleAddUser() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New User");
        dialog.setHeaderText("Enter the details for the new user:");
        dialog.setContentText("Username:");

        dialog.showAndWait().ifPresent(username -> {
            if (existingUsernames.contains(username)) {
                showAlert("Add User Error", "Username already exists. Please choose another.");
                return;
            }

            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Add New User");
            passwordDialog.setHeaderText("Enter the password for the new user:");
            passwordDialog.setContentText("Password:");

            passwordDialog.showAndWait().ifPresent(password -> {
                String category = userCategory.getValue();
                if (category == null || category.isEmpty()) {
                    showAlert("Add User Error", "Please select a user category before adding.");
                    return;
                }

                // Add user to the system
                addUserToFile(username, password, category);
            });
        });
    }

    private void addUserToFile(String username, String password, String category) {
        boolean append = new File(USER_DATA_FILE).exists();
        try (ObjectOutputStream oos = new AppendableObjectOutputStream(new FileOutputStream(USER_DATA_FILE, true), append)) {
            User newUser = new User(username, password, category);
            oos.writeObject(newUser);
            existingUsernames.add(username);
            showAlert("Add User Success", "User successfully added.");
        } catch (IOException e) {
            showAlert("File Error", "Unable to save user data.");
        }
    }

    private boolean isUserValid(String username, String password, String category) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            while (true) {
                User user = (User) ois.readObject();
                if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getCategory().equals(category)) {
                    return true;
                }
            }
        } catch (EOFException e) {
            return false; // End of file reached, user not found
        } catch (IOException | ClassNotFoundException e) {
            showAlert("File Error", "Unable to read user data.");
            return false;
        }
    }

    private void loadUserData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            while (true) {
                User user = (User) ois.readObject();
                existingUsernames.add(user.getUsername());
            }
        } catch (EOFException e) {
            // End of file reached, no more users to load
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing user data found or error reading data.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Custom ObjectOutputStream to avoid header corruption
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        private final boolean append;

        public AppendableObjectOutputStream(OutputStream out, boolean append) throws IOException {
            super(out);
            this.append = append;
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            if (!append) {
                super.writeStreamHeader();
            } else {
                reset();
            }
        }
    }

    // User class for binary file storage
    static class User implements Serializable {
        private String username;
        private String password;
        private String category;

        public User(String username, String password, String category) {
            this.username = username;
            this.password = password;
            this.category = category;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getCategory() {
            return category;
        }
    }
}
