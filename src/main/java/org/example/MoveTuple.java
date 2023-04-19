package org.example;

public class MoveTuple {
    public final Board board;
    public final Board.Move move;

    public MoveTuple(Board board, Board.Move move) {
        this.board = board;
        this.move = move;
    }
}
