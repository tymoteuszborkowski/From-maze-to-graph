import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Maze {

    private final static String FILE_PATH = "maze.txt";

    List<String> lines = new ArrayList<>();

    public Maze() {
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {

            stream.forEach(lines::add);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void countIntersections() {
        char[][] maze = getChars();


        int intersections = 0;
        int columns = 0;
        int rows = 0;
        for (int i = 0; i < maze.length; i++) {

            for (int j = 0; j < maze[i].length; j++) {

                if (maze[i][j] != '.') {
                    int counter = 0;

                    try {
                        if (maze[i - 1][j] != '#') {
                            counter++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}


                    try{
                        if (maze[i + 1][j] != '#') {
                            counter++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}


                    try{
                        if (maze[i][j - 1] != '#') {
                            counter++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}


                    try{
                        if (maze[i][j + 1] != '#') {
                            counter++;
                        }
                    }catch (ArrayIndexOutOfBoundsException e){}


                    if (counter >= 3) {
                        intersections++;
                    }
                }

            }
        }

        System.out.println(rows + " co" + columns);
        System.out.println(intersections);

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }


    char[][] getChars() {

        char[][] maze = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = lines.get(i).charAt(j);
            }
        }


        return maze;
    }

}
