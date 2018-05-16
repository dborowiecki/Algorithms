 import com.dmn.Graph;
 import com.dmn.Node;
 import com.dmn.WageGraph;

 import java.util.LinkedList;
 import java.util.Random;
 import java.util.Set;

 public class Main {

     static public LinkedList randomNodeList(int len){
         LinkedList<Node> l = new LinkedList<>();
         Random rand = new Random();
         int i = 0;
         while(++i<len){
             Node n = Node.makeNewSet(i);
             l.add(n);
         }

         return l;
     }
     public static void graphConnectTest(Graph graph,int verticleIndex1, int verticleIndex2){
         if (graph.checkConnection(verticleIndex1, verticleIndex2)){
             System.out.println("Verticle "+verticleIndex1 + " and "+verticleIndex2+" are connected");
         }
         else
             System.out.println("Verticle "+verticleIndex1 + " and "+verticleIndex2+" are not connected");
     }
    public static void main(String[] args) {
         //TEST
        LinkedList<Node> nodeList = randomNodeList(50);
        //Set implementation
        Node n1 = nodeList.get(0);
        Node n2 = nodeList.get(1);
        Node n3 = nodeList.get(6);
        Node n4 = nodeList.get(5);
        Node n5 = nodeList.get(30);
        System.out.println("Węzęł 1: "+ n1.findSet().getKey()+ " Reprezentant 1: " + n1);
        System.out.println("Węzęł 2: "+ n2.findSet().getKey()+ " Reprezentant 2: " + n2);
        System.out.println("Węzęł 3: "+ n3.findSet().getKey()+ " Reprezentant 3: " + n3);
        System.out.println("Węzęł 4: "+ n4.findSet().getKey()+ " Reprezentant 3: " + n4);
        n1.union(n2.findSet());
        System.out.println("\nPo union(n1, n2) reprezentant węzła n1: "+n1.findSet().getKey());
        n1.union(n3.findSet());
        System.out.println("\nPo union(n1, n3) reprezentant węzła n3: "+n3.findSet().getKey());
        n4.union(n5.findSet());
        System.out.println("\nPo union(n4, n5) reprezentant węzła n4: "+n4.findSet().getKey());
        n3.union(n4.findSet());
        System.out.println("\nPo union(n3, n4) reprezentant węzła n3: "+n3.findSet().getKey());

        //Graf
        Graph g = new Graph();
        for(int i=0; i<6;i++) g.addVericle();
        g.addEdge(1, 2);
        g.addEdge(3, 1);
        g.addEdge(5, 4);
        graphConnectTest(g, 1, 3);
        graphConnectTest(g, 1, 5);
        graphConnectTest(g, 6, 2);

        //Graf spójny
        WageGraph s = new WageGraph();
        for(int i=0; i<6;i++) s.addVericle();
        s.addEdge(1, 2, 4);
        s.addEdge(2, 3, 6);
        s.addEdge(2, 4, 1);
        s.addEdge(1, 5, 2);
        s.addEdge(3, 4, 8);
        s.addEdge(3, 5, 5);
        System.out.println("Przed kuskalem: ");
        s.printEdges();
        s = WageGraph.kruskal(s);
        System.out.println("Po kruskalu: ");
        s.printEdges();
    }
}
