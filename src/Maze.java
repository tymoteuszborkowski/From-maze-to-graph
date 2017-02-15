import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Maze {

    private final static String FILE_PATH = "maze.txt";
    private final char[][] maze;

    Maze() {

        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {

            stream.forEach(lines::add);

        } catch (IOException e) {
            e.printStackTrace();
        }

        maze = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = lines.get(i).charAt(j);
            }
        }
    }


    Map<Node, List<Neighbour>> checkDistance() {
        Map<Node, List<Neighbour>> map = new HashMap<>();


        for (int x = 1; x < maze.length - 1; x++) {
            for (int y = 1; y < maze[x].length - 1; y++) {

                if (maze[x][y] != '#') {

                    Node node = new Node(x, y);
                    List<Neighbour> neighbours = new ArrayList<>();

                    if (isIntersection(x, y)) {

                        if (maze[x - 1][y] == '.') {
                            goDown(x - 1, y, neighbours, node);
                        }

                        if (maze[x + 1][y] == '.') {
                            goUp(x + 1, y, neighbours, node);
                        }

                        if (maze[x][y + 1] == '.') {
                            goUp(x, y + 1, neighbours, node);
                        }

                        if (maze[x][y - 1] == '.') {
                            goDown(x, y, neighbours, node);
                        }

                        map.put(node, neighbours);
                    }
                }
            }
        }

        return map;
    }

    private boolean isIntersection(int x, int y) {
        int counter = 0;
        if (maze[x + 1][y] == '.') {
            counter++;
        }

        if (maze[x - 1][y] == '.') {
            counter++;
        }

        if (maze[x][y + 1] == '.') {
            counter++;
        }

        if (maze[x][y - 1] == '.') {
            counter++;
        }

        return counter >= 3;
    }

   private void goDown(int x, int y, List<Neighbour> neighbours, Node node) {
        int distance = 0;

       outerLoop:
        for (int i = x; i > 0; i--) {
            for (int j = y; maze[i][j] != '#'; j--) {
                distance++;
                if (isIntersection(i, j)) {
                    if (x != i || y != j) {
                        neighbours.add(new Neighbour(i, j, distance, node));
                        break outerLoop;
                    }
                }
            }
        }

    }

    private void goUp(int x, int y, List<Neighbour> neighbours, Node node) {
        int distance = 0;

        outerLoop:
        for (int i = x; i < maze.length - 1; i++) {
            for (int j = y; maze[i][j] != '#'; j++) {
                distance++;
                if (isIntersection(i, j)) {
                    if (x != i || y != j) {
                        neighbours.add(new Neighbour(i, j, distance, node));
                        break outerLoop;
                    }
                }
            }
        }

    }

}
