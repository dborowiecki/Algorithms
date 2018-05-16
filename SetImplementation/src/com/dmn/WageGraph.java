package com.dmn;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WageGraph extends Graph {

    private List<Edge> edges = new LinkedList<>();

    private void addEdge(Node verticleIndex1, Node verticleIndex2, int wage){
        edges.add(new Edge(verticleIndex1, verticleIndex2, wage));
    }

    public void addEdge(int verticleIndex1, int verticleIndex2, int wage){
        addEdge(getVerticle(verticleIndex1), getVerticle(verticleIndex2), wage);
    }

    public void printEdges(){
        for (Edge e: edges){
            int verticleIndex1Index = verticles.indexOf(e.getVerticle1());
            int verticleIndex2Index = verticles.indexOf(e.getVerticle2());
            System.out.println("Verticle1: "+verticleIndex1Index+
                                " Verticle2: "+verticleIndex2Index+
                                    " Wage: "+e.getWage());
        }
    }

    public static WageGraph kruskal(WageGraph g){

        WageGraph consistent = new WageGraph();
        consistent.verticles = g.verticles;
        Collections.sort(g.edges);

        for(Edge e: g.edges) {
          //  System.out.println(e+", ");
            Node verticleIndex1 = e.getVerticle1();
            Node verticleIndex2 = e.getVerticle2();
            Node representVert1 = verticleIndex1.findSet();
            Node representVert2 = verticleIndex2.findSet();
            if(representVert1!=representVert2){
               consistent.addEdge(verticleIndex1, verticleIndex2, e.getWage());
               verticleIndex1.union(verticleIndex2);
            }
        }
        return consistent;
    }
}
