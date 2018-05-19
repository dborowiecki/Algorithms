package com.dmn;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WageGraph extends Graph {

    private List<Edge> edges = new LinkedList<>();

    private void addEdge(Node v1, Node v2, int wage){
        edges.add(new Edge(v1, v2, wage));
    }

    public void addEdge(int v1, int v2, int wage){
        addEdge(getVerticle(v1), getVerticle(v2), wage);
    }

    public void printEdges(){
        for (Edge e: edges){
            int v1Index = verticles.indexOf(e.getVerticle1());
            int v2Index = verticles.indexOf(e.getVerticle2());
            System.out.println("Verticle1: "+v1Index+
                                " Verticle2: "+v2Index+
                                    " Wage: "+e.getWage());
        }
    }

    public static WageGraph kruskal(WageGraph g){

        WageGraph consistent = new WageGraph();
        consistent.verticles = g.verticles;
        Collections.sort(g.edges);

        for(Edge e: g.edges) {
            Node v1 = e.getVerticle1();
            Node v2 = e.getVerticle2();
            Node representVert1 = v1.findSet();
            Node representVert2 = v2.findSet();
            if(representVert1.getKey()!=representVert2.getKey()){
               consistent.addEdge(v1, v2, e.getWage());
               v1.union(v2);
            }
        }
        return consistent;
    }
}
