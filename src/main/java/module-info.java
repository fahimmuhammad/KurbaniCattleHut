module com.example.kurbanicattlehut {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kurbanicattlehut to javafx.fxml;
    exports com.example.kurbanicattlehut;

    opens Mahdi to javafx.fxml;
    exports Mahdi;
}