package grafo;

import java.util.Objects;

public class Vertice implements Comparable<Vertice> {
	
	private int number;

	public Vertice(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vertice vertice = (Vertice) o;
		return number == vertice.number;
	}

	@Override
	public int hashCode() {

		return Objects.hash(number);
	}

	public int compareTo(Vertice Vertice) {
		if (this.number < Vertice.number) {
			return -1;
		}
		if (this.number > Vertice.number) {
			return 1;
		}
		return 0;
	}
}
