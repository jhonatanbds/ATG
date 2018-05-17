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
        if (type.equals("AM")) {
            ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
            Collections.sort(verticesTemp);
            return getAMString(verticesTemp, getAM(verticesTemp));
        } else if (type.equals("AL")) {
            List<Integer> orderedVertexes = new ArrayList<Integer>(this.vertices.keySet());
            Collections.sort(orderedVertexes);
            StringBuilder list = new StringBuilder();

            for (Integer v: orderedVertexes) {
                String neighbors = vertices.get(v).stream()
                        .sorted(Comparator.comparing(Aresta::getTargetVertex))
                        .map(this::mapOperatorListRepresentation)
                        .reduce((s1, s2) -> s1 + " " + s2).orElse("");
                list.append(v).append(" - ").append(neighbors).append(LINE_SEPARATOR);
            }
            return list.toString();
        } else {
            return "Tipo não definido";
        }
    }

    protected  float[][] getAM(ArrayList<Integer> verticesOrdenados) {
        float am[][] = new float[vertexNumber][vertexNumber];

        for(int i = 0; i < vertexNumber; i++) {
            Integer currentVertex = verticesOrdenados.get(i);
            Set<Aresta> connectedEdges = vertices.get(currentVertex);
            for (Aresta edge : connectedEdges) {
                Integer targetVertex = edge.getTargetVertex();
                am[i][verticesOrdenados.indexOf(targetVertex)] = edge.getPeso();
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

    protected abstract String mapOperatorListRepresentation(E e);

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
