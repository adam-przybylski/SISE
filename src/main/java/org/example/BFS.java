package org.example;

import java.util.*;

class BoardWithDepthNoHash {
    public BoardWithDepthNoHash(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }
    public Board board;
    public int depth;
    public int hashCode() {
        return board.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardWithDepthNoHash) {
            BoardWithDepthNoHash other = (BoardWithDepthNoHash) obj;
            return board.equals(other.board);
        }
        return false;
    }
}

public class BFS {
    public static int MAX_DEPTH = 10000;
    private static final ArrayDeque<BoardWithDepthNoHash> open = new ArrayDeque<>();
    private static final HashSet<BoardWithDepthNoHash> closed = new HashSet<>();

    //  In value, we keep move that led to this board
    private static final HashMap<Board, Board.Move> traversalGraph = new HashMap<>();
    private static int depth = 0;

    // TODO: return path
    public static Statistics solve(Board board, String order) throws WrongMoveException {
        long startTime = System.nanoTime();
        int maxDepth = 0;
        int nodesVisited = 0;
        int nodesProcessed = 0;
        BoardWithDepthNoHash boardWithDepth = new BoardWithDepthNoHash(board, 0);
        open.add(boardWithDepth);
        //traversalGraph.put(board, null);
        while (!open.isEmpty()) {
            BoardWithDepthNoHash current = open.poll();
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
                BoardWithDepthNoHash neighborWithDepth = new BoardWithDepthNoHash(neighbor.board, current.depth + 1);
                if (!closed.contains(neighborWithDepth) && !open.contains(neighborWithDepth)) {
                    open.add(neighborWithDepth);
                    // May be to change how we got here, I think it's ok nvm
                    traversalGraph.put(neighbor.board, neighbor.move);
                }
            }
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        return new Statistics(-1, nodesVisited, nodesProcessed, maxDepth, timeElapsed, null);
    }

}
