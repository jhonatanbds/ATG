package grafo;

import java.util.*;

import static java.lang.Character.LINE_SEPARATOR;

public abstract class GrafoBase {

    protected Map<Integer, Set<Aresta>> vertices;
    private List<Aresta> arestas = new ArrayList<Aresta>();
    private boolean pesos;

    private float meanEdge; //grau medio
    private int edgeNumber; //numero de arestas
    private int vertexNumber;

    public GrafoBase() {

    }

    String graphRepresentation (GrafoBase graph, String type) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        if (type.equals("AM")) {
            return getAMString(verticesTemp, getAM(verticesTemp));
        } else if (type.equals("AL")) {
           return getALString(getAL(verticesTemp));
        } else {
            return "Tipo não definido";
        }
    }

    protected String getALString(List<String> al) {
        String res = "";
        for (String s : al)
            res += s + "\n";
        return res;
    }

    protected List<String> getAL(ArrayList<Integer> verticesOrdenados) {
        ArrayList<String> al = new ArrayList<>();
        for (Integer v : verticesOrdenados) {
            String adj = "";
            adj += v + " - ";
            ArrayList<Integer> vAdj = new ArrayList<>();
            for (Aresta a : this.vertices.get(v)) {
                if (a.getV1() != v)
                    vAdj.add(a.getV1());
                else
                    vAdj.add(a.getV2());
            }
            Collections.sort(vAdj);
            for (Integer ver : vAdj)
                adj += ver + " ";
            al.add(adj.trim());
        }
        return al;
    }

    protected abstract String getALVertice1Model();
    protected abstract String getALVertice2Model();

    protected  float[][] getAM(ArrayList<Integer> verticesOrdenados) {
        float am[][] = new float[vertexNumber][vertexNumber];

        for(int i = 0; i < vertexNumber; i++) {
            Integer atual = verticesOrdenados.get(i);
            Set<Aresta> arestas = vertices.get(atual);
            for (Aresta a : arestas) {
                Integer targetVertex = a.getTargetVertex();
                am[i][verticesOrdenados.indexOf(targetVertex)] = a.getPeso();
            }
        }
        return am;
    }
    protected String getAMString(List<Integer> orderedVertexes, float[][] adjacencyMatrix) {
        int vertexesNumber = vertexNumber;
        StringBuilder matrixSB = new StringBuilder("  ");

        for (int i = 0; i < vertexesNumber; i++) {
            matrixSB.append(orderedVertexes.get(i));
            boolean shouldAddSpace = vertexesNumber - i > 1;
            if (shouldAddSpace) matrixSB.append(" ");
        }
        matrixSB.append(LINE_SEPARATOR);
        for(int i = 0; i < vertexesNumber; i++) {
            StringBuilder line = new StringBuilder(orderedVertexes.get(i) + " ");
            for(int j = 0; j < vertexesNumber; j++) {
                line.append(Float.toString(adjacencyMatrix[i][j]));
                boolean shouldAddSpace = vertexesNumber - j > 1;
                if (shouldAddSpace) line.append(" ");
            }
            matrixSB.append(line).append(LINE_SEPARATOR);
        }
        return matrixSB.toString();
    }

    protected abstract String mapOperatorListRepresentation(Aresta e);

    public float getMeanEdge() {
        return meanEdge;
    }

    public int getEdgeNumber() {
        return edgeNumber;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    abstract String BFS(GrafoBase graph, Vertice vertex);

    abstract String DFS(GrafoBase graph, Vertice vertex);

    abstract boolean connected(GrafoBase graph);

    abstract String shortestPath(GrafoBase graph, Vertice head, Vertice tail);

    abstract String mst(GrafoBase graph);

}
