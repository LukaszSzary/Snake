package com.example.snake;

public final class GameParameters {
    private static GameParameters instance;

    //Game parameters
    public final int SnakeBodyBlockWigth=20;
    public final int WidthOfMap=50;
    public final boolean GameOver=false;
    private GameParameters(){}
    public static GameParameters GetGamePatametersInstace(){
        if(instance==null){
          instance=new GameParameters();
        }
        return instance;
    }
}
