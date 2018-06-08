package com.dmn;

import java.util.LinkedList;
import java.util.List;

public class Verticle {

    private int index;
    List<Verticle> connected = new LinkedList<>();
    int visitTime = 0;
    int transformTime = 0;
    int i = 0;
    int lowlink = 0;
    int color; //1 for white, 0 for gray
    List<Verticle> sons;
    Verticle pi; //Verticle from which we came to this verticle
    public Verticle(int index){
        this.index = index;
        sons = new LinkedList<>();
    }
    public int getIndex() {
        return index;
    }

    public void addConnection(Verticle v){
        connected.add(v);
    }
    public List<Verticle> getConnected(){
        return connected;
    }
    public void setColor(int newColor){
        this.color = newColor;
    }
    public int getColor(){
        return color;
    }
    public Verticle getParent(){return pi;}

    public void printSonsInorder(){
        for(Verticle son: sons){
            son.printSonsInorder();
            System.out.print(son.index+", ");
        }
    }
    public int getConnectedIndexByValue(int value){
        for(Verticle v: connected){
            if(v.getIndex()==value){
                return connected.indexOf(v);
            }
        }
        return -1;
    }
}
