package com.main.templates.Controllers;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    private Background back;
    private boolean started = false;

    public void initialize() {
        back = new Background(background, background.getGraphicsContext2D(), mainPanel, false);
        back.initBackground(background.getWidth(), background.getHeight());

        mainPanel.setTranslateY(-600);
        TranslateTransition showRegisterPane = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        showRegisterPane.setFromY(-1000);
        showRegisterPane.setToY(0);
        showRegisterPane.setInterpolator(Interpolator.EASE_OUT);
        showRegisterPane.play();
        mainPanel.widthProperty().addListener((obs, oldVal, newVal) -> {
            back.initBackground(background.getWidth(), background.getHeight());
        });
        mainPanel.heightProperty().addListener((obs, oldVal, newVal) -> {
            back.initBackground(background.getWidth(), background.getHeight());
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

    public void changeToLoginPage(MouseEvent mouseEvent) throws IOException {
        back.toggleView();
    }
}
