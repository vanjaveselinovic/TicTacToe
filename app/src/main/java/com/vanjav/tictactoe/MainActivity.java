package com.vanjav.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GameBoard gameBoard;
    private int moveCount, x, y;
    private String player = "x", computer = "o";
    private boolean isOver = false;
    private AI ai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameBoard = new GameBoard();
        ai = new AI(computer, gameBoard);
    }

    public void radioButtonClick(View v) {
        RadioButton radioButton = (RadioButton) findViewById(v.getId());

        if (radioButton.getText().equals("x")) {
            player = "x";
            computer  = "o";
        }
        else if (radioButton.getText().equals("o")) {
            player = "o";
            computer = "x";
        }

        resetClick(null);
    }

    public void resetClick(View v) {
        if (isOver) {
            String winner = gameBoard.checkForWinner();
            String s = "The winner is " + winner + "!";
            if (winner.equals("")) s = "It's a tie!";
            new AlertDialog.Builder(this)
                    .setTitle("Game over")
                    .setMessage(s)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            reset();
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
        else reset();
    }

    private void reset() {
        gameBoard.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                setMove(i, j, "");
            }
        }
        moveCount = 0;
        isOver = false;

        if(computer.equals("x")) aiMove();
    }

    public void cellClick(View v) {
        TextView cell = (TextView) findViewById(v.getId());
        String content = (String) cell.getText();

        if(content.equals("") && !isOver) {
            switch (cell.getId()) {
                case R.id.cell00:
                    x = 0; y = 0; break;
                case R.id.cell01:
                    x = 0; y = 1; break;
                case R.id.cell02:
                    x = 0; y = 2; break;
                case R.id.cell10:
                    x = 1; y = 0; break;
                case R.id.cell11:
                    x = 1; y = 1; break;
                case R.id.cell12:
                    x = 1; y = 2; break;
                case R.id.cell20:
                    x = 2; y = 0; break;
                case R.id.cell21:
                    x = 2; y = 1; break;
                case R.id.cell22:
                    x = 2; y = 2; break;
            }

            setMove(x, y, player);
            moveCount++;
            isOver = checkForEnd();
            if (!isOver) aiMove();
            else {
                resetClick(null);
            }
        }
    }

    private boolean checkForEnd() {
        return !(gameBoard.checkForWinner().equals("")) || moveCount >= 9;
    }

    private void aiMove() {
        int[] move = ai.getMove();
        setMove(move[0], move[1], computer);
        moveCount++;
        isOver = checkForEnd();
        if (isOver) {
            resetClick(null);
        }
    }

    private void setMove(int x, int y, String s) {
        Resources res = getResources();
        int id = res.getIdentifier("cell"+x+y, "id", getApplicationContext().getPackageName());
        TextView cell = (TextView) findViewById(id);
        gameBoard.setMove(x, y, s);
        cell.setText(s);
    }
}
