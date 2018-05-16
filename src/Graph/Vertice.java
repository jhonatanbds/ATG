package Graph;

public class Edge implements Comparable<Edge> {
	
	private int id;
	private boolean visitado = false;
    private Edge pai;
	private int distancia;
	
	public Edge(int id){
		this.id = id;
	}
	
	public Edge(int id, int peso){
		this.id = id;
		this.distancia = peso;
	}
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
    public Edge getPai() {
		return pai;
	}

	public void setPai(Edge pai) {
		this.pai = pai;
	}

	public void visitar (){    
        this.visitado = true;
    }
    
	public boolean verificarVisita() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public int compareTo(Edge Edge) {
        if (this.id < Edge.id) {
            return -1;
        }
        if (this.id > Edge.id) {
            return 1;
        }
        return 0;
    }
    
}
