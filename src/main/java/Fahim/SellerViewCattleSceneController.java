package Fahim;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class SellerViewCattleSceneController {

    @FXML
    private TableView<Cattle> cattleTable;

    @FXML
    private TableColumn<Cattle, String> idColumn;

    @FXML
    private TableColumn<Cattle, String> breedColumn;

    @FXML
    private TableColumn<Cattle, Integer> ageColumn;

    @FXML
    private TableColumn<Cattle, Double> weightColumn;

    @FXML
    private TableColumn<Cattle, String> healthStatusColumn;

    @FXML
    private TextField idField;

    @FXML
    private ComboBox<String> breedField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private ComboBox<String> healthStatusField;

    @FXML
    private Button addCattleButton;

    @FXML
    private Button removeCattleButton;

    private static final String FILE_PATH = "cattle_data.csv";

    @FXML
    public void initialize() {
        // Initialize the TableView columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        healthStatusColumn.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));

        // Load data from file and populate table
        cattleTable.setItems(loadCattleFromFile());

        // Set predefined options for breed and health status
        breedField.setItems(FXCollections.observableArrayList("Holstein", "Jersey", "Angus", "Guernsey"));
        healthStatusField.setItems(FXCollections.observableArrayList("Healthy", "Vaccinated", "Needs Attention"));

        // Add functionality to buttons
        addCattleButton.setOnAction(event -> addCattle());
        removeCattleButton.setOnAction(event -> removeSelectedCattle());
    }

    private ObservableList<Cattle> loadCattleFromFile() {
        ObservableList<Cattle> cattleList = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String id = parts[0];
                    String breed = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    double weight = Double.parseDouble(parts[3]);
                    String healthStatus = parts[4];
                    cattleList.add(new Cattle(id, breed, age, weight, healthStatus));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new one upon adding.");
        } catch (IOException e) {
            showAlert("File Error", "An error occurred while reading the file.");
        } catch (NumberFormatException e) {
            showAlert("Data Error", "Invalid data format in the file.");
        }

        return cattleList;
    }

    private void addCattle() {
        try {
            String id = idField.getText();
            String breed = breedField.getValue();
            int age = Integer.parseInt(ageField.getText());
            double weight = Double.parseDouble(weightField.getText());
            String healthStatus = healthStatusField.getValue();

            Cattle newCattle = new Cattle(id, breed, age, weight, healthStatus);
            cattleTable.getItems().add(newCattle);

            // Save to CSV file
            saveCattleToFile(newCattle);

            // Clear the input fields after adding
            idField.clear();
            breedField.setValue(null);
            ageField.clear();
            weightField.clear();
            healthStatusField.setValue(null);
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please ensure all fields are correctly filled.");
        } catch (IOException e) {
            showAlert("File Error", "An error occurred while saving to the file.");
        }
    }

    private void removeSelectedCattle() {
        Cattle selectedCattle = cattleTable.getSelectionModel().getSelectedItem();
        if (selectedCattle != null) {
            cattleTable.getItems().remove(selectedCattle);
            updateCattleFile();
        } else {
            showAlert("Selection Error", "No cattle selected to remove.");
        }
    }

    private void updateCattleFile() {
        ObservableList<Cattle> cattleList = cattleTable.getItems();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Cattle cattle : cattleList) {
                String line = String.format("%s,%s,%d,%.2f,%s",
                        cattle.getId(),
                        cattle.getBreed(),
                        cattle.getAge(),
                        cattle.getWeight(),
                        cattle.getHealthStatus());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("File Error", "An error occurred while updating the file.");
        }
    }

    private void saveCattleToFile(Cattle cattle) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%s,%s,%d,%.2f,%s",
                    cattle.getId(),
                    cattle.getBreed(),
                    cattle.getAge(),
                    cattle.getWeight(),
                    cattle.getHealthStatus());
            writer.write(line);
            writer.newLine();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
