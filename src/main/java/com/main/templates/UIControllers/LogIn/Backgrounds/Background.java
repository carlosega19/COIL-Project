package com.main.templates.UIControllers.LogIn.Backgrounds;

import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class Background {
    protected GraphicsContext gc;
    protected Canvas background;
    protected HBox mainPanel;
    protected boolean active = false;
    protected boolean started = false;

    protected double i = 1;
    protected Timeline timeline;
    protected double width;
    protected double height;

    public Background(Canvas c, HBox mp) {
        mainPanel = mp;
        background = c;
        gc = background.getGraphicsContext2D();
        background.widthProperty().bind(mainPanel.widthProperty());
        background.heightProperty().bind(mainPanel.heightProperty());

    }

    public Background(Canvas c, GraphicsContext gc, HBox mp) {
        mainPanel = mp;
        background = c;
        this.gc = gc;
        background.widthProperty().bind(mainPanel.widthProperty());
        background.heightProperty().bind(mainPanel.heightProperty());

    }

    protected double speedCalc(double epoch) {
        return Math.log(epoch) * 5;
    }

    protected abstract void applyProcessing();

    public abstract void enter();

    public abstract void exit();

    public abstract void still(double width, double height);

    public void changeTo(Background other) {
        exit();
        active = false;
        //other.start();
    }

    public void start() {
        active = true;
        enter();
        //still(background.getWidth(), background.getHeight());
    }

    // GETTERS AND SETTERS
    public boolean isActive() {
        return active;
    }
    public boolean isStarted() {
        return started;
    }
}
