package isam.maze;

import junitparams.JUnitParamsRunner;
import junitparams.NamedParameters;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class MazeEndToEndTest {

    @Test
    @Parameters(named = "mazes")
    public void shouldSolveMaze(String resource) throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource(resource).toURI());
        Maze maze = Maze.parse(path);
        boolean solved = maze.solve();
        Assert.assertTrue("Expected maze to be solved", solved);
        maze.show();
    }

    @Test
    public void shouldNotSolveMazeWithNoSolution() throws URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource("maze/no_solution.txt").toURI());
        Maze maze = Maze.parse(path);
        boolean solved = maze.solve();
        Assert.assertFalse("Expected maze not to be solved", solved);
        maze.show();
    }

    @NamedParameters("mazes")
    private List<String> getMazeFilesToTest() {
        return Arrays.asList(
                "maze/input.txt",
                "maze/small.txt",
                "maze/medium_input.txt",
                "maze/large_input.txt");
    }
}