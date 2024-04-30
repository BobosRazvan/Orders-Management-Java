module com.example.pt2024_30425_bobos_razvanandrei_assignment_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.pt2024_30425_bobos_razvanandrei_assignment_3 to javafx.fxml;
    opens com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Model to javafx.base;
    exports com.example.pt2024_30425_bobos_razvanandrei_assignment_3;
    exports com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers;
    opens com.example.pt2024_30425_bobos_razvanandrei_assignment_3.Controllers to javafx.fxml;
    exports com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess;
    opens com.example.pt2024_30425_bobos_razvanandrei_assignment_3.DataAccess to javafx.fxml;
}