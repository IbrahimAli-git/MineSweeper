package com.codegym.games.minesweeper;

import com.codegym.engine.cell.*;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;

    public MinesweeperGame(){

    }

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame(){
        int count = 0;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                int randomNumber = getRandomNumber(10);
                if (randomNumber == 9){
                    gameField[i][j] = new GameObject(j, i, true);
                    count++;
                } else {
                    gameField[i][j] = new GameObject(j, i, false);
                }
                setCellColor(j, i, Color.ORANGE);
            }
        }
        countMinesOnField = count;
    }

    public static void main(String[] args) {

    }
}

