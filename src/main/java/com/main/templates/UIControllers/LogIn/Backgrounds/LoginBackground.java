package com.main.templates.UIControllers.LogIn.Backgrounds;

import com.main.templates.MathManagement.Vec2d;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class LoginBackground extends Background {
    double gap = 10;

    public LoginBackground(Canvas c, GraphicsContext gc, HBox mp) {
        super(c, gc, mp);
    }

    @Override
    protected void applyProcessing() {
        ColorAdjust ca = new ColorAdjust();
        ca.setContrast(0.2);
        ca.setBrightness(0.25);
        GaussianBlur blur = new GaussianBlur(2);
        gc.applyEffect(ca);
        gc.applyEffect(blur);

    }

    @Override
    public void enter() {
        final double width = background.getWidth();
        final double height = background.getHeight();

        if (!started) {
            AnimationTimer timer = new AnimationTimer() {

                Vec2d p1 = new Vec2d(0, height * 0.68); // bottom left
                Vec2d p2 = new Vec2d((width * 0.32), height);

                Vec2d p3 = new Vec2d((width * 0.68), 0); // top right
                Vec2d p4 = new Vec2d(width, height * 0.32);

                Vec2d actual1 = new Vec2d(p1.x, p1.y);
                Vec2d actual2 = new Vec2d(p4.x, p4.y);
                double i = 1;

                @Override
                public void handle(long now) {
                    System.out.println(background);
                    System.out.println("p1: " + p1.x + ", " + p1.y);
                    System.out.println("p2: " + p2.x + ", " + p2.y);
                    System.out.println("p3: " + p3.x + ", " + p3.y);
                    System.out.println("p4: " + p4.x + ", " + p4.y);
                    System.out.println("actual1: " + actual1.x + ", " + actual1.y);
                    System.out.println("actual2: " + actual2.x + ", " + actual2.y);
                    System.out.println(width + ", " + height);
                    System.out.println("------------\n");
                    gc.clearRect(0, 0, width, height);

                    // LoginBackground
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
    }

    @Override
    public void exit() {

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


                actual1.add(Vec2d.angleBetween(p2, p1).setMagnitude(i));
                actual2.add(Vec2d.angleBetween(p3, p4).setMagnitude(i));


                i += 0.01;
                applyProcessing();

                if (actual1.x <= 0 && actual2.x >= width) {
                    gc.clearRect(0, 0, width, height);
                    gc.setFill(Color.web("#181848"));
                    gc.fillRect(0, 0, width, height);
                    applyProcessing();

                    // elimina los puntos que quedan graficados
                    this.stop();
                }
            }
        };
        timer.start();
        active = false;
    }

    @Override
    public void still(double width, double height) {

        Vec2d p1 = new Vec2d(0, height * 0.68); // bottom left
        Vec2d p2 = new Vec2d((width * 0.32), height);

        Vec2d p3 = new Vec2d((width * 0.68), 0); // top right
        Vec2d p4 = new Vec2d(width, height * 0.32);
        gc.clearRect(0, 0, width, height);

        // LoginBackground
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
/*
Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16.67), event -> {
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

    // Ajusta el movimiento
    Vec2d movement1 = Vec2d.angleBetween(p2, p1).setMagnitude(i);
    Vec2d movement2 = Vec2d.angleBetween(p3, p4).setMagnitude(i);

    actual1 = actual1.add(movement1);
    actual2 = actual2.add(movement2);

    i += 0.005; // Ajusta el incremento de velocidad

    applyProcessing();

    if (actual1.x <= 0 && actual2.x >= width) {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.web("#181848"));
        gc.fillRect(0, 0, width, height);
        applyProcessing();

        // Detiene el timeline
        timeline.stop();
    }
}));

// Configura el timeline para que se repita indefinidamente
timeline.setCycleCount(Timeline.INDEFINITE);
timeline.play(); // Inicia la animación
*/