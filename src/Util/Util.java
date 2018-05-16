
import Graph.Grafo;

public class Util {

    Grafo readGrafo(String path) {
        return new Grafo();
    }

    Grafo readWeightedGrafo(String path) {
        return new Grafo();
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
