package com.dmn;

import java.util.LinkedList;
import java.util.List;

public class VerticleTree {
    Verticle root;
    List<Verticle> sons;

    public VerticleTree(Verticle root){
        this.root = root;
        sons = new LinkedList<>();
    }
    public void addSon(Verticle son){
        this.sons.add(son);
    }


    public void printTree(){
        System.out.println(root);
    }
}
