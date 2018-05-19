package com.dmn;

public class Node{

    private int key;
    private int rank;
    private Node parent;

    private Node(int value){
        key = value;
        rank = 0;
        this.parent = this;
    }

    public int getKey() {
        return key;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void incRank() {
        this.rank++;
    }

    public static Node makeNewSet(int value){
        return new Node(value);
       /* Node newNode = new Node();
        newNode.key = value;
        newNode.rank = 0;
        newNode.setParent(newNode);
        return newNode;*/
    }

    public Node makeSet(int value){
        return makeNewSet(value);
    }

    public Node findSet(){
        if(this!=getParent()){
            setParent(findSet(getParent()));
        }

        return getParent();
    }

    private Node findSet(Node n){
        if(n!=n.getParent()){
            setParent(findSet(n.getParent()));
        }

        return n.getParent();
    }

    public void union(Node y){
        Node x = this.findSet();
        if(x.getRank() > y.getRank()){
            y.setParent(this);
        }
        else{
            setParent(y);
            if(x.getRank()==y.getRank())
                y.incRank();
        }
    }
}
