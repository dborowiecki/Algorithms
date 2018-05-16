package com.dmn;

import java.util.ArrayList;

public class Graph {
    protected ArrayList<Node> verticles = new ArrayList<>();
    protected int lastVerticleIndex = 1;

    public int getLastVeticleIndex(){
        return lastVerticleIndex-1;
    }
    public void addVericle(){
        verticles.add(Node.makeNewSet(lastVerticleIndex));
        lastVerticleIndex++;
    }

    public void addEdge(int verticleIndex1, int verticleIndex2){
        getVerticle(verticleIndex1).union(getVerticle(verticleIndex2));
    }

    public boolean checkConnection(int verticleIndex1, int verticleIndex2){
        try {
            return getVerticle(verticleIndex1).findSet() == getVerticle(verticleIndex2).findSet();
        } catch (Exception e){
            System.err.println("Error "+e);

            return false;
        }
    }

    public Node getVerticle(int index){
        try {
            return verticles.get(index);
        } catch (IndexOutOfBoundsException e){
            System.err.println("Index "+index+" not found in graph");
        } catch (Exception e){
            System.err.println("Error occured: "+e);
        }
        return null;
    }
}
