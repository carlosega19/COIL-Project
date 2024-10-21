package com.main.templates.UIControllers.LogIn;

import com.main.templates.Main;
import com.main.templates.UIControllers.LogIn.Backgrounds.RegisterBackground;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class RegisterController {

    @FXML
    private HBox mainPanel;

    @FXML
    private Canvas background;

    @FXML
    private Button registerBtn;

    @FXML
    private Label loginLbl;

    private RegisterBackground back;

    public void initialize() {
        back = new RegisterBackground(background, background.getGraphicsContext2D(), mainPanel);
        back.start();

        mainPanel.setTranslateY(-600);
        TranslateTransition showRegisterPane = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        showRegisterPane.setFromY(-1000);
        showRegisterPane.setToY(0);
        showRegisterPane.setInterpolator(Interpolator.EASE_OUT);
        showRegisterPane.play();

        // Responsive background
        mainPanel.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (back.isStarted()) {
                back.still((double) newVal, background.getHeight());
            } else back.enter();
        });
        mainPanel.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (back.isStarted()) {
                back.still(background.getWidth(), (double) newVal);
            } else back.enter();
        });

        //COMPONENTS
        registerBtn.setOnMouseEntered(event -> {
            registerBtn.setOpacity(0.5);
        });
        registerBtn.setOnMouseExited(event -> {
            registerBtn.setOpacity(1);
        });
        loginLbl.setOnMouseEntered(event -> {
            loginLbl.setOpacity(0.5);
        });
        loginLbl.setOnMouseExited(event -> {
            loginLbl.setOpacity(1);
        });
    }

    public void changeToLoginPage(MouseEvent e) throws IOException {
        back.exit();

        final double height = background.getHeight();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        transition.setFromY(0);
        transition.setToY(height+200);
        transition.setInterpolator(Interpolator.EASE_IN);
        transition.setOnFinished(event -> {
            Stage stage = (Stage) mainPanel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
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
