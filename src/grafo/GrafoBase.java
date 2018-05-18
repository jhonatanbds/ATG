package grafo;

import java.util.*;
import java.util.stream.Collectors;

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
                Integer targetVertex = a.verticeAlvo(atual);
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

    public String BFS(Integer v) {
        Queue<Integer> fila = new LinkedList<>();
        Map<Integer, Boolean> visitado = new HashMap<>();
        Map<Integer, Integer> predecessor = new HashMap<>();
        Map<Integer, Integer> nivel = new HashMap<>();

        for(Integer vertex : this.vertices.keySet()) {
            visitado.put(vertex, false);
        }
        visitado.put(v, true);
        predecessor.put(v, null);
        nivel.put(v, 0);
        fila.add(v);
        while(!fila.isEmpty()){
            Integer atual = fila.poll();
            for(Integer adjacentVertex : getAdjacentVertexes(atual)){
                if(!visitado.get(adjacentVertex)){
                    visitado.put(adjacentVertex, true);
                    predecessor.put(adjacentVertex, atual);
                    nivel.put(adjacentVertex, nivel.get(atual) + 1);
                    fila.add(adjacentVertex);
                }
            }
        }
        String res = "";
        for(Integer vertice : visitado.keySet()) {
            res += (vertice.toString() + " - " + nivel.get(vertice).toString() + " ");
            if(predecessor.get(vertice) == null)
                res += ("-" + "\n");
            else
                res += predecessor.get(v).toString() + "\n";
        }
        return res;
    }

    public Set<Integer> getAdjacentVertexes(Integer v) {
        HashSet<Integer> l = new HashSet<>();
        for (Aresta a: vertices.get(v)) {
            l.add(a.verticeAlvo(v));
        }
        return l;
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
        int total = 0;
        for (Integer v : vertices.keySet())
            for (Aresta a : vertices.get(v))
                total++;
        return total;
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

    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < this.numVertices; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    void shortestPath(int src) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] graph = getAM(verticesTemp);

        int dist[] = new int[this.numVertices]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[this.numVertices];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < this.numVertices; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < this.numVertices-1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < this.numVertices; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = (int) (dist[u] + graph[u][v]);
        }

        // print the constructed distance array
        printSolution(dist, this.numVertices);
    }

    void printSolution(int dist[], int n)
    {
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < this.numVertices; i++)
            System.out.println(i+" tt "+dist[i]);
    }

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
