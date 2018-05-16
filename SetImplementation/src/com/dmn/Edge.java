package com.dmn;

import java.util.Comparator;

public class Edge implements Comparator<Edge>, Comparable<Edge> {
    private Node verticle1;
    private Node verticle2;
    private int wage;


    public Node getVerticle1() {
        return verticle1;
    }
    public Node getVerticle2() {
        return verticle2;
    }

    public int getWage() {
        return wage;
    }

    public Edge(Node verticleIndex1Index, Node verticleIndex2Index, int wage){
        verticle1 = verticleIndex1Index;
        verticle2 = verticleIndex2Index;
        this.wage = wage;
    }

    public int compare(Edge e, Edge e2){
        return e.getWage()-e2.getWage();
    }

    public int compareTo(Edge e){
        return wage-e.getWage();
    }
}
