1. Tests

Execute in the project root directory:

mvn clean test

2. Building

Execute in the project root directory:

mvn clean package

3. Running

java -cp maze-solver-1.0-SNAPSHOT.jar isam.maze.Launcher maze/input.txt

Example output:

Found a solution to the maze

#####
#S# #
#X# #
#XXE#
#####

java -cp maze-solver-1.0-SNAPSHOT.jar isam.maze.Launcher maze/no_solution.txt

Unable to find a solution to the maze

#####
#S# #
# # #
# # #
# # #
# #E#
#####