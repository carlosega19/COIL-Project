package com.main.templates.UIControllers.LogIn;

import com.main.templates.Main;
import com.main.templates.MathManagement.Vec2d;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
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
    private double gap = 15;


    public Background(Canvas background, GraphicsContext gc, HBox mainPanel, boolean inLogin) {
        this.background = background;
        this.gc = gc;
        this.mainPanel = mainPanel;
        this.inLogin = inLogin;

        background.widthProperty().bind(mainPanel.widthProperty());
        background.heightProperty().bind(mainPanel.heightProperty());
    }

    protected void applyProcessing() {
        ColorAdjust ca = new ColorAdjust();
        ca.setContrast(0.2);
        ca.setBrightness(0.25);
        GaussianBlur blur = new GaussianBlur(2);
        gc.applyEffect(ca);
        gc.applyEffect(blur);
    }

    private double speedCalc(double x) {
        return Math.log(x) * 3;
    }

    private void loginBack(double width, double height) {

        if (!started) {
            AnimationTimer timer = new AnimationTimer() {
                final double width = background.getWidth();
                final double height = background.getHeight();

                Vec2d p1 = new Vec2d(0, height * 0.68); // bottom left
                Vec2d p2 = new Vec2d((width * 0.32), height);

                Vec2d p3 = new Vec2d((width * 0.68), 0); // top right
                Vec2d p4 = new Vec2d(width, height * 0.32);

                Vec2d actual1 = new Vec2d(p1.x, p1.y);
                Vec2d actual2 = new Vec2d(p4.x, p4.y);
                double i = 1;

                @Override
                public void handle(long now) {
                    gc.clearRect(0, 0, width, height);

                    // Background
                    gc.setFill(Color.web("#181848"));
                    gc.fillRect(0, 0, width, height);

                    // Bottom left
                    gc.setFill(Color.web("#B0C4DE"));
                    gc.beginPath();
                    gc.moveTo(0, height); // Esquina inferior izquierda
                    gc.lineTo(p1.x, p1.y + gap); // Punto en la izquierda
                    gc.lineTo(actual1.x, p2.y + gap); // Punto en la parte inferior
                    gc.closePath();
                    gc.fill();

                    // Top right
                    gc.beginPath();
                    gc.moveTo(width, 0); // Esquina superior derecha
                    gc.lineTo(p4.x, p4.y - gap); // Punto en la derecha
                    gc.lineTo(actual2.x, p3.y - gap); // Punto en la parte superior
                    gc.closePath();
                    gc.fill();


                    // Lines
                    gc.setStroke(Color.web("#FFA500"));
                    gc.setLineWidth(2); // Grosor de la línea dorada
                    // Bottom left
                    gc.strokeLine(p1.x, p1.y, actual1.x, actual1.y);
                    // Top right
                    gc.strokeLine(p4.x, p4.y, actual2.x, actual2.y);


                    actual1.add(Vec2d.angleBetween(p1, p2).setMagnitude(speedCalc(i)));
                    actual2.add(Vec2d.angleBetween(p4, p3).setMagnitude(speedCalc(i)));

                    applyProcessing();

                    i += 0.01;
                    if (actual1.y >= height && actual2.y <= 0) {
                        this.stop();
                        started = true;
                    }
                }
            };
            timer.start();
        }
        else {

            Vec2d p1 = new Vec2d(0, height * 0.68); // bottom left
            Vec2d p2 = new Vec2d((width * 0.32), height);

            Vec2d p3 = new Vec2d((width * 0.68), 0); // top right
            Vec2d p4 = new Vec2d(width, height * 0.32);
            gc.clearRect(0, 0, width, height);

            // Background
            gc.setFill(Color.web("#181848"));
            gc.fillRect(0, 0, width, height);

            // Bottom left
            gc.setFill(Color.web("#B0C4DE"));
            gc.beginPath();
            gc.moveTo(0, height); // Esquina inferior izquierda
            gc.lineTo(0, (height * 0.68) + gap); // Punto en la izquierda
            gc.lineTo(p2.x, p2.y + gap); // Punto en la parte inferior
            gc.closePath();
            gc.fill();

            // Top right
            gc.beginPath();
            gc.moveTo(width, 0); // Esquina superior derecha
            gc.lineTo(width, height * 0.32 - gap); // Punto en la derecha
            gc.lineTo(p3.x, p3.y - gap); // Punto en la parte superior
            gc.closePath();
            gc.fill();

            // Lines
            gc.setStroke(Color.web("#FFA500"));
            gc.setLineWidth(2); // Grosor de la línea dorada


            // Bottom left
            gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
            // Top right
            gc.strokeLine(p4.x, p4.y, p3.x, p3.y);

            applyProcessing();


        }
    }

    private void registerBack(double width, double height) {
        if (!started) {
            AnimationTimer timer = new AnimationTimer() {
                final double width = background.getWidth();
                final double height = background.getHeight();

                double i = 1;

                Vec2d p1 = new Vec2d(width, height * 0.68); // bottom left
                Vec2d p2 = new Vec2d(width * 0.68, height);

                Vec2d p3 = new Vec2d(width * 0.32, 0); // top right
                Vec2d p4 = new Vec2d(0, height * 0.32);

                Vec2d actual1 = new Vec2d(p1.x, p1.y);
                Vec2d actual2 = new Vec2d(p4.x, p4.y);


                @Override
                public void handle(long now) {
                    gc.clearRect(0, 0, width, height);

                    // Background
                    gc.setFill(Color.web("#181848"));
                    gc.fillRect(0, 0, width, height);

                    // Bottom right
                    gc.setFill(Color.web("#B0C4DE"));
                    gc.beginPath();
                    gc.moveTo(width, height);
                    gc.lineTo(p1.x, p1.y + gap); // Punto en la izquierda
                    gc.lineTo(actual1.x, p2.y + gap); // Punto en la parte inferior
                    gc.closePath();
                    gc.fill();

                    // Top left
                    gc.beginPath();
                    gc.moveTo(0, 0);
                    gc.lineTo(p4.x, p4.y - gap); // Punto en la derecha
                    gc.lineTo(actual2.x, p3.y - gap); // Punto en la parte superior
                    gc.closePath();
                    gc.fill();

                    // Lines
                    gc.setStroke(Color.web("#FFA500"));
                    gc.setLineWidth(2); // Grosor de la línea dorada
                    // Bottom right
                    gc.strokeLine(p1.x, p1.y, actual1.x, actual1.y);
                    // Top left
                    gc.strokeLine(p4.x, p4.y, actual2.x, actual2.y);

                    actual1.add(Vec2d.angleBetween(p1, p2).setMagnitude(speedCalc(i)));
                    actual2.add(Vec2d.angleBetween(p4, p3).setMagnitude(speedCalc(i)));

                    i += 0.01;
                    applyProcessing();

                    if (actual1.y >= height && actual2.y <= 0) {
                        this.stop();
                        started = true;
                    }
                }
            };
            timer.start();
        }
        else {
            Vec2d p1 = new Vec2d(width, height * 0.68); // bottom left
            Vec2d p2 = new Vec2d(width * 0.68, height);

            Vec2d p3 = new Vec2d(width * 0.32, 0); // top right
            Vec2d p4 = new Vec2d(0, height * 0.32);
            gc.clearRect(0, 0, width, height);

            // Background
            gc.setFill(Color.web("#181848"));
            gc.fillRect(0, 0, width, height);

            // Bottom right
            gc.setFill(Color.web("#B0C4DE"));
            gc.beginPath();
            gc.moveTo(width, height);
            gc.lineTo(p1.x, p1.y + gap); // Punto en la izquierda
            gc.lineTo(p2.x, p2.y + gap); // Punto en la parte inferior
            gc.closePath();
            gc.fill();

            // Top left
            gc.beginPath();
            gc.moveTo(0, 0);
            gc.lineTo(p4.x, p4.y - gap); // Punto en la derecha
            gc.lineTo(p3.x, p3.y - gap); // Punto en la parte superior
            gc.closePath();
            gc.fill();

            // Lines
            gc.setStroke(Color.web("#FFA500"));
            gc.setLineWidth(2); // Grosor de la línea dorada

            // Bottom right
            gc.strokeLine(p1.x, p1.y, p2.x, p2.y);

            // Top left
            gc.strokeLine(p4.x, p4.y, p3.x, p3.y);
            applyProcessing();

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

            Vec2d p1 = new Vec2d(0, height * 0.68); // bottom left
            Vec2d p2 = new Vec2d(width * 0.32, height);

            Vec2d p3 = new Vec2d((width * 0.68), 0); // top right
            Vec2d p4 = new Vec2d(width, height * 0.32);

            Vec2d actual1 = new Vec2d(p2.x, p2.y);
            Vec2d actual2 = new Vec2d(p3.x, p3.y);

            double i = 1;


            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, width, height);

                gc.setFill(Color.web("#181848"));
                gc.fillRect(0, 0, width, height);

                // Bottom left
                gc.setFill(Color.web("#B0C4DE"));
                gc.beginPath();
                gc.moveTo(0, height); // Esquina inferior izquierda
                gc.lineTo(0, (height * 0.68) + gap); // Punto en la izquierda
                gc.lineTo(actual1.x, actual1.y + gap); // Punto en la parte inferior
                gc.closePath();
                gc.fill();




                // Top right
                gc.beginPath();
                gc.moveTo(width, 0); // Esquina superior derecha
                gc.lineTo(width, height * 0.32 - gap); // Punto en la derecha
                gc.lineTo(actual2.x, actual2.y - gap); // Punto en la parte superior
                gc.closePath();
                gc.fill();




                // Lines
                gc.setStroke(Color.web("#FFA500"));
                gc.setLineWidth(2); // Grosor de la línea dorada
                // Bottom left
                gc.strokeLine(p1.x, p1.y, actual1.x, actual1.y);
                // Top right
                gc.strokeLine(p4.x, p4.y, actual2.x, actual2.y);


                actual1.add(Vec2d.angleBetween(p2, p1).setMagnitude(speedCalc(i)));
                actual2.add(Vec2d.angleBetween(p3, p4).setMagnitude(speedCalc(i)));


                i += 0.01;
                applyProcessing();


                if (actual1.x <= 0 && actual2.x >= width) {
                    gc.clearRect(0, 0, width, height);
                    gc.setFill(Color.web("#181848"));
                    gc.fillRect(0, 0, width, height);
                    applyProcessing();

                    // elimina los puntos que quedan graficados

                    this.stop();
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
                    transition.setFromY(0);
                    transition.setToY(height+200);
                    transition.setInterpolator(Interpolator.EASE_IN);
                    transition.setOnFinished(event -> {
                        Stage stage = (Stage) mainPanel.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
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


            Vec2d p1 = new Vec2d(width, height * 0.68); // bottom left
            Vec2d p2 = new Vec2d(width * 0.68, height);

            Vec2d p3 = new Vec2d(width * 0.32, 0); // top right
            Vec2d p4 = new Vec2d(0, height * 0.32);

            Vec2d actual1 = new Vec2d(p2.x, p2.y);
            Vec2d actual2 = new Vec2d(p3.x, p3.y);

            double i = 1;

            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, width, height);

                // Background
                gc.setFill(Color.web("#181848"));
                gc.fillRect(0, 0, width, height);

                // Bottom right
                gc.setFill(Color.web("#B0C4DE"));
                gc.beginPath();
                gc.moveTo(width, height);
                gc.lineTo(p1.x, p1.y + gap); // Punto en la izquierda
                gc.lineTo(actual1.x, actual1.y + gap); // Punto en la parte inferior
                gc.closePath();
                gc.fill();

                // Top left
                gc.beginPath();
                gc.moveTo(0, 0);
                gc.lineTo(p4.x, p4.y - gap); // Punto en la derecha
                gc.lineTo(actual2.x, actual2.y - gap); // Punto en la parte superior
                gc.closePath();
                gc.fill();

                // Lines
                gc.setStroke(Color.web("#FFA500"));
                gc.setLineWidth(2); // Grosor de la línea dorada

                // Bottom right
                gc.strokeLine(p1.x, p1.y, actual1.x, actual1.y);

                // Top left
                gc.strokeLine(p4.x, p4.y, actual2.x, actual2.y);


                actual1.add(Vec2d.angleBetween(p2, p1).setMagnitude(speedCalc(i)));
                actual2.add(Vec2d.angleBetween(p3, p4).setMagnitude(speedCalc(i)));
                applyProcessing();

                i += 0.01;
                if (actual1.x >= width && actual2.x <= 0) {
                    gc.clearRect(0, 0, width, height);
                    gc.setFill(Color.web("#181848"));
                    gc.fillRect(0, 0, width, height);
                    applyProcessing();

                    // elimina los puntos que quedan graficados
                    this.stop();
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), mainPanel);
                    transition.setFromY(0);
                    transition.setToY(height+200);
                    transition.setInterpolator(Interpolator.EASE_IN);
                    transition.setOnFinished(event -> {
                        Stage stage = (Stage) mainPanel.getScene().getWindow();
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
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
