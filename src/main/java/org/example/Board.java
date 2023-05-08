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

        public static Move[] getInOrder(String order) {
            Move[] orderMoves = new Move[order.length()];
            for (int i = 0; i < order.length(); i++) {
                switch (order.charAt(i)) {
                    case 'U' -> orderMoves[i] = UP;
                    case 'D' -> orderMoves[i] = DOWN;
                    case 'L' -> orderMoves[i] = LEFT;
                    case 'R' -> orderMoves[i] = RIGHT;
                }
            }
            return orderMoves;
        }

        public static Move[] getReversed(String order) {
            Move[] orderMoves = new Move[order.length()];
            for (int i = 0; i < order.length(); i++) {
                switch (order.charAt(i)) {
                    case 'U' -> orderMoves[order.length() - i] = DOWN;
                    case 'D' -> orderMoves[order.length() - i] = UP;
                    case 'L' -> orderMoves[order.length() - i] = RIGHT;
                    case 'R' -> orderMoves[order.length() - i] = LEFT;
                }
            }
            return orderMoves;
        }
    }

    public List<MoveTuple> getNeighbors();

    public List<MoveTuple> getNeighborsReversed();

    public boolean isGoal();

    public void move(Move move) throws WrongMoveException;

    public int[] getState();
}
