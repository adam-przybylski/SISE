package org.example;

import java.util.List;

public class SquareCentricBoard implements Board {
    public SquareCentricBoard(int[][] board) {
        this.board = board;
        this.rows = board.length;
        this.columns = board[0].length;
    }

    private int[][] board;
    private final int rows;
    private final int columns;

    @Override
    public List<MoveTurple> getNeighbors() {
        return null;
    }

    @Override
    public boolean isGoal() {
        if (board[rows - 1][columns - 1] != 0) {
            return false;
        }
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != i * columns + j + 1) {
                    return false;
                }
            }
        }
        for (int i = 0; i < columns-1; i++) {
            if (board[rows-1][i] != (rows-1) * columns + i + 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move(Move move) throws WrongMoveException {

    }
}
