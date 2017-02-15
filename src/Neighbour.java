class Neighbour {

    private final int x;
    private final int y;
    private final int distance;
    private final Node node;


    Neighbour(int x, int y, int distance, Node node) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.node = node;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getDistance() {
        return distance;
    }

    Node getNode() {
        return node;
    }
}
