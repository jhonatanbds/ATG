package grafo;

public class ArestaPonderada extends Aresta {
    private double peso;

    public ArestaPonderada(Vertice vertice1, Vertice vertice2, double peso) {
        super(vertice1, vertice2);
        this.peso = peso;
    }
}
