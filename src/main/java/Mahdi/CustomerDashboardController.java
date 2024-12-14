package Mahdi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class CustomerDashboardController {

    @FXML
    private BorderPane customerDashboardBorderPane;
    @FXML
    private Label errorLabel;

    @FXML
    void purchaseCattleOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/PurchaseCattleInterface.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void viewCartOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/ViewCart.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void makePaymentOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/MakePayment.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void trackOrderOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/TrackOrder.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void orderHistoryOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/OrderHistory.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void feedbackOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/Feedback.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void customerSupportOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/CustomerSupport.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    void accountInformationOnAction(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/AccountInformation.fxml"));
        try {
            customerDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

}
