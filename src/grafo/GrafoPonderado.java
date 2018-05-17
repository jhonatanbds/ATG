package grafo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoPonderado extends GrafoBase {

    public GrafoPonderado(Map<Integer, Set<Aresta>> Integers, List<Aresta> arestas) {
        super(Integers, arestas);
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
