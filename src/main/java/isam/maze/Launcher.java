package isam.maze;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Launcher {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No maze file supplied");
        }
        Path path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Path " + path + " does not exists");
        }
        Maze maze = Maze.parse(path);
        boolean solved = maze.solve();
        System.out.println(solved ? "Found a solution to the maze\n" : "Unable to find a solution to the maze\n");
        maze.show();
    }
}
