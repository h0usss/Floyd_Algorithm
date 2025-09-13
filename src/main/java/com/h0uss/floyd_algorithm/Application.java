package com.h0uss.floyd_algorithm;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("UI/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1284, 681);
        InputStream icoStream = getClass().getResourceAsStream("/images/icon.jpg");

        scene.getStylesheets().add(getClass().getResource("/com/h0uss/floyd_algorithm/UI/style.css").toExternalForm());
        stage.getIcons().add(new Image(icoStream));
        stage.setResizable(false);
        stage.setTitle("Floyd");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}