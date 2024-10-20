package com.main.templates.Controllers;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

    private Background back;
    boolean started = false;

    public void initialize() {
        back = new Background(background, background.getGraphicsContext2D(), mainPanel, true);
        back.initBackground(background.getWidth(), background.getHeight());

        mainPanel.setTranslateY(600);
        TranslateTransition showLoginPane = new TranslateTransition(Duration.seconds(0.5), mainPanel);
        showLoginPane.setFromY(600);
        showLoginPane.setToY(0);
        showLoginPane.setInterpolator(Interpolator.EASE_OUT);
        showLoginPane.play();
        mainPanel.widthProperty().addListener((obs, oldVal, newVal) -> {
            back.initBackground(background.getWidth(), background.getHeight());
        });
        mainPanel.heightProperty().addListener((obs, oldVal, newVal) -> {
            back.initBackground(background.getWidth(), background.getHeight());
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

    public void changeToRegisterPage(MouseEvent event) throws IOException {
        back.toggleView();
    }
}
