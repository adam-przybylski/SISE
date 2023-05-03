package org.example;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        SquareCentricBoard board = Dao.readInitialState("C:\\Users\\jpazio\\Desktop\\SEMESTR4\\Sztuczna\\dodatkowe-uklady\\4x4_13_10839.txt");
        System.out.println(board.isGoal());

        try {
            List<Board.Move> result = AStar.solveManhattan(board);
            System.out.println("1: " + result);
            List<Board.Move> result2 = BFS.solve(board);
            System.out.println("2: " + result2);
            List<Board.Move> result3 = DFS.solve(board);
            System.out.println("3: " + result3);
            List<Board.Move> result4 = AStar.solveHamming(board);
            System.out.println("4: " + result4);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
