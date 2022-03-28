package com.codegym.games.minesweeper;

import com.codegym.engine.cell.*;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
    }

    public static void main(String[] args) {
        new MinesweeperGame().initialize();
    }
}
