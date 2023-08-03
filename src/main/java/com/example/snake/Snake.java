package com.example.snake;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Snake extends Application {
    //Basically one unit
    public final int snakeBodyBlockWidth=20;
    public final int widthOfMap=50;
    public final int heightOfMap=50;

    private final int WIDTH=snakeBodyBlockWidth*widthOfMap;
    private final int HEIGHT=snakeBodyBlockWidth*heightOfMap;
    private  Position food=new Position();
    private  LinkedList<Position> snake=new LinkedList<>();
    private  FieldState[][] field=new FieldState[widthOfMap][heightOfMap];
    private  List<Position> possibleFoodPosition=new ArrayList<>();


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

        startGame();

        drawField(graphicsContext);


    }
   private void drawField(GraphicsContext gc){
        for (int i=0 ; i< widthOfMap;i++) {
            for (int j=0 ; j< heightOfMap;j++) {
                switch (field[i][j]){
                    case empty:
                        gc.setFill(Color.LIGHTGREEN);
                        gc.fillRect(i*snakeBodyBlockWidth,j*snakeBodyBlockWidth,snakeBodyBlockWidth,snakeBodyBlockWidth);
                        break;
                    case snake:
                        gc.setFill(Color.DARKGREEN);
                        gc.fillRect(i*snakeBodyBlockWidth,j*snakeBodyBlockWidth,snakeBodyBlockWidth,snakeBodyBlockWidth);
                        break;
                    case food:
                        gc.setFill(Color.RED);
                        gc.fillOval(i*snakeBodyBlockWidth,j*snakeBodyBlockWidth,snakeBodyBlockWidth,snakeBodyBlockWidth);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    private void generateField(){
        //clearing field
        /*
        for (FieldState[] row : field)
            Arrays.fill(row, FieldState.empty);
        */
        //adding snake

        //if snake head= apple
        addApple();


    }
    private void startGame(){
        //reset field
        for (FieldState[] row : field)
            Arrays.fill(row, FieldState.empty);
        //reset snake
        snake.clear();
        snake.add(new Position(1,25));
        snake.add(new Position(2,25));
        addSnake();
        //add apple
        addApple();
    }
    private void addSnake(){
        for(Position p:snake){
            field[p.horizontal][p.vertical]=FieldState.snake;
        }
    }
    private void addApple(){
        //getting list of empty fields
        possibleFoodPosition.clear();
        for(int i=0;i< widthOfMap;i++ ){
            for(int j=0;j< heightOfMap;j++) {
                if(field[i][j]==FieldState.empty){
                    possibleFoodPosition.add(new Position(i,j));
                }
            }
        }
        // gets int in range [0,possibleFoodPosition-1)
        int randomIndex = (int) Math.floor(Math.random() * (possibleFoodPosition.size()-1 ));
        food=possibleFoodPosition.get(randomIndex);

        //adding an apple
        field[food.horizontal][food.vertical]=FieldState.food;
    }
    public static void main(String[] args) {
        launch();
    }
}