package org.example;

import java.util.ArrayDeque;
import java.util.HashMap;

public class BFS {
    public static int MAX_DEPTH = 100;
    private static ArrayDeque<Board> open = new ArrayDeque<>();
    private static HashMap<Board, MoveTurple> closed = new HashMap<>();
    private static int depth = 0;
    public static MoveTurple solve(Board board) {
        open.add(board);
        while (!open.isEmpty()) {
            Board current = open.poll();
            if (current.isGoal()) {
                return closed.get(current);
            }
            for (MoveTurple neighbor : current.getNeighbors()) {
                if (!closed.containsKey(neighbor.board)) {
                    closed.put(neighbor.board, neighbor);
                    open.add(neighbor.board);
                }
            }
            depth++;
            if (depth > MAX_DEPTH) {
                return null;
            }
        }
        return null;
    }

}
