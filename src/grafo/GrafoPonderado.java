package grafo;

import java.util.Map;
import java.util.Set;

public class GrafoPonderado extends GrafoBase {

    public GrafoPonderado() {
        super();
    }

    @Override
    protected String getALVertice1Model(Aresta a) {
        return a.getVertice1() + "(" + a.getPeso() + ")";
    }

    @Override
    protected String getALVertice2Model(Aresta a) {
        return a.getVertice2() + "(" + a.getPeso() + ")";
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

    @Override
    public void addAresta(String aresta) {

    }
}
