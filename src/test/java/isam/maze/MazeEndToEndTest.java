package isam.maze;

import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MazeEndToEndTest {

    // TODO more tests

    @Test
    public void shouldSolveMaze() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("maze/large_input.txt").toURI());
        Maze maze = Maze.parse(path);
        boolean solved = maze.solve();
        Assert.assertTrue("Expected maze to be solved", solved);
        maze.show();
    }
}