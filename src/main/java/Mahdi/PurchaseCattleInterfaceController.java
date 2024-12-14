package Mahdi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseCattleInterfaceController {

    @FXML
    private TableView<Cattle> cattleTable;

    @FXML
    private TableColumn<Cattle, Integer> columnId;

    @FXML
    private TableColumn<Cattle, String> columnBreed;

    @FXML
    private TableColumn<Cattle, Integer> columnAge;

    @FXML
    private TableColumn<Cattle, Double> columnWeight;

    @FXML
    private TableColumn<Cattle, String> columnHealthStatus;

    @FXML
    private TableColumn<Cattle, Double> colPrice;

    @FXML
    private Button addToCartButton;

    // Shared data for the cart
    public static ObservableList<Cattle> cartItems = FXCollections.observableArrayList();

    private final ObservableList<Cattle> cattleList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        columnHealthStatus.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Load initial data
        loadCattleData();

        // Bind data to the table
        cattleTable.setItems(cattleList);
    }

    private void loadCattleData() {
        // Sample data
        cattleList.add(new Cattle(1, "Angus", 2, 600.5, "Healthy", 1200.0));
        cattleList.add(new Cattle(2, "Holstein", 3, 700.0, "Healthy", 1500.0));
        cattleList.add(new Cattle(3, "Hereford", 1, 550.0, "Healthy", 1000.0));
    }

    @FXML
    private void addToCart() {
        Cattle selectedCattle = cattleTable.getSelectionModel().getSelectedItem();
        if (selectedCattle != null) {
            try {
                // Load the viewCart.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCart.fxml"));
                Parent root = loader.load();

                // Get the controller for the cart scene
                ViewCartController viewCartController = loader.getController();

                // Add the selected cattle to the cart
                viewCartController.addToCart(selectedCattle);

                // Switch to the cart scene
                Stage stage = (Stage) addToCartButton.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please select a cattle to add to the cart.");
        }
    }



    @FXML
    private void viewCart() {
        try {
            // Load the ViewCart FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCart.fxml"));
            Parent root = loader.load();

            // Set the scene for the new stage
            Stage stage = (Stage) addToCartButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Unable to load the cart view.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Cattle model class
    public static class Cattle {
        private final int id;
        private final String breed;
        private final int age;
        private final double weight;
        private final String healthStatus;
        private final double price;

        public Cattle(int id, String breed, int age, double weight, String healthStatus, double price) {
            this.id = id;
            this.breed = breed;
            this.age = age;
            this.weight = weight;
            this.healthStatus = healthStatus;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getBreed() {
            return breed;
        }

        public int getAge() {
            return age;
        }

        public double getWeight() {
            return weight;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public double getPrice() {
            return price;
        }
    }
}
