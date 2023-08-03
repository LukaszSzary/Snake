package com.example.snake;

import com.almasb.fxgl.app.GameController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javafx.util.Duration;
public class Snake extends Application {
    private final GameParameters parameters = GameParameters.GetGamePatametersInstace();
    private final int WIDTH=parameters.snakeBodyBlockWigth*parameters.widthOfMap;
    private  final int HEIGHT=parameters.snakeBodyBlockWigth*parameters.heightOfMap;
    private  final Position food=new Position();
    private  final LinkedList<Position> snake=new LinkedList<>();
    private final  FieldState[][] field=new FieldState[parameters.widthOfMap][parameters.heightOfMap];

    @Override
    public void start(Stage stage){
    //initialize window
        Group root =new Group();
        Canvas canvas=new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
        GraphicsContext graphicsContext= canvas.getGraphicsContext2D();

        generateField();
        drawField(graphicsContext);


    }
    private void updateGameState(GraphicsContext gc){
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0,0,WIDTH,HEIGHT);
        gc.setFill(Color.GRAY);
        gc.fillRect(0, parameters.snakeBodyBlockWigth*25, parameters.snakeBodyBlockWigth, parameters.snakeBodyBlockWigth);
    }
    private void drawField(GraphicsContext gc){
        for (int i=0 ; i< parameters.widthOfMap;i++) {
            for (int j=0 ; j< parameters.heightOfMap;j++) {
                switch (field[i][j]){
                    case empty:
                        gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(i*parameters.snakeBodyBlockWigth,j*parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth);
                        break;
                    case snake:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(i*parameters.snakeBodyBlockWigth,j*parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth);
                        break;
                    case food:
                        gc.setFill(Color.RED);
                        gc.fillRect(i*parameters.snakeBodyBlockWigth,j*parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth,parameters.snakeBodyBlockWigth);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private void generateField(){
        //clearing field
        for (FieldState[] row : field)
            Arrays.fill(row, FieldState.empty);
        //adding snake

        //getting list of empty fields

        //adding an apple
    }
    public static void main(String[] args) {
        launch();
    }
}