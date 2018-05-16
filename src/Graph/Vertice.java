package Graph;

public class Vertice implements Comparable<Vertice> {
	
	private int id;
	private boolean visitado = false;
    private Vertice pai;
	private int distancia;
	
	public Vertice(int id){
		this.id = id;
	}
	
	public Vertice(int id, int peso){
		this.id = id;
		this.distancia = peso;
	}
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
    public Vertice getPai() {
		return pai;
	}

	public void setPai(Vertice pai) {
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
	
    public int compareTo(Vertice Vertice) {
        if (this.id < Vertice.id) {
            return -1;
        }
        if (this.id > Vertice.id) {
            return 1;
        }
        return 0;
    }
    
}
