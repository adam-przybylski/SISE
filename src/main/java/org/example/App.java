package org.example;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");
        SquareCentricBoard board = Dao.readInitialState("input.txt");
        System.out.println(board.isGoal());


    }
}
