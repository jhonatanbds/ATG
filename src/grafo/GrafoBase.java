package grafo;

import java.util.*;

public abstract class GrafoBase {

    protected Map<Integer, Set<Aresta>> vertices;

    private float meanEdge;
    private int numVertices;

    public GrafoBase(Map<Integer, Set<Aresta>> vertices) {
        this.vertices = vertices;
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
        double am[][] = new double[numVertices][numVertices];

        for(int i = 0; i < numVertices; i++) {
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
        String amString = " ";

        for (int i = 0; i < this.numVertices; i++) {
            amString += orderedVertexes.get(i);
            if (this.numVertices - i > 1)
                amString += " ";
        }
        amString += "\n";

        for(int i = 0; i < this.numVertices; i++) {
            String linha = orderedVertexes.get(i) + " ";
            for(int j = 0; j < this.numVertices; j++) {
                linha += Double.toString(adjacencyMatrix[i][j]);
                if (this.numVertices - j > 1)
                    linha += " ";
            }
            amString += linha + "\n";
        }
        return amString;
    }

    public float getMeanEdge() {
        float numVertices = getVertexNumber();
        if (numVertices > 0) {
        	meanEdge = getEdgeNumber() / getVertexNumber();
        }else {
        	meanEdge = 0;
        }
        return meanEdge;
    }

    public int getEdgeNumber() {
        ////////////////// TODO ///////////////////
        return 1;
    }

    public int getVertexNumber() {
        return vertices.size();
    }

    abstract String BFS(GrafoBase graph, Integer vertex);

    abstract String DFS(GrafoBase graph, Integer vertex);

    abstract boolean connected(GrafoBase graph);

    abstract String shortestPath(GrafoBase graph, Integer head, Integer tail);

    abstract String mst(GrafoBase graph);

    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
}
