package org.example;

import java.util.*;
import java.util.function.Function;

public class AStar {
    private static final PriorityQueue<BoardWithScore> open = new PriorityQueue<>();
    private static final HashSet<Board> closed = new HashSet<>();
    private static final HashMap<Board, LastMoveScoreTuple> traversalGraph = new HashMap<>();

    public static List<Board.Move> solve(Board board, Function<Board, Integer> heuristic) throws WrongMoveException {
        BoardWithScore boardWithScore = new BoardWithScore(board, 0, heuristic.apply(board));
        open.add(boardWithScore);
        while (!open.isEmpty()) {
            BoardWithScore curr = open.poll();
            Board current = curr.board;
            if (current.isGoal()) {
                List<Board.Move> result = new ArrayList<>();
                while (!current.equals(board)) {
                    Board.Move lastMove = traversalGraph.get(current).lastMove;
                    result.add(lastMove);
                    current.move(lastMove.opposite());
                }
                Collections.reverse(result);
                return result;
            }
            if (!closed.contains(current)) {
                closed.add(current);
                for (MoveTuple neighbor : current.getNeighbors()) {
                    if (!closed.contains(neighbor.board)) {
                        BoardWithScore neighborWithScore = new BoardWithScore(
                                neighbor.board,
                                curr.depth + 1,
                                curr.depth + 1 + heuristic.apply(neighbor.board)
                        );
                        open.add(neighborWithScore);
                        traversalGraph.put(neighbor.board, new LastMoveScoreTuple(neighbor.move, neighborWithScore.score));
                    }
                }
            } else if (traversalGraph.get(current) != null) {
                if (traversalGraph.get(current).score > curr.depth + 1 + heuristic.apply(current)) {
                    traversalGraph.put(current, new LastMoveScoreTuple(traversalGraph.get(current).lastMove, curr.depth + 1 + heuristic.apply(current)));
                }
            }
        }
        return null;
    }

    public static List<Board.Move> solveHamming(Board board) throws WrongMoveException {
        return solve(board, new HammingHeuristic());
    }
    public static List<Board.Move> solveManhattan(Board board) throws WrongMoveException {
        return solve(board, new ManhattanHHeuristic());
    }
}

class BoardWithScore implements Comparable<BoardWithScore> {
    public final Board board;
    public final int depth;
    public final int score;

    public BoardWithScore(Board board, int depth, int score) {
        this.board = board;
        this.depth = depth;
        this.score = score;
    }

    @Override
    public int compareTo(BoardWithScore other) {
        return Integer.compare(score, other.score);
    }
}

class LastMoveScoreTuple {
    public final Board.Move lastMove;
    public final int score;

    public LastMoveScoreTuple(Board.Move lastMove, int score) {
        this.lastMove = lastMove;
        this.score = score;
    }
}

class HammingHeuristic implements Function<Board, Integer> {
    @Override
    public Integer apply(Board board) {
        int result = 0;
        for (int i = 0; i < 16; i++) {
            if (board.getState()[i] != i + 1) {
                result++;
            }
        }
        return result;
    }
}

class ManhattanHHeuristic implements Function<Board, Integer> {
    @Override
    public Integer apply(Board board) {
        int result = 0;
        for (int i = 0; i < 16; i++) {
            int value = board.getState()[i];
            if (value != i + 1 && value != 0) {
                int x = (value - 1) % 4;
                int y = (value - 1) / 4;
                int x0 = i % 4;
                int y0 = i / 4;
                result += Math.abs(x - x0) + Math.abs(y - y0);
            }
        }
        return result;
    }
}
