package com.example.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Snake extends Application {
    GameParameters parameters = GameParameters.GetGamePatametersInstace();


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Snake.class.getResource("snake-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), parameters.SnakeBodyBlockWigth*parameters.WidthOfMap, parameters.SnakeBodyBlockWigth*parameters.WidthOfMap);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}