package Mahdi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewCartController {

    @FXML
    private TableView<PurchaseCattleInterfaceController.Cattle> cartTable;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, Integer> columnId;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, String> columnBreed;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, Integer> columnAge;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, Double> columnWeight;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, String> columnHealthStatus;

    @FXML
    private TableColumn<PurchaseCattleInterfaceController.Cattle, Double> columnPrice;

    private final ObservableList<PurchaseCattleInterfaceController.Cattle> cartItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnBreed.setCellValueFactory(new PropertyValueFactory<>("breed"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        columnHealthStatus.setCellValueFactory(new PropertyValueFactory<>("healthStatus"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Bind data to the table
        cartTable.setItems(cartItems);
    }

    public void addToCart(PurchaseCattleInterfaceController.Cattle cattle) {
        cartItems.add(cattle);
    }
}
