import com.dmn.*;

import java.util.LinkedList;
import java.util.List;

import static com.dmn.Graph.testBreadthFirstSearch;

public class Main {

    public static void testSearching(List<Verticle> vertsFromFile){
    }
    public static void main(String[] args) {
        List<Verticle> vertsFromFile = ReadWrite.readVerticlesFile(("graph1.txt"));
        List<Verticle> vertsFromFile2 = ReadWrite.readVerticlesFile("graph1.txt");
        Graph g1 = new Graph("graph1.txt");
        Graph g2 = new Graph("graph2.txt");
        g1.saveAsNeighbourGroup("firstGraphOut.txt");
        g1.strongConnect();

        g2.saveAsNeighbourGroup("secondGraphOut.txt");
        g2.strongConnect();

    }


}
