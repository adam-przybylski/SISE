package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareCentricBoard implements Board, Cloneable {
    public SquareCentricBoard(int[][] board, String order) {
        this.board = board;
        this.rows = board.length;
        this.columns = board[0].length;
        this.order = order;
    }
    public SquareCentricBoard(int[][] board) {
        this.board = board;
        this.rows = board.length;
        this.columns = board[0].length;
        this.order = "UDLR";
    }

    protected int[][] board;
    protected final int rows;
    protected final int columns;
    protected String order;

    public void setOrder(String order) {
        this.order = order;
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public List<MoveTuple> getNeighbors() {
        List<MoveTuple> result = new ArrayList<>();

        for(Move move : Move.getInOrder(order)) {
            try {
                SquareCentricBoard newBoard = (SquareCentricBoard)this.clone();
                newBoard.move(move);
                result.add(new MoveTuple(newBoard, move));
            } catch (WrongMoveException e) {
                // do nothing
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    public List<MoveTuple> getNeighborsReversed() {
        List<MoveTuple> result = new ArrayList<>();

        for(Move move : Move.getReversed(order)) {
            try {
                SquareCentricBoard newBoard = (SquareCentricBoard)this.clone();
                newBoard.move(move);
                result.add(new MoveTuple(newBoard, move));
            } catch (WrongMoveException e) {
                // do nothing
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


    @Override
    public boolean isGoal() {
        if (board[rows - 1][columns - 1] != 0) {
            return false;
        }
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != i * columns + j + 1) {
                    return false;
                }
            }
        }
        for (int i = 0; i < columns - 1; i++) {
            if (board[rows - 1][i] != (rows - 1) * columns + i + 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void move(Move move) throws WrongMoveException {
        int zeroRow = 0;
        int zeroCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
        switch (move) {
            case UP -> {
                if (zeroRow == 0) {
                    throw new WrongMoveException();
                }
                board[zeroRow][zeroCol] = board[zeroRow - 1][zeroCol];
                board[zeroRow - 1][zeroCol] = 0;
            }
            case DOWN -> {
                if (zeroRow == rows - 1) {
                    throw new WrongMoveException();
                }
                board[zeroRow][zeroCol] = board[zeroRow + 1][zeroCol];
                board[zeroRow + 1][zeroCol] = 0;
            }
            case LEFT -> {
                if (zeroCol == 0) {
                    throw new WrongMoveException();
                }
                board[zeroRow][zeroCol] = board[zeroRow][zeroCol - 1];
                board[zeroRow][zeroCol - 1] = 0;
            }
            case RIGHT -> {
                if (zeroCol == columns - 1) {
                    throw new WrongMoveException();
                }
                board[zeroRow][zeroCol] = board[zeroRow][zeroCol + 1];
                board[zeroRow][zeroCol + 1] = 0;
            }
        }

    }




    @Override
    public Object clone() throws CloneNotSupportedException {
        int[][] newBoard = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return new SquareCentricBoard(newBoard, order);
    }

    @Override
    public boolean equals(Object obj) {
        return Arrays.deepEquals(board, ((SquareCentricBoard) obj).board);
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
    @Override
    public int[] getState() {
        int[] result = new int[rows * columns];
        int k = 0;
        for (int[] row : board) {
            for (int cell : row) {
                result[k++] = cell;
            }
        }
        return result;
    }
}
