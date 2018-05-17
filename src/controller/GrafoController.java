package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.GrafoBase;
import grafo.GrafoPonderado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GrafoController {

    Grafo readGrafo(String path) {


        return null;
    }

    GrafoBase createGrafo(String path, boolean ponderado){
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(fileReader);

            Integer vertices = Integer.parseInt(buffer.readLine());

            GrafoBase newGraph;

            if(ponderado){
                newGraph = new GrafoPonderado();
            } else {
                newGraph = new Grafo();
            }

            for (int i = 0; i < vertices ; i++) {
                String aresta = buffer.readLine();
                newGraph.addAresta(aresta);
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
