package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        String strategy = args[0];
        String version = args[1];
        String path = args[2];
        String outputSolution = args[3];
        String outputStats = args[4];

        SquareCentricBoard board = Dao.readInitialState("C:\\Users\\jpazio\\Desktop\\SEMESTR4\\Sztuczna\\zad1\\ukladanki\\" + path);
        List<Board.Move> solution = null;
        try {
            switch (strategy) {
                case "bfs":
                    board.setOrder(version);
                    solution = BFS.solve(board, version);
                    break;
                case "dfs":
                    board.setOrder(version);
                    solution = DFS.solve(board, version);
                    break;
                case "astr":
                    if(version.equals("manh")) {
                        solution = AStar.solveManhattan(board);
                    }
                    else if(version.equals("hamm")) {
                        solution = AStar.solveHamming(board);
                    }
                    break;
                default:
                    System.out.println("Wrong strategy");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int resultLength = solution.size();
        StringBuilder result = new StringBuilder();
        for (Board.Move move : solution) {
            switch (move) {
                case UP:
                    result.append("U");
                    break;
                case DOWN:
                    result.append("D");
                    break;
                case LEFT:
                    result.append("L");
                    break;
                case RIGHT:
                    result.append("R");
                    break;
            }
        }
        Dao.writeSolution("C:\\Users\\jpazio\\Desktop\\SEMESTR4\\Sztuczna\\zad1\\ukladanki\\" + outputSolution , String.valueOf(resultLength), result.toString());
    }
}
