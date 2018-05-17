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

    public GrafoBase(Map<Integer, Set<Aresta>> vertices, List<Aresta> arestas) {
        this.vertices = vertices;
        this.arestas = arestas;

    }

    public String graphRepresentation (String type) {
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
            ArrayList<String> vAdj = new ArrayList<>();
            for (Aresta a : this.vertices.get(v)) {
                if (a.getVertice1() != v)
                    vAdj.add(getALVertice1Model(a));
                else
                    vAdj.add(getALVertice2Model(a));
            }
            Collections.sort(vAdj);
            for (String ver : vAdj)
                adj += ver + " ";
            al.add(adj.trim());
        }
        return al;
    }

    protected abstract String getALVertice1Model(Aresta a);

    protected abstract String getALVertice2Model(Aresta a);

    protected  double[][] getAM(ArrayList<Integer> verticesOrdenados) {
        double am[][] = new double[vertexNumber][vertexNumber];

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
    protected String getAMString(List<Integer> orderedVertexes, double[][] adjacencyMatrix) {
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
                line.append(Double.toString(adjacencyMatrix[i][j]));
                boolean shouldAddSpace = vertexesNumber - j > 1;
                if (shouldAddSpace) line.append(" ");
            }
            matrixSB.append(line).append(LINE_SEPARATOR);
        }
        return matrixSB.toString();
    }

    public float getMeanEdge() {
        float vertexNumber = getVertexNumber();
        if (vertexNumber > 0) {
        	meanEdge = getEdgeNumber() / getVertexNumber();
        }else {
        	meanEdge = 0;
        }
        return meanEdge;
    }

    public int getEdgeNumber() {
        return arestas.size();
    }

    public int getVertexNumber() {
        return vertices.size();
    }

    abstract String BFS(GrafoBase graph, Integer vertex);

    abstract String DFS(GrafoBase graph, Integer vertex);

    abstract boolean connected(GrafoBase graph);

    abstract String shortestPath(GrafoBase graph, Integer head, Integer tail);

    abstract String mst(GrafoBase graph);

}
