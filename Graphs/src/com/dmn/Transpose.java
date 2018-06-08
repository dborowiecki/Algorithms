package com.dmn;

import java.util.LinkedList;
import java.util.List;

public class Transpose {
    public static List<Verticle> transposed;
    public static List<Verticle> transposeGraph(Graph g){
        transposed = new LinkedList<>();
        List<Verticle> verticles = g.verticles;
        for(Verticle v: verticles){
            Verticle newV = new Verticle(v.getIndex());
            newV.setColor(v.getColor());
            transposed.add(newV);
        }
        for(Verticle v: verticles){
            List<Verticle> connected = v.connected;
            for(Verticle setConnection: connected){
                int transposedIndex = findInTransposed(setConnection);
                transposed.get(transposedIndex).addConnection(v);
            }
        }

        Graph transposedGraph = new Graph(transposed);
        transposedGraph.printConnections();
        return transposed;

    }
    public static int findInTransposed(Verticle v){
        for(Verticle t: transposed){
            if(t.getIndex()==v.getIndex()){
                return transposed.indexOf(t);
            }
        }
        return -1;
    }
}
