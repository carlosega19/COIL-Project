package com.main.templates.UIControllers.LogIn.Backgrounds;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;

public abstract class Background {
    protected GraphicsContext gc;
    protected Canvas background;
    protected HBox mainPanel;
    protected boolean active = false;
    protected boolean started = false;

    protected double i = 1;
    protected double width;
    protected double height;

    //FPS CONTROLLER
    protected long lastUpdate = 0;
    private final double fps = 120; // FPS deseados
    protected final double interval = 1_000_000_000 / fps;

    public Background(Canvas c, GraphicsContext gc, HBox mp) {
        mainPanel = mp;
        background = c;
        this.gc = gc;
        background.widthProperty().bind(mainPanel.widthProperty());
        background.heightProperty().bind(mainPanel.heightProperty());
    }

    protected double speedCalc(double epoch) {
        return Math.log(epoch) * 100;
    }

    protected abstract void applyProcessing();

    public abstract void enter();

    public abstract void exit();

    public abstract void still(double width, double height);


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
