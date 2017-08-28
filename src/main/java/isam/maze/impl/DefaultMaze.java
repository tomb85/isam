package isam.maze.impl;

import isam.maze.Maze;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class DefaultMaze implements Maze {

    private final int height;
    private final int width;
    private final int start;
    private final int end;
    private final int[] maze;

    DefaultMaze(int height, int width, int start, int end, int[] maze) {
        this.height = height;
        this.width = width;
        this.start = start;
        this.end = end;
        this.maze = maze;
    }

    static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean solve() {
        Stack<Integer> path = new Stack<>();
        path.push(start);
        Set<Integer> visited = new HashSet<>();
        int[] neighbours = new int[4];
        while (!path.isEmpty()) {
            int current = path.peek();
            if (current == end) {
                break;
            }
            visited.add(current);
            fillNeighbours(current, neighbours);
            Arrays.stream(neighbours)
                    .filter(value -> value >= 0)
                    .filter(value -> value < maze.length)
                    .filter(this::isPassage)
                    .filter(negate(visited::contains))
                    .forEach(path::push);
            if (current == path.peek()) {
                path.pop();
            }
        }
        boolean solved = !path.isEmpty();
        if (solved) {
            applyPath(path, visited);
        }
        return solved;
    }

    private boolean isPassage(int index) {
        return maze[index] == '0';
    }

    private IntPredicate negate(IntPredicate predicate) {
        return predicate.negate();
    }

    private int[] fillNeighbours(int current, int[] neighbours) {
        neighbours[0] = current - width;    // up
        neighbours[1] = current + width;    // down
        neighbours[2] = current - 1;        // left
        neighbours[3] = current + 1;        // right
        return neighbours;
    }

    private void applyPath(Stack<Integer> path, Set<Integer> visited) {
        path.stream().skip(1).limit(path.size() - 2).filter(visited::contains).forEach(i -> maze[i] = 'X');
    }

    @Override
    public void show() {
        int[] maze = Arrays.copyOf(this.maze, this.maze.length);
        add(maze, start, () -> 'S');
        add(maze, end, () -> 'E');
        for (int i = 0; i < maze.length; i++) {
            System.out.print(getDisplayChar(maze[i]));
            if ((i + 1) % width == 0) {
                System.out.println();
            }
        }
    }

    private void add(int[] maze, int index, Supplier<Character> supplier) {
        char symbol = supplier.get();
        maze[index] = symbol;
    }

    private char getDisplayChar(int value) {
        switch (value) {
            case 48:
                return ' ';
            case 49:
                return '#';
            default:
                return (char) value;
        }
    }

    static class Builder {

        private Integer height;
        private Integer width;
        private Integer startCol;
        private Integer startRow;
        private Integer endCol;
        private Integer endRow;
        private int[] maze;

        public Builder withDimensions(int height, int width) {
            this.height = height;
            this.width = width;
            return this;
        }

        Builder withStart(int column, int row) {
            this.startCol = column;
            this.startRow = row;
            return this;
        }

        Builder withEnd(int column, int row) {
            this.endCol = column;
            this.endRow = row;
            return this;
        }

        Builder withMaze(int[] maze) {
            this.maze = maze;
            return this;
        }

        Maze build() {
            requireNonNull(height, "Height must be specified");
            requireNonNull(width, "Width must be specified");
            requireNonNull(startCol, "Start column must be specified");
            requireNonNull(startRow, "Start row must be specified");
            requireNonNull(endCol, "End column must be specified");
            requireNonNull(endRow, "End row must be specified");
            requireNonNull(maze, "Maze must be specified");
            return new DefaultMaze(height, width, startCol * width + startRow, endCol * width + endRow, maze);
        }
    }
}