package org.example;
import java.util.*;

class BoardWithDepth {
    public BoardWithDepth(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }
    public Board board;
    public int depth;
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardWithDepth) {
            BoardWithDepth other = (BoardWithDepth) obj;
            return board.equals(other.board);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, depth);
    }
}
public class DFS {
    public static int MAX_DEPTH = 20;
    private static final ArrayDeque<BoardWithDepth> open = new ArrayDeque<>();
    private static final HashSet<BoardWithDepth> closed = new HashSet<>();
    private static final HashMap<BoardWithDepth, Board.Move> traversalGraph = new HashMap<>();
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
            if(curr.depth > maxDepth) {
                maxDepth = curr.depth;
            }
            if (curr.depth < MAX_DEPTH && !closed.contains(curr)) {
                closed.add(curr);
                for (MoveTuple neighbor : curr.board.getNeighborsReversed()) {
                    BoardWithDepth neighborWithDepth = new BoardWithDepth(neighbor.board, curr.depth + 1);
                    nodesProcessed++;
                    if (!closed.contains(neighborWithDepth) && curr.depth < MAX_DEPTH) {
                        open.push(neighborWithDepth);
                        traversalGraph.put(neighborWithDepth, neighbor.move);
                        if (neighbor.board.isGoal()) {
                            curr = new BoardWithDepth(neighborWithDepth.board, neighborWithDepth.depth);
                            ArrayList<Board.Move> result = new ArrayList<>();
                            while (!curr.board.equals(board)) {
                                Board.Move lastMove = traversalGraph.get(curr);
                                result.add(lastMove);
                                curr.board.move(lastMove.opposite());
                                curr.depth--;
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
