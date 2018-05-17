package controller;

import grafo.Grafo;
import grafo.GrafoPonderado;

import java.io.BufferedReader;
import java.io.FileReader;

public class GrafoController {

    Grafo readGrafo(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(fileReader);

            Integer vertices = Integer.parseInt(buffer.readLine());

            for (int i = 0; i < vertices ; i++) {
                String aresta = buffer.readLine();

            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    GrafoPonderado readWeightedGrafo(String path) {
        return null;
    }

    String BFS(Grafo graph, Integer vertex) {
        return "";
    }

    String DFS(Grafo graph, Integer vertex) {
        return "";
    }

    boolean connected(Grafo graph) {
        return false;
    }

    String shortestPath(Grafo graph, Integer head, Integer tail) {
        return "";
    }

    String mst(Grafo graph) {
        return "";
    }

    int getVertexNumber(Grafo Grafo) {
        return Grafo.getVertexNumber();
    }

    int getEdgeNumber(Grafo Grafo) {
        return Grafo.getEdgeNumber();
    }

    float getMeanEdge(Grafo Grafo) {
        return Grafo.getMeanEdge();
    }

    String GrafoRepresentation (Grafo Grafo, String type) {
        return "";
    }
}
