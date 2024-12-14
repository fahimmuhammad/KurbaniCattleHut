module org.example.finalprojectnew {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.finalprojectnew to javafx.fxml;
    exports org.example.finalprojectnew;

    opens Fahim to javafx.fxml;
    exports Fahim;
}