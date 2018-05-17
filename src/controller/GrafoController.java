package controller;

import grafo.Grafo;
import grafo.GrafoPonderado;

import java.io.BufferedReader;
import java.io.FileReader;

public class GrafoController {

    Grafo readGrafo(String path) {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(fileReader);

            Integer vertices = Integer.parseInt(buffer.readLine());

            for (int i = 0; i < vertices ; i++) {
                String aresta = buffer.readLine();

            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    GrafoPonderado readWeightedGrafo(String path) {
        return null;
    }
}
