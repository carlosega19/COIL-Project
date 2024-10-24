package com.main.templates.UIControllers.LogIn;

import com.main.templates.Main;
import com.main.templates.UIControllers.LogIn.Backgrounds.LoginBackground;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {

    @FXML
    private HBox mainPanel;

    @FXML
    private Canvas background;

    @FXML
    private TextField emailF;

    @FXML
    private PasswordField passF;

    @FXML
    private Button loginBtn;

    @FXML
    private Label registerLbl;

    private LoginBackground back;

    public void initialize() {
        back = new LoginBackground(background, background.getGraphicsContext2D(), mainPanel);

        mainPanel.setTranslateY(600);
        TranslateTransition showLoginPane = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        showLoginPane.setFromY(600);
        showLoginPane.setToY(0);
        showLoginPane.setInterpolator(Interpolator.EASE_OUT);
        showLoginPane.play();

        mainPanel.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (!back.isActive()) {
                if (back.isStarted()) {
                    back.still((double) newVal, background.getHeight());
                } else back.start();
            }
        });
        mainPanel.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (!back.isActive()) {
                if (back.isStarted()) {
                    back.still(background.getWidth(), (double) newVal);
                } else back.start();
            }
        });


        // COMPONENTS
        loginBtn.setOnMouseEntered((e -> {
            loginBtn.setOpacity(0.5);
        }));
        loginBtn.setOnMouseExited((e -> {
            loginBtn.setOpacity(1);
        }));
        registerLbl.setOnMouseEntered((e -> {
            registerLbl.setOpacity(0.5);
        }));
        registerLbl.setOnMouseExited((e -> {
            registerLbl.setOpacity(1);
        }));
    }

    public void changeToRegisterPage(MouseEvent e) throws IOException {
        back.exit();


        final double height = background.getHeight();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        transition.setFromY(0);
        transition.setToY(height+200);
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setOnFinished(event -> {
            Stage stage = (Stage) mainPanel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), mainPanel.getWidth(), mainPanel.getHeight());
            } catch (IOException g) {
                throw new RuntimeException(g);
            }
            stage.setTitle("Arqui-Tech | Register");
            stage.setScene(scene);
            stage.show();
        });
        transition.play();

    }
}
