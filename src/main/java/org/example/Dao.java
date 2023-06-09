package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Dao {

    public static SquareCentricBoard readInitialState(String filename)
            throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] board = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        sc.close();
        return new SquareCentricBoard(board, "");
    }

    public static void writeSolution(String filename, String numOfMoves, String moves)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(numOfMoves);
        writer.newLine();
        writer.write(moves);
        writer.close();
    }

    public static void writeSolution(String filename, String numOfMoves)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(numOfMoves);
        writer.close();
    }
    public static void writeStat(String filename, Statistics statistics) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(String.valueOf(statistics.getResultLength()));
        writer.newLine();
        writer.write(String.valueOf(statistics.getNodesVisited()));
        writer.newLine();
        writer.write(String.valueOf(statistics.getNodesProcessed()));
        writer.newLine();
        writer.write(String.valueOf(statistics.getMaxDepth()));
        writer.newLine();
        String time = new DecimalFormat("#.###").format(statistics.getTimeElapsed() / 1_000_000.0);
        writer.write(time);
        writer.close();
    }
}
