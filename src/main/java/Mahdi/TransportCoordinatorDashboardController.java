package Mahdi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TransportCoordinatorDashboardController {

    @FXML
    private BorderPane transportCoordinatorDashboardBorderPane;

    @FXML
    private Label errorLabel;


    @FXML
    public void transportRequestOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/transportRequest.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void vehicleAssignmentOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/VehicleAssignment.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void tasksOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/Tasks.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void taskSchedulingOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/TaskScheduling.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void transportMonitoringOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/TransportMonitoring.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void issueManagementOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/IssueManagement.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void deliveryConfirmationOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/DeliveryConfirmation.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

    @FXML
    public void performanceAnalysisOnAction(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mahdi/PerformanceAnalysis.fxml"));
        try {
            transportCoordinatorDashboardBorderPane.setCenter(fxmlLoader.load());
        } catch (Exception e) {
            //
        }
    }

}
