package grafo;


import java.util.List;
import java.util.Set;

public class Grafo extends GrafoBase {

    public Grafo(Set<Vertice> vertices, List<Aresta> arestas) {
        super(vertices, arestas);
    }

    @Override
    protected String getALVertice1Model() {
        return null;
    }

    @Override
    protected String getALVertice2Model() {
        return null;
    }

    @Override
    protected String mapOperatorListRepresentation(Aresta e) {
        return null;
    }

    @Override
    String BFS(GrafoBase graph, Vertice vertex) {
        return null;
    }

    @Override
    String DFS(GrafoBase graph, Vertice vertex) {
        return null;
    }

    @Override
    boolean connected(GrafoBase graph) {
        return false;
    }

    @Override
    String shortestPath(GrafoBase graph, Vertice head, Vertice tail) {
        return null;
    }

    @Override
    String mst(GrafoBase graph) {
        return null;
    }
}
