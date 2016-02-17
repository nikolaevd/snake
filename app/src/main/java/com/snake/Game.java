
package com.snake;

import java.util.ArrayList;

/*
     0 - пустая ячейка
    -1 - ячейку занимает тело змеи
    -2 - ячейку занимает фрукт
*/

public class Game {

    private static final int sizeByX = 18;
    private static final int sizeByY = 30;

    private static final int DIR_NORTH = 1;
    private static final int DIR_EAST = 2;
    private static final int DIR_SOUTH = 3;
    private static final int DIR_WEST = 4;

    private int score = 0;
    private int isGrowing = 0;

    private int[][] field = new int[sizeByX][sizeByY];

    private ArrayList<Position> snake = new ArrayList<Position>();

    private int curDirection = Game.DIR_EAST;

    Game(){
        for(int x = 0; x < sizeByX; x++){
            for(int y = 0; y < sizeByY; y++){
                field[x][y] = 0;
            }
        }

        snake.add(new Position(2, 2));
        field[2][2] = -1;
        snake.add(new Position(2, 3));
        field[2][3] = -1;
        snake.add(new Position(2, 4));
        field[2][4] = -1;

        AddFruit();
    }

    private void AddFruit(){

        boolean bFlag = true;

        while(bFlag){
            int x = (int)(Math.random() * sizeByX);
            int y = (int)(Math.random() * sizeByY);
            if(field[x][y] == 0){
                bFlag = false;
                field[x][y] = -2;
            }
        }

    }

    public boolean nextMove(){

        switch(curDirection){
            case DIR_NORTH:{
                int nextX = snake.get(snake.size() - 1).getPosByX();
                int nextY = snake.get(snake.size() - 1).getPosByY() + 1;
                if(checkBorder(nextX, nextY)){
                    return checkNextPosition(nextX, nextY);
                }
                else{
                    return false;
                }
            }
            case DIR_EAST:{
                int nextX = snake.get(snake.size() - 1).getPosByX() + 1;
                int nextY = snake.get(snake.size() - 1).getPosByY();
                if(checkBorder(nextX, nextY)){
                    return checkNextPosition(nextX, nextY);
                }
                else{
                    return false;
                }
            }
            case DIR_SOUTH:{
                int nextX = snake.get(snake.size() - 1).getPosByX();
                int nextY = snake.get(snake.size() - 1).getPosByY() - 1;
                if(checkBorder(nextX, nextY)){
                    return checkNextPosition(nextX, nextY);
                }
                else{
                    return false;
                }
            }
            case DIR_WEST:{
                int nextX = snake.get(snake.size() - 1).getPosByX() - 1;
                int nextY = snake.get(snake.size() - 1).getPosByY();
                if(checkBorder(nextX, nextY)){
                    return checkNextPosition(nextX, nextY);
                }
                else{
                    return false;
                }
            }
        }

        return false;
    }

    private boolean checkBorder(int x, int y){
        if(x >= 0 && x <= sizeByX && y >= 0 && y <= sizeByY){
            return true;
        }
        return false;
    }

    private boolean checkNextPosition(int x, int y){
        switch (field[x][y]) {
            case 0:
                if(isGrowing > 0){
                    isGrowing--;
                }
                else{
                    field[snake.get(0).getPosByX()][snake.get(0).getPosByY()] = 0;
                    snake.remove(0);
                }
                snake.add(new Position(x, y));
                field[x][y] = -1;
                return true;
            case -1:
                return false;
            case -2:
                isGrowing += 2;
                score += 10;

                field[x][y] = 0;
                snake.add(new Position(x, y));
                field[x][y] = -1;

                AddFruit();
                return true;
            default:
                return false;
        }
    }

    public static int getSizeByX() {
        return sizeByX;
    }

    public static int getSizeByY() {
        return sizeByY;
    }

    public int[][] getField() {
        return field;
    }

    public ArrayList<Position> getSnake() {
        return snake;
    }

    public int getSnakeLength() {
        return snake.size();
    }

    public int getCurDirection() {
        return curDirection;
    }

    public void setCurDirection(int curDirection) {
        this.curDirection = curDirection;
    }

    public void clearScore(){
        this.score = 0;
    }

}
