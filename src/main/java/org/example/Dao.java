package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        return new SquareCentricBoard(board);
    }

    public static void writeSolution(String filename, int numOfMoves, String moves)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(numOfMoves);
        writer.newLine();
        writer.write(moves);
        writer.close();
    }
}
