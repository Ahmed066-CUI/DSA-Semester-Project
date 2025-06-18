module com.example.dsaprojecttrial2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.json;


    opens com.example.dsaprojecttrial2 to javafx.fxml;
    exports com.example.dsaprojecttrial2;
}