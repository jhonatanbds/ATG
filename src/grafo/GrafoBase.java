package grafo;

import java.util.ArrayList;
import java.util.List;

public abstract class GrafoBase {

    private List<Vertice> vertice = new ArrayList<Vertice>();
    private List<Aresta> arestas = new ArrayList<Aresta>();
    private boolean pesos;

    private float meanEdge; //grau medio
    private int edgeNumber; //numero de arestas
    private int vertexNumber;

    public GrafoBase() {

    }

    public float getMeanEdge() {
        return meanEdge;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    abstract String BFS(Grafo graph, Vertice vertex);

    abstract String DFS(Grafo graph, Vertice vertex);

    abstract boolean connected(Grafo graph);

    abstract String shortestPath(Grafo graph, Vertice head, Vertice tail);

    abstract String mst(Grafo graph);

}
