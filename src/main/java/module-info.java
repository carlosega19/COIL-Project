module com.main.templates {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.templates to javafx.fxml;
    exports com.main.templates;
    exports com.main.templates.UIControllers.LogIn;
    opens com.main.templates.UIControllers.LogIn to javafx.fxml;
    exports com.main.templates.UIControllers.Home;
    opens com.main.templates.UIControllers.Home to javafx.fxml;
    exports com.main.templates.UIControllers.LogIn.Backgrounds;
    opens com.main.templates.UIControllers.LogIn.Backgrounds to javafx.fxml;
}