package com.codegym.games.minesweeper;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE = "\\uD83D\\uDCA3";
    private static final String FLAG = "\\uD83D\\uDEA9";
    private int countFlags;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
        isGameStopped = false;
    }

    private void gameOver(){
        showMessageDialog(Color.WHITE, "GAME OVER", Color.BLACK, 50);
        isGameStopped = true;
    }
    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[j][i].isMine) {
                    for (GameObject gameObject : getNeighbors(gameField[j][i])) {
                        if (!gameObject.isMine) {
                            gameObject.countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }

    private void markTile(int x, int y) {
        GameObject thisField = gameField[y][x];

        if (isGameStopped) return;

        if (thisField.isOpen) return;

        if (!thisField.isFlag && countFlags == 0) {

        } else {
            if (thisField.isFlag){
              thisField.isFlag = false;
              countFlags++;
              setCellValue(x, y, "");
              setCellColor(x, y, Color.ORANGE);
            } else {
                thisField.isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
            }
        }
    }

    private void openTile(int x, int y){
        GameObject thisField = gameField[y][x];

        if (thisField.isOpen) return;

        if (thisField.isMine){
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        } else {
            thisField.isOpen = true;

            if (thisField.countMineNeighbors != 0){
                setCellNumber(x, y, thisField.countMineNeighbors);
            } else {
                setCellValue(x, y, "");
                for (GameObject gameObject : getNeighbors(thisField)){
                    openTile(gameObject.x, gameObject.y);
                }
            }
        }
        thisField.isOpen = true;
        setCellColor(x, y, Color.GREEN);
    }
}
