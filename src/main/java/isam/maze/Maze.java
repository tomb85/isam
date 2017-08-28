package isam.maze;

import isam.maze.impl.MazeParser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface Maze {

    // TODO javadoc

    MazeParser PARSER = new MazeParser();

    static Maze parse(Path path) {
        try {
            return PARSER.parse(path);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse the maze", e);
        }
    }

    static Maze parse(String file) {
        return parse(Paths.get(file));
    }

    boolean solve();

    void show();

    int getHeight();

    int getWidth();

    int getStart();

    int getEnd();

    int getSize();
}
