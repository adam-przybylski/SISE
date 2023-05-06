package org.example;

import java.util.*;

public class BFS {
    public static int MAX_DEPTH = 10000;
    private static final ArrayDeque<BoardWithDepth> open = new ArrayDeque<>();
    private static final HashSet<BoardWithDepth> closed = new HashSet<>();

    //  In value, we keep move that led to this board
    private static final HashMap<Board, Board.Move> traversalGraph = new HashMap<>();
    private static int depth = 0;

    // TODO: return path
    public static Statistics solve(Board board, String order) throws WrongMoveException {
        long startTime = System.nanoTime();
        int maxDepth = 0;
        int nodesVisited = 0;
        int nodesProcessed = 0;
        BoardWithDepth boardWithDepth = new BoardWithDepth(board, 0);
        open.add(boardWithDepth);
        //traversalGraph.put(board, null);
        while (!open.isEmpty()) {
            BoardWithDepth current = open.poll();
            nodesVisited++;
            if (current.depth > maxDepth) {
                maxDepth = current.depth;
            }
            if (current.board.isGoal()) {
                // TODO: return path
                ArrayList<Board.Move> result = new ArrayList<>();
                while (!current.board.equals(board)) {
                    Board.Move lastMove = traversalGraph.get(current.board);
                    result.add(lastMove);
                    current.board.move(lastMove.opposite());
                }
                Collections.reverse(result);
                long endTime = System.nanoTime();
                long timeElapsed = endTime - startTime;
                return new Statistics(result.size(), nodesVisited, nodesProcessed, maxDepth, timeElapsed, result);
            }
            closed.add(current);
            for (MoveTuple neighbor : current.board.getNeighbors()) {
                nodesProcessed++;
                BoardWithDepth neighborWithDepth = new BoardWithDepth(neighbor.board, depth + 1);
                if (!closed.contains(neighborWithDepth) && !open.contains(neighborWithDepth)) {
                    open.add(neighborWithDepth);
                    // May be to change how we got here, I think it's ok nvm
                    traversalGraph.put(neighbor.board, neighbor.move);
                }
            }
        }
        return null;
    }

}
