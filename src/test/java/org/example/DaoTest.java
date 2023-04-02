package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DaoTest {

    @Test
    public void writeTest() {
        try {
            Dao.writeSolution("test.txt", "1", "LRDU");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner sc;
        try {
            sc = new Scanner(new File("test.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String moves = sc.next();
        String movesString = sc.next();
        sc.close();
        Assertions.assertEquals("1", moves);
        Assertions.assertEquals("LRDU", movesString);

    }
}
