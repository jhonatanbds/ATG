package Graph;

import java.util.ArrayList;
import java.util.List;
import Graph.Vertice;
import Graph.Arestas;



public class Grafo {

   
	private List<Vertice> vertice = new ArrayList<Vertice>();
	private List<Arestas> arestas = new ArrayList<Arestas>();
	private boolean pesos;
	
	private float meanEdge; //grau medio
    private int edgeNumber; //numero de arestas
    private int vertexNumber;

    public Grafo() {

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
}
