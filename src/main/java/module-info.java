module com.main.templates {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.templates to javafx.fxml;
    exports com.main.templates;
    exports com.main.templates.Controllers;
    opens com.main.templates.Controllers to javafx.fxml;
}