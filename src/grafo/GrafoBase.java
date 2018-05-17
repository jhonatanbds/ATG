package grafo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GrafoBase {

    private Set<Vertice> vertices = new HashSet<Vertice>();
    private List<Aresta> arestas = new ArrayList<Aresta>();

    private float meanEdge; //grau medio

    public GrafoBase(Set<Vertice> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;

    }

    public float getMeanEdge() {
        return meanEdge;
    }

    public int getEdgeNumber() {
        return arestas.size();
    }

    public int getVertexNumber() {
        return vertices.size();
    }

    abstract String BFS(Grafo graph, Vertice vertex);

    abstract String DFS(Grafo graph, Vertice vertex);

    abstract boolean connected(Grafo graph);

    abstract String shortestPath(Grafo graph, Vertice head, Vertice tail);

    abstract String mst(Grafo graph);

}
