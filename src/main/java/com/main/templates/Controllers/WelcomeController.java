package com.main.templates.Controllers;

import com.main.templates.HelloApplication;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ProgressBar progressBar;

    public void initialize() {
        welcomeLabel.setOpacity(0);
        nameLabel.setOpacity(0);

        FadeTransition showWelcome = new FadeTransition(Duration.seconds(2), welcomeLabel);
        showWelcome.setFromValue(0);
        showWelcome.setToValue(1);
        showWelcome.setCycleCount(1);
        showWelcome.setAutoReverse(true);

        FadeTransition showName = new FadeTransition(Duration.seconds(2), nameLabel);
        showName.setFromValue(0);
        showName.setToValue(1);
        showName.setCycleCount(1);
        showName.setAutoReverse(true);
        showName.setOnFinished(e-> {
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 750, 550);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            stage.setTitle("Arqui-Tech | Log In");
            stage.setScene(scene);
            stage.show();
        });

        showWelcome.setOnFinished(e->{
            showName.play();
        });
        showWelcome.play();

    }
}