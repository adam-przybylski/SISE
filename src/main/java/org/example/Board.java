package org.example;

import java.util.List;

public interface Board {
    enum Move {
        UP {
            @Override
            public Move opposite() {
                return DOWN;
            }
        },
        DOWN {
            @Override
            public Move opposite() {
                return UP;
            }
        },
        LEFT {
            @Override
            public Move opposite() {
                return RIGHT;
            }
        },
        RIGHT {
            @Override
            public Move opposite() {
                return LEFT;
            }
        };

        abstract public Move opposite();
    }

    public List<MoveTuple> getNeighbors();

    public boolean isGoal();

    public void move(Move move) throws WrongMoveException;

    public int[] getState();
}
