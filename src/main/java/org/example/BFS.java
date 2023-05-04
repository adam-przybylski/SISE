package org.example;

import java.util.*;

public class BFS {
    public static int MAX_DEPTH = 10000;
    private static final ArrayDeque<Board> open = new ArrayDeque<>();
    private static final HashSet<Board> closed = new HashSet<>();

    //  In value, we keep move that led to this board
    private static final HashMap<Board, Board.Move> traversalGraph = new HashMap<>();
    private static int depth = 0;
    // TODO: return path
    public static List<Board.Move> solve(Board board, String order) throws WrongMoveException {
        open.add(board);
        //traversalGraph.put(board, null);
        while (!open.isEmpty()) {
            Board current = open.poll();
            if (current.isGoal()) {
                // TODO: return path
                List<Board.Move> result = new ArrayList<>();
                while (!current.equals(board)) {
                    Board.Move lastMove = traversalGraph.get(current);
                    result.add(lastMove);
                    current.move(lastMove.opposite());
                }
                Collections.reverse(result);
                return result;
            }
            closed.add(current);
            for (MoveTuple neighbor : current.getNeighbors()) {
                // Can change for performance
                if (!closed.contains(neighbor.board) && !open.contains(neighbor.board)) {
                    open.add(neighbor.board);
                    // May be to change how we got here, I think it's ok nvm
                    traversalGraph.put(neighbor.board, neighbor.move);
                }
            }
            // TODO: fix depth
            depth++;
//            if (depth > MAX_DEPTH) {
//                return null;
//            }
        }
        return null;
    }

}
