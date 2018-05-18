package teste;

import org.junit.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import grafo.*;

public class testGrafo {
    Grafo grafo = new Grafo(2);

    @Test
    public void criandovertice () {

        try {
            grafo.createVertex(1);
            grafo.createVertex(1);
        } catch (Exception e) {
            String message = "vertice ja existe.";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    @Test
    public void connectSimpleVertex1 () {
        try {
            String vertices = "a b c";
            grafo.connectSimpleVertex(vertices);
        } catch (Exception e) {
            String message = "argumentos invalidos";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    @Test
    public void connectSimpleVertex2 () {
        try {
            String vertices = "1 2 3 4";
            grafo.connectSimpleVertex(vertices);
        } catch (Exception e) {
            String message = "argumentos excederam o limite!";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    @Test
    public void connectSimpleVertex3 () {
        try {
            String vertices = "";
            grafo.connectSimpleVertex(vertices);
        } catch (Exception e) {
            String message = "argumentos inexistente";
            Assert.assertEquals(true, e.getMessage().equals(message));
        }
    }

    /*@Test
    public void connectSimpleVertex4 () {
        Edge edgeOut = new Edge(1,2);
        Edge edgeIn = new Edge(2,1);
        try {
            String vertices = "1 2";
            grafo.connectSimpleVertex(vertices);
            HashSet<Edge> edgesOut = grafo.getEdges(1);
            HashSet<Edge> edgesIn = grafo.getEdges(2);

            for ( Iterator<Edge> i = edgesOut.iterator(); i.hasNext();) {
                Edge e = i.next();
                Assert.assertEquals(true, e.equals(edgeOut));
            }

            for ( Iterator<Edge> i = edgesIn.iterator(); i.hasNext();) {
                Edge e = i.next();
                Assert.assertEquals(true, e.equals(edgeIn));
            }
        } catch (Exception e) {

        }
    }*/


}