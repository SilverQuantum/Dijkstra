package pathfinders;

import graph.Graph;

public class Main {

    public static void main(String[] args) {      
        Graph g = new Graph();
        Dijkstra d = new Dijkstra();
        g.registerPathFinder(d);
    }
}
