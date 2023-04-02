package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareCentricBoardTest {

    @Test
    public void constructorTest() {
        int[][] testArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        SquareCentricBoard board = new SquareCentricBoard(testArray);
        Assertions.assertEquals(testArray, board.getBoard());
    }

    @Test
    public void getNeighborsTest() {
        int[][] testArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        SquareCentricBoard board = new SquareCentricBoard(testArray);
        int want = board.getNeighbors().size();
        Assertions.assertEquals(2, want);

        int[][] testArray2 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        SquareCentricBoard board2 = new SquareCentricBoard(testArray2);
        Assertions.assertEquals(3, board2.getNeighbors().size());

        int[][] testArray3 = new int[][]{{1, 2, 3}, {4, 0, 6}, {5, 7, 8}};
        SquareCentricBoard board3 = new SquareCentricBoard(testArray3);
        Assertions.assertEquals(4, board3.getNeighbors().size());
    }

    @Test
    public void isGoalTest() {
        int[][] testArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        SquareCentricBoard board = new SquareCentricBoard(testArray);
        Assertions.assertTrue(board.isGoal());
    }

    @Test
    public void moveTest() {
        int[][] testArray = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        SquareCentricBoard board = new SquareCentricBoard(testArray);
        int[][] testArray2 = new int[][]{{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        SquareCentricBoard board2 = new SquareCentricBoard(testArray2);
        try {
            board.move(Board.Move.UP);
        } catch (WrongMoveException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(board,board2);
    }
}
