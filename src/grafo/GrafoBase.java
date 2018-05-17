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
            List<Integer> orderedVertexes = new ArrayList<Integer>(this.vertices.keySet());
            Collections.sort(orderedVertexes);
            int vertexesNumber = this.vertexNumber;
            float adjacencyMatrix[][] = new float[vertexesNumber][vertexesNumber];
            for(int i = 0; i < vertexesNumber; i++) {
                Integer currentVertex = orderedVertexes.get(i);
                Set<Aresta> connectedEdges = vertices.get(currentVertex);
                for (Aresta edge : connectedEdges) {
                    Integer targetVertex = edge.getTargetVertex();
                    adjacencyMatrix[i][orderedVertexes.indexOf(targetVertex)] = edge.getPeso();
                }
            }
            return getAdjacencyMatrixString(orderedVertexes, adjacencyMatrix);
        } else if (type.equals("AL")) {
            return "todo";
        } else {
            return "Tipo não definido";
        }
    }

    protected String getAdjacencyMatrixString(List<Integer> orderedVertexes, float[][] adjacencyMatrix) {
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
