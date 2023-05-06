package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = new File(App.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath());
        String jarPath = file.getParent();

        String strategy = args[0];
        String version = args[1];
        String path = args[2];
        String outputSolution = args[3];
        String outputStats = args[4];

        SquareCentricBoard board = Dao.readInitialState(jarPath + "\\" + path);
        Statistics solution = null;
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
                    if (version.equals("manh")) {
                        board.setOrder("UDLR");
                        solution = AStar.solveManhattan(board);
                    } else if (version.equals("hamm")) {
                        board.setOrder("UDLR");
                        solution = AStar.solveHamming(board);
                    }
                    break;
                default:
                    System.out.println("Wrong strategy");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if (solution != null) {
            int resultLength = solution.getResultLength();
            StringBuilder result = new StringBuilder();
            for (Board.Move move : solution.getMoves()) {
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
            Dao.writeSolution(jarPath + "\\" + outputSolution, String.valueOf(resultLength),
                    result.toString());
        } else {
            Dao.writeSolution(jarPath + "\\" + outputSolution, String.valueOf(-1));
        }

    }
}
