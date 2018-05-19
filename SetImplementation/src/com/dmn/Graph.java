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

    public void addEdge(int v1, int v2){
        getVerticle(v1).union(getVerticle(v2));
    }

    public boolean checkConnection(int v1, int v2){
        try {
            return getVerticle(v1).findSet() == getVerticle(v2).findSet();
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
