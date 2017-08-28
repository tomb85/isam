package isam.maze.impl;

import isam.maze.Maze;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class MazeParserTest {

    private MazeParser parser = new MazeParser();

    @Test
    public void shouldParseMaze() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("maze/input_different_height_and_width.txt").toURI());
        Maze maze = parser.parse(path);
        Assert.assertThat(maze.getHeight(), is(equalTo(7)));
        Assert.assertThat(maze.getWidth(), is(equalTo(5)));
        Assert.assertThat(maze.getStart(), is(equalTo(6)));
        Assert.assertThat(maze.getEnd(), is(equalTo(28)));
        Assert.assertThat(maze.getSize(), is(equalTo(35)));
    }
}