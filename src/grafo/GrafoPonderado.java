package grafo;

import java.util.List;
import java.util.Set;

public class GrafoPonderado extends GrafoBase {

    public GrafoPonderado(Set<Vertice> vertices, List<Aresta> arestas) {
        super(vertices, arestas);
    }

    @Override
    public String BFS(Grafo graph, Vertice vertex) {
        return null;
    }

    @Override
    public String DFS(Grafo graph, Vertice vertex) {
        return null;
    }

    @Override
    public boolean connected(Grafo graph) {
        return false;
    }

    @Override
    public String shortestPath(Grafo graph, Vertice head, Vertice tail) {
        return null;
    }

    @Override
    public String mst(Grafo graph) {
        return null;
    }
}
