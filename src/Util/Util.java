package Util;

import Graph.Graph;

public class Util {

    Graph readGraph(String path) {
        return new Graph();
    }

    Graph readWeightedGraph(String path) {
        return new Graph();
    }

    int getVertexNumber(Graph graph) {
        return graph.getVertexNumber();
    }

    int getEdgeNumber(Graph graph) {
        return graph.getEdgeNumber();
    }

    float getMeanEdge(Graph graph) {
        return graph.getMeanEdge();
    }

    String graphRepresentation (Graph graph, String type) {
        return "";
    }
}
