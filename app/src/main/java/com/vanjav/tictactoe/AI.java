package com.vanjav.tictactoe;
import java.util.Random;

/**
 * Created by vveselin on 29/09/2016.
 */

public class AI {
    private String computer;
    private String[][] grid;
    AI (String s, GameBoard gameBoard) {
        computer = s;
        grid = gameBoard.getGrid();
    }

    public int[] getMove () {
        int x = 0, y = 0;
        Random r = new Random();

        do {
            x = r.nextInt(3);
            y = r.nextInt(3);
        } while (grid[x][y] != "");

        int[] a = new int[2];
        a[0] = x;
        a[1] = y;

        return a;
    }
}
