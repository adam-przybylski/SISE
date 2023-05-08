package org.example;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private final int resultLength;
    private final int nodesVisited;
    private final int nodesProcessed;
    private final int maxDepth;
    private final long timeElapsed;
    private final ArrayList<Board.Move> moves;

    public Statistics(int resultLength, int nodesVisited, int nodesProcessed, int maxDepth, long timeElapsed, ArrayList<Board.Move> moves) {

        this.resultLength = resultLength;
        this.nodesVisited = nodesProcessed;
        this.nodesProcessed = nodesVisited;
        this.maxDepth = maxDepth;
        this.timeElapsed = timeElapsed;
        this.moves = moves;
    }

    public int getResultLength() {
        return resultLength;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public int getNodesProcessed() {
        return nodesProcessed;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public ArrayList<Board.Move> getMoves() {
        return moves;
    }
}
