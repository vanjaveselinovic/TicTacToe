package com.vanjav.tictactoe;

/**
 * Created by vveselin on 29/09/2016.
 */

public class GameBoard {
    private String[][] grid = new String[3][3];

    GameBoard() {
        clear();
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = "";
            }
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void setMove(int x, int y, String s) {
        grid[x][y] = s;
    }

    public String checkForWinner() {
        //rows and columns
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) return grid[i][0];
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) return grid[0][i];
        }

        //diagonals
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) return grid[0][0];
        if (grid[2][0] == grid[1][1] && grid[1][1] == grid[0][2]) return grid[2][0];

        return "";
    }
}
