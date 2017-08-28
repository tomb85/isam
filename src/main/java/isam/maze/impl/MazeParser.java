package isam.maze.impl;

import isam.maze.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MazeParser {

    private static final int DIMENSIONS = 0;
    private static final int START_POS = 1;
    private static final int END_POS = 2;

    public Maze parse(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        DefaultMaze.Builder builder = DefaultMaze.builder();
        parseFields(lines.get(DIMENSIONS), builder::withDimensions);
        parseFields(lines.get(START_POS), builder::withStart);
        parseFields(lines.get(END_POS), builder::withEnd);
        parseMaze(lines, builder::withMaze);
        return builder.build();
    }

    private void parseMaze(List<String> lines, Function<int[], DefaultMaze.Builder> setter) {
        List<String> mazeLines = lines.stream().skip(3).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        mazeLines.forEach(builder::append);
        String maze = builder.toString();
        int[] array = IntStream.range(0, maze.length()).map(maze::charAt).filter(Character::isDigit).toArray();
        setter.apply(array);
    }

    private void parseFields(String line, BiFunction<Integer, Integer, DefaultMaze.Builder> setter) {
        String[] values = line.split(" ");
        int column = Integer.parseInt(values[0]);
        int row = Integer.parseInt(values[1]);
        setter.apply(column, row);
    }
}