package grafo;

import java.util.*;
import grafo.Prim;

public abstract class GrafoBase {

    protected Map<Integer, Set<Aresta>> vertices;
    private List<Aresta> arestas = new ArrayList<Aresta>();


    private float meanEdge;
    private int numVertices;

    public GrafoBase() {
        this.vertices = new HashMap<Integer, Set<Aresta>>();

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


    public Map<Integer, Set<Aresta>> getVertices() {
        return vertices;
    }

    public void setVertices(Map<Integer, Set<Aresta>> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public void setMeanEdge(float meanEdge) {
        this.meanEdge = meanEdge;
    }

    public void setVertexNumber(int vertexNumber) {
        this.numVertices = vertexNumber;
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

        for (double[] l : am)
            Arrays.fill(l, 0);

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

    public String BFS(int source) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] am = getAM(verticesTemp);
        Queue<Integer> fila = new LinkedList<>();
        int[] visitado = new int[numVertices + 1];
        int i, element;
        visitado[source] = 1;
        fila.add(source);
        String res = "";

        while (!fila.isEmpty()) {
            element = fila.remove();
            i = element;
            res += i + "\n";
            while (i <= numVertices) {
                if (am[element][i] != 0 && visitado[i] == 0) {
                    fila.add(i);
                    visitado[i] = 1;
                }
                i++;
            }
        }
        return res;
    }

    public boolean connected() {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] am = getAM(verticesTemp);
        Queue<Integer> fila = new LinkedList<>();
        int[] visitado = new int[numVertices + 1];
        int i, element;
        Integer source = vertices.keySet().iterator().next();
        visitado[source] = 1;
        fila.add(source);
        while (!fila.isEmpty()) {
            element = fila.remove();
            i = element;
            while (i <= numVertices) {
                if (am[element][i] != 0 && visitado[i] == 0) {
                    fila.add(i);
                    visitado[i] = 1;
                }
                if (am[element][i] != 0 && visitado[i] != 0) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public int getEdgeNumber() {
        ////////////////// TODO ///////////////////
        return 1;
    }

    public int getVertexNumber() {
        return vertices.size();
    }

    public abstract void addAresta(String aresta);

    public String DFS(Integer source) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] am = getAM(verticesTemp);
        Stack<Integer> pilha = new Stack<>();  // para o DFS usa-se umma pilha como auxiliar
        int[] visitado = new int[numVertices + 1];
        int i, element;
        visitado[source] = 1;
        pilha.push(source);
        String res = "";

        while (!pilha.isEmpty()) {
            element = pilha.peek(); // parte-se do topo da pilha
            i = 0;
            while (i <= numVertices) { // verifica-se se ha adjacentes nao-visitados
                if (am[element][i] != 0 && visitado[i] == 0) {
                    pilha.push(i);
                    visitado[i] = 1;
                    break; //segue-se para proximo vertice em profundidade
                }
                i++;
            }
            if (i == numVertices) // checou todos os possiveis vertices adjacentes e nao restou avanco em profundidade
            	res += pilha.pop() + "\n"; // remove da pilha e registra na saida
        }
        return res;
    }

    abstract boolean connected(GrafoBase graph);

    public String shortestPath(GrafoBase graph, Integer head, Integer tail){
    	return "";
    }


    public String mst(GrafoBase graph, String source) {
    	return Prim.arvoreGeradoraMinima (graph, source);
    }
    
       public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }
       
 
}
