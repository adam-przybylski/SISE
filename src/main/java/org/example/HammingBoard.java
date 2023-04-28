package org.example;

public class HammingBoard extends SquareCentricBoard{
    public HammingBoard(int[][] board) {
        super(board);
    }
    public int getHeuristic() {
        int result = 0;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] != i * columns + j + 1) {
                    result++;
                }
            }
        }
        for (int i = 0; i < columns - 1; i++) {
            if (board[rows - 1][i] != (rows - 1) * columns + i + 1) {
                result++;
            }
        }
        return result;
    }
}
