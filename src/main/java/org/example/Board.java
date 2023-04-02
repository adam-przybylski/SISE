package org.example;

import java.util.List;

public interface Board {
    enum Move {
        UP, DOWN, LEFT, RIGHT
    }

    public List<MoveTurple> getNeighbors();

    public boolean isGoal();

    public void move(Move move) throws WrongMoveException;
}
