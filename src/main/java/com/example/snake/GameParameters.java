package com.example.snake;

public final class GameParameters {
    private static GameParameters instance;

    //Game parameters
    //Basically one unit
    public final int snakeBodyBlockWigth=20;
    public final int widthOfMap=50;
    public final int heightOfMap=50;
    public final boolean gameOver=false;
    private GameParameters(){}
    public static GameParameters GetGamePatametersInstace(){
        if(instance==null){
          instance=new GameParameters();
        }
        return instance;
    }
}
