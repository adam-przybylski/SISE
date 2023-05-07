package org.example;
import java.util.*;

class BoardWithDepth {
    public BoardWithDepth(Board board, int depth) {
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
        if (obj instanceof BoardWithDepth) {
            BoardWithDepth other = (BoardWithDepth) obj;
            return board.equals(other.board);
        }
        return false;
    }
}
public class DFS {
    public static int MAX_DEPTH = 20;
    private static final ArrayDeque<BoardWithDepth> open = new ArrayDeque<>();
    private static final HashSet<BoardWithDepth> closed = new HashSet<>();
    private static final HashMap<Board, Board.Move> traversalGraph = new HashMap<>();
    public static Statistics solve(Board board, String order) throws WrongMoveException {
        long startTime = System.nanoTime();
        int maxDepth = 1;
        int nodesVisited = 1;
        int nodesProcessed = 1;
        BoardWithDepth boardWithDepth = new BoardWithDepth(board, 0);
        open.add(boardWithDepth);
        while (!open.isEmpty()) {
            BoardWithDepth curr =open.poll();
            nodesVisited++;
            Board current = curr.board;
            if(curr.depth > maxDepth) {
                maxDepth = curr.depth;
            }
            if (curr.depth < MAX_DEPTH && !closed.contains(curr)) {
                closed.add(curr);
                for (MoveTuple neighbor : current.getNeighbors()) {
                    BoardWithDepth neighborWithDepth = new BoardWithDepth(neighbor.board, curr.depth + 1);
                    nodesProcessed++;
                    if (!closed.contains(neighborWithDepth) && curr.depth < MAX_DEPTH) {
                        open.push(neighborWithDepth);
                        traversalGraph.put(neighbor.board, neighbor.move);
                        if (neighbor.board.isGoal()) {
                            current = neighbor.board;
                            ArrayList<Board.Move> result = new ArrayList<>();
                            while (!current.equals(board)) {
                                Board.Move lastMove = traversalGraph.get(current);
                                result.add(lastMove);
                                current.move(lastMove.opposite());
                            }
                            Collections.reverse(result);
                            long endTime = System.nanoTime();
                            long timeElapsed = endTime - startTime;
                            return new Statistics(result.size(), nodesVisited, nodesProcessed, maxDepth, timeElapsed, result);
                        }
                    }
                }
            }
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        return new Statistics(-1, nodesVisited, nodesProcessed, maxDepth, timeElapsed, null);
    }
}
