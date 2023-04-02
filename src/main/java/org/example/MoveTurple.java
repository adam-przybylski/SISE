package org.example;

public class MoveTurple {
    public final Board board;
    public final Board.Move move;

    public MoveTurple(Board board, Board.Move move) {
        this.board = board;
        this.move = move;
    }
}
