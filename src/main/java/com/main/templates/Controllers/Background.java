package com.main.templates.Controllers;

import com.main.templates.HelloApplication;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Background {
    private HBox mainPanel;
    private Canvas background;
    private GraphicsContext gc;
    private boolean started = false;
    private boolean inLogin;

    public Background(Canvas background, GraphicsContext gc, HBox mainPanel, boolean inLogin) {
        this.background = background;
        this.gc = gc;
        this.mainPanel = mainPanel;
        this.inLogin = inLogin;

        background.widthProperty().bind(mainPanel.widthProperty());
        background.heightProperty().bind(mainPanel.heightProperty());
    }

    private double speedCalc(double x) {
        return Math.log(x+1)*5;
    }

    private void loginBack(double width, double height) {
        if (!started) {
            AnimationTimer timer = new AnimationTimer() {
                final double width = background.getWidth();
                final double height = background.getHeight();

                double y1 = height+500;
                double y2 = -60;
                double i = 1;

                @Override
                public void handle(long now) {

                    drawLoginBack(y1, y2, width, height);

                    if (y1 >= height) {
                        y1 -= speedCalc(i);
                    }
                    if (y2 <= height) {
                        y2 += speedCalc(i);
                    }

                    i += 0.001;

                    if (y1 < height && y2 > height) {
                        this.stop();
                        started = true;
                    }
                }
            };
            timer.start();
        }
        else {
            drawLoginBack(height, height, width, height);
        }
    }

    private void drawLoginBack(double y1, double y2 ,double width, double height) {
        gc.clearRect(0, 0, width, height);

        // Background
        gc.setFill(Color.web("#181848"));
        gc.fillRect(0, 0, width, height);

        // Bottom left
        gc.setFill(Color.web("#B0C4DE"));
        gc.beginPath();
        gc.moveTo(0, height);
        gc.lineTo(0, y1 * 0.7);
        gc.lineTo(width * 0.3, height);
        gc.closePath();
        gc.fill();

        // Top right
        gc.beginPath();
        gc.moveTo(width, 0);
        gc.lineTo(width, y2 * 0.3);
        gc.lineTo(width * 0.7, 0);
        gc.closePath();
        gc.fill();

        // Lines
        gc.setStroke(Color.web("#FFA500"));
        gc.setLineWidth(2);

        // Bottom left
        gc.strokeLine(0, y1*0.68, width * 0.32+5, height+5);

        // Top right
        gc.strokeLine(width * 0.68-5, -5, width, y2 * 0.32);
    }

    private void drawRegisterBack(double y1, double y2 ,double width, double height) {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.web("#181848"));
        gc.fillRect(0, 0, width, height);

        // Bottom right
        gc.setFill(Color.web("#B0C4DE"));
        gc.beginPath();
        gc.moveTo(width, height);
        gc.lineTo(width, y1 * 0.7);
        gc.lineTo(width * 0.7, height);
        gc.closePath();
        gc.fill();

        // Top left
        gc.beginPath();
        gc.moveTo(0, 0);
        gc.lineTo(0, y2 * 0.3);
        gc.lineTo(width * 0.3, 0);
        gc.closePath();
        gc.fill();

        // Lines
        gc.setStroke(Color.web("#FFA500"));
        gc.setLineWidth(2);

        // Bottom right
        gc.strokeLine(width, y1 * 0.68, width * 0.68-5, height+5);

        // Top left
        gc.strokeLine(width * 0.32, -5, -5, y2 * 0.32);
    }

    private void registerBack(double width, double height) {
        if (!started) {
            AnimationTimer timer = new AnimationTimer() {
                double speed = 2;
                final double width = background.getWidth();
                final double height = background.getHeight();

                double y1 = height+500;
                double y2 = -60;
                double i = 1;

                @Override
                public void handle(long now) {

                    drawRegisterBack(y1, y2, width, height);

                    if (y1 >= height) {
                        y1 -= speedCalc(i);
                    }
                    if (y2 <= height) {
                        y2 += speedCalc(i);
                    }

                    i += 0.01;

                    if (y1 < height && y2 > height) {
                        this.stop();
                        started = true;
                    }
                }
            };
            timer.start();
        }
        else {
            drawRegisterBack(height, height, width, height);
        }
    }

    public void initBackground(double width, double height) {
        if (inLogin) loginBack(width, height);
        else registerBack(width, height);
    }

    public void changeToRegister() {
        AnimationTimer timer = new AnimationTimer() {
            final double width = background.getWidth();
            final double height = background.getHeight();

            double y1 = height;
            double y2 = height;
            double i = 1;

            @Override
            public void handle(long now) {
                drawLoginBack(y1, y2, width, height);
                if (y1 >= height) {
                    y1 += speedCalc(i);
                }
                if (y2 <= height) {
                    y2 -= speedCalc(i);
                }

                i+=0.01;

                if (y1 > height+100 && y2 < -100) {
                    this.stop();
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
                    transition.setFromY(0);
                    transition.setToY(height+200);
                    transition.setInterpolator(Interpolator.EASE_IN);
                    transition.setOnFinished(event -> {
                        Stage stage = (Stage) mainPanel.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 750, 550);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setTitle("Arqui-Tech | Register");
                        stage.setScene(scene);
                        stage.show();
                    });
                    transition.play();
                }
            }
        };
        timer.start();
    }

    public void changeToLogin() {
        AnimationTimer timer = new AnimationTimer() {
            final double width = background.getWidth();
            final double height = background.getHeight();

            double y1 = height;
            double y2 = height;
            double i = 1;

            @Override
            public void handle(long now) {
                drawRegisterBack(y1, y2, width, height);

                if (y1 >= height) {
                    y1 += speedCalc(i);
                }
                if (y2 <= height) {
                    y2 -= speedCalc(i);
                }

                i+=0.01;

                if (y1 > height+100 && y2 < -100) {
                    this.stop();
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
                    transition.setFromY(0);
                    transition.setToY(height+200);
                    transition.setInterpolator(Interpolator.EASE_IN);
                    transition.setOnFinished(event -> {
                        Stage stage = (Stage) mainPanel.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(fxmlLoader.load(), 750, 550);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.setTitle("Arqui-Tech | Register");
                        stage.setScene(scene);
                        stage.show();
                    });
                    transition.play();
                }
            }
        };
        timer.start();
    }

    public void toggleView() throws IOException {
         if (inLogin) {
             changeToRegister();
             inLogin = false;
         }
         else {
             changeToLogin();
             inLogin = true;
         }
    }
}