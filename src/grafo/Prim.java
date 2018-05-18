package grafo;

import java.util.ArrayList;
import java.util.List;


public class Prim {
	public static List<String> vertices;
	int matAdj[][];
	private static List<Aresta> arestas;
	
public static String arvoreGeradoraMinima(GrafoBase graph, String origem) {
		
		if(graph.connected() == false)
			return "Não é possivel realizar operação!\nGrafo não é conexo.\n";
		

		String caminho = "Prim " +origem + ":\n";
		vertices = (List<String>) graph.getVertices();
		arestas = graph.getArestas();
		
		List<String> visitados = new ArrayList<String>();
		
		Aresta arestaAtual = new Aresta();
		String verticeAtual = origem;
		int custoTotal = 0;
		
		
		while (visitados.size() < vertices.size()){
			
			visitados.add(verticeAtual);
			arestaAtual= percorreArestas(visitados, verticeAtual);
			
			if(arestaAtual.getVertice2() == null)
				break;
			
			caminho += arestaAtual.getVertice1() + " " + arestaAtual.getVertice2() +" "+ arestaAtual.getPeso() + ",\n";  // FORMATAR A SAIDA AQUI
			
			custoTotal += arestaAtual.getPeso();
			
			verticeAtual = Integer.toString(arestaAtual.getVertice2());
		}
		
		caminho += custoTotal;
		
		return caminho + "\n";
	}


	private static Aresta percorreArestas(List<String> visitados, String verticeAtual) {
		
		Aresta arestaSelecionada = new Aresta();
		int menorCusto = Integer.MAX_VALUE;
		
		for (String visitado : visitados) {
			for (Aresta aresta : arestas) {
				if(Integer.toString(aresta.getVertice1()).equals(visitado)){
					if(!visitados.contains(Integer.toString(aresta.getVertice2()))){
						if(menorCusto > aresta.getPeso()){
							menorCusto = (int) aresta.getPeso();
							arestaSelecionada = aresta;
						}
					}		
				}	
			}
		}
		return arestaSelecionada;
	}
}