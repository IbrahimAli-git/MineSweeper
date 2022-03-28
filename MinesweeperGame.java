package com.codegym.games.minesweeper;

import com.codegym.engine.cell.*;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame(){
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                gameField[i][j] = new GameObject(j, i);
                setCellColor(j, i, Color.ORANGE);
            }
        }
    }

    public static void main(String[] args) {

    }
}
