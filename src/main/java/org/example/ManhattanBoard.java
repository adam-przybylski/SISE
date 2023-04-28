package org.example;

public class ManhattanBoard extends SquareCentricBoard {
    public ManhattanBoard(int[][] board) {
        super(board);
    }

    public int getHeuristic() {
        int result = 0;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                int value = board[i][j];
                if (value != 0) {
                    int expectedRow = (value - 1) / columns;
                    int expectedColumn = (value - 1) % columns;
                    result += Math.abs(i - expectedRow) + Math.abs(j - expectedColumn);
                }
            }
        }
        for (int i = 0; i < columns - 1; i++) {
            int value = board[rows - 1][i];
            if (value != 0) {
                int expectedRow = (value - 1) / columns;
                int expectedColumn = (value - 1) % columns;
                result += Math.abs(rows - 1 - expectedRow) + Math.abs(i - expectedColumn);
            }
        }
        return result;
    }
}
