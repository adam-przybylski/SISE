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
}
public class DFS {
    public static int MAX_DEPTH = 25;
    private static final ArrayDeque<BoardWithDepth> open = new ArrayDeque<>();
    private static final HashSet<Board> closed = new HashSet<>();
    private static final HashMap<Board, Board.Move> traversalGraph = new HashMap<>();
    public static List<Board.Move> solve(Board board, String order) throws WrongMoveException {
        BoardWithDepth boardWithDepth = new BoardWithDepth(board, 0);
        open.add(boardWithDepth);
        while (!open.isEmpty()) {
            BoardWithDepth curr =open.poll();
            Board current = curr.board;
            if (current.isGoal()) {
                List<Board.Move> result = new ArrayList<>();
                while (!current.equals(board)) {
                    Board.Move lastMove = traversalGraph.get(current);
                    result.add(lastMove);
                    current.move(lastMove.opposite());
                }
                Collections.reverse(result);
                return result;
            }
            if (curr.depth < MAX_DEPTH && !closed.contains(current)) {
                closed.add(current);
                for (MoveTuple neighbor : current.getNeighbors()) {
                    if (!closed.contains(neighbor.board) && curr.depth < MAX_DEPTH) {
                        BoardWithDepth neighborWithDepth = new BoardWithDepth(neighbor.board, curr.depth + 1);
                        open.push(neighborWithDepth);
                        traversalGraph.put(neighbor.board, neighbor.move);
                    }
                }
            }
        }
        return null;
    }
}
