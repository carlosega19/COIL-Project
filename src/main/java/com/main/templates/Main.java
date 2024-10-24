package com.main.templates;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("javafx.animation.framerate", "60");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcome.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 750, 550);

        stage.setTitle("Arqui-Tech | Log In");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}