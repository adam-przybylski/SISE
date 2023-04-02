package org.example;

public class WrongMoveException extends Exception {
    public WrongMoveException() {
        super("Move is not allowed");
    }
}
