package com.example.snake;

public class Position {
    //row on grid
    public int horizontal=0;
    //column on grid
    public int vertical=0;
    public Position(int hor,int ver){
        this.horizontal=hor;
        this.vertical=ver;
    }
    public Position() {
    }
    public boolean equals(Position p){
        return this.horizontal == p.horizontal && this.vertical == p.vertical;
    }
}
