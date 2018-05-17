package grafo;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class Grafo extends GrafoBase {

    public Grafo(Map<Integer, Set<Aresta>> vertices, List<Aresta> arestas) {
        super(vertices, arestas);
    }

    @Override
    protected String getALVertice1Model(Aresta a) {
        return a.getVertice1().toString();
    }

    @Override
    protected String getALVertice2Model(Aresta a) {
        return a.getVertice2().toString();
    }

    @Override
    protected String mapOperatorListRepresentation(Aresta e) {
        return null;
    }

    @Override
    String BFS(GrafoBase graph, Integer vertex) {
        return null;
    }

    @Override
    String DFS(GrafoBase graph, Integer vertex) {
        return null;
    }


    @Override
    boolean connected(GrafoBase graph) {
        return false;
    }

    @Override
    String shortestPath(GrafoBase graph, Integer head, Integer tail) {
        return null;
    }

    @Override
    String mst(GrafoBase graph) {
        return null;
    }
}
