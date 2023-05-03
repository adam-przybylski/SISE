package org.example;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        SquareCentricBoard board = Dao.readInitialState("input.txt");
        System.out.println(board.isGoal());

        try {
            List<Board.Move> result = AStar.solveManhattan(board);
            System.out.println(result);
            List<Board.Move> result2 = BFS.solve(board);
            System.out.println(result2);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
