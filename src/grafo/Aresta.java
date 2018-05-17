package grafo;

public class Aresta {
    private Vertice vertice1;
    private Vertice vertice2;
    private double peso;

    public Aresta(Vertice vertice1, Vertice vertice2, double peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Aresta(Vertice vertice1, Vertice vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = 1;
    }

    public Vertice getVertice1() {
        return vertice1;
    }

    public Vertice getVertice2() {
        return vertice2;
    }

    public double getPeso() {
        return peso;
    }
}
