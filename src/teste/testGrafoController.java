package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import controller.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class testGrafoController {

    private  GrafoController controle;
    private final String grafo1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; // connected graph
    private final String grafo2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; // disconnected graph

    @Before
    public void criandoGrafo() {
        List<String> linhas = Arrays.asList("5", "1 2", "2 5", "5 3", "4 5", "1 5");
        Path arquivo = Paths.get(grafo1);
        try {
            Files.write(arquivo, linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareSimpleDisconnectedGraphFile() {
        List<String> linhas = Arrays.asList("10", "1 2", "2 5", "5 3", "4 5", "1 5");
        Path arquivo = Paths.get(grafo2);
        try {
            Files.write(arquivo, linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void createController() {

        controle = new GrafoController();
    }

    @Test
    public void testCreateSimpleGraphFromFile () {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGets() {
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(5, controle.getVertexNumber(controle.getGraph()));
        Assert.assertEquals(5, controle.getEdgeNumber(controle.getGraph()));
        Assert.assertEquals(2.0, controle.getMeanEdge(controle.getGraph()), 0.0);
    }

    @Test
    public void testGetsfails() {
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertNotEquals(2, controle.getVertexNumber(controle.getGraph()));
        Assert.assertNotEquals(6, controle.getEdgeNumber(controle.getGraph()));
        Assert.assertNotEquals(1.0, controle.getMeanEdge(controle.getGraph()), 0.0);
    }

    //implementa ainda
    @Test
    public void testNotConnection() {
        controle = new GrafoController();
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(controle.connected(controle.getGraph()));
    }


        @Test
    public void testConnection() {
        controle = new GrafoController();
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(controle.connected(controle.getGraph()));

        /*
        controller = new Controller();
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertFalse(controller.connected(controller.getGraph()));
        * outro */
    }



//exemplo 1 de saida 1 AM e AL
    @Test
    public void testRepresentaçao() {
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        String expected = "1 - 2 5" + System.getProperty("line.separator") +
                "2 - 1 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1 2 3 4";

        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   1   0   0   1" + System.getProperty("line.separator") +
                        "2   1   0   0   0   1" + System.getProperty("line.separator") +
                        "3   0   0   0   0   1" + System.getProperty("line.separator") +
                        "4   0   0   0   0   1" + System.getProperty("line.separator") +
                        "5   1   1   1   1   0" + System.getProperty("line.separator");


        try {
            Assert.assertEquals(expectedAM, controle.graphRepresentation(controle.getGraph(), "AM"));
            Assert.assertEquals(expected, controle.graphRepresentation(controle.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRepresentaçaoErrada() {
        try {
            controle.readGraph(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        String expected = "1 - 2 5" + System.getProperty("line.separator") +
                "2 - 1 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1 2 3 4";

        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   0   0   0   0" + System.getProperty("line.separator") +
                        "2   0   0   0   0   0" + System.getProperty("line.separator") +
                        "3   0   0   0   0   0" + System.getProperty("line.separator") +
                        "4   0   0   0   0   0" + System.getProperty("line.separator") +
                        "5   0   0   0   0   0" + System.getProperty("line.separator");


        try {
            Assert.assertNotEquals(expectedAM, controle.graphRepresentation(controle.getGraph(), "AM"));
            Assert.assertNotEquals(expected, controle.graphRepresentation(controle.getGraph(), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }
}