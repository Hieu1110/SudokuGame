module com.example.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.project2 to javafx.fxml;
    exports com.example.project2;

    exports com.example.project2.Controller.Login;
    opens com.example.project2.Controller.Login to javafx.fxml;

    exports com.example.project2.Controller.LuyenTap;
    opens com.example.project2.Controller.LuyenTap to javafx.fxml;

    exports com.example.project2.Controller.ThiDau;
    opens com.example.project2.Controller.ThiDau to javafx.fxml;

    exports com.example.project2.Controller.Server;
    opens com.example.project2.Controller.Server to javafx.fxml;

    exports com.example.project2.Controller.Client;
    opens com.example.project2.Controller.Client to javafx.fxml;




}