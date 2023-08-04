package com.example.snake;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private final double GAMELOOPSPEED=100;
    private Direction direction=Direction.right;
    //food
    private  Position food=new Position();
    private boolean isAppeEaten=false;
    //snake
    private  LinkedList<Position> snake=new LinkedList<>();
    //field
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
        //get key input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                switch (code){
                    case RIGHT:
                        if(direction!=Direction.left){
                            direction=Direction.right;
                        }
                        break;
                    case LEFT:
                        if(direction!=Direction.right){
                            direction=Direction.left;
                        }
                        break;
                    case UP:
                        if(direction!=Direction.bottom){
                            direction=Direction.top;
                        }
                        break;
                    case DOWN:
                        if(direction!=Direction.top){
                            direction=Direction.bottom;
                        }
                        break;
                }
            }
        });

        startGame();
        Timeline timeline=new Timeline(new KeyFrame(Duration.millis(GAMELOOPSPEED), e->GameLoop(graphicsContext)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();



    }
    private void GameLoop(GraphicsContext gc){

        //draw field
        drawField(gc);

        //checks if the apple is eaten
        isAppeEaten=snake.getFirst().equals(food);

        //change snake position snake
        switch (direction){
            case left:
                snake.addFirst(new Position(snake.getFirst().horizontal-1,snake.getFirst().vertical));
                break;
            case right:
                snake.addFirst(new Position(snake.getFirst().horizontal+1,snake.getFirst().vertical));
                break;
            case top :
                snake.addFirst(new Position(snake.getFirst().horizontal,snake.getFirst().vertical-1));
                break;
            case bottom:
                snake.addFirst(new Position(snake.getFirst().horizontal,snake.getFirst().vertical+1));
                break;
        }
        //if snake has eaten an apple make it longer
        if(!isAppeEaten) {
            field[snake.getLast().horizontal][snake.getLast().vertical] = FieldState.empty;
            snake.removeLast();
        }
        addSnake();

        //check if the game ends

        // add apple if it's eaten
        if(isAppeEaten) {
            isAppeEaten = false;
            addApple();
        }


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
    private void startGame(){
        direction=Direction.right;
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