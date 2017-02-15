import java.util.List;
import java.util.Map;

public class Init {

    public static void main(String[] args) {
        Maze maze = new Maze();

        Map<Node, List<Neighbour>> map = maze.checkDistance();

        System.out.println(map.keySet().size());
        System.out.println(map.values().size());

    }

}
