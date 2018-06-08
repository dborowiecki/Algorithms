package com.dmn;

import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.min;

public class Graph {
    public static int DEPTH_LEVEL = 1;
    public List<Verticle> verticles = new LinkedList<>();
    private Map<Verticle, List<Verticle>> neighbourList;
    private String filename;

    public Graph(List<Verticle> verticles){
        this.verticles = verticles;
        neighbourList = new HashMap<>();
        createNighbourList();
    }
    public Graph(String filename){
        this.filename = filename;
        List<Verticle> vertsFromFile = readFromMatrix();
        this.verticles = vertsFromFile;
        neighbourList = new HashMap<>();
        createNighbourList();
    }
    private void createNighbourList(){
        for(Verticle v: verticles){
            List<Verticle> neighbours = new LinkedList<>();
            for(Verticle u: v.getConnected()) {
                neighbours.add(u);
            }
            neighbourList.put(v, neighbours);
        }
    }

    private List<Verticle> readFromMatrix(){
        List<Verticle> verts = ReadWrite.readVerticlesFile(filename);
        return verts;
    }

    public void printConnections(){
        for(Verticle v: verticles){
            System.out.print(v.getIndex() +": ");
            for(Verticle u: v.getConnected()) {
                System.out.print(u.getIndex()+", ");
            }
            System.out.println();
        }
    }

    public static int t = 0;
    public static List<StronglyConnected> strongConnectionAll = new LinkedList<>();

    public List<Verticle> depthFirstSearch(){
        DEPTH_LEVEL = 1;
        List<Verticle> strongConnection = new ArrayList<>();
        for(Verticle u: verticles) {
            u.setColor(1);
            u.pi = null;
        }
        System.out.println("DEPTH GRAPH SEARCH START");
        List<Verticle> lowestVerticles= new LinkedList<>();
        for(Verticle u: verticles){

            if(u.getColor()==1){
                System.out.print("!Visited from "+u.getIndex()+":\n");
                depthFirstSearch(u);
                lowestVerticles.add(u);
            }
        }
        System.out.println("DEPTH GRAPH SEARCH END\n-----");
        return lowestVerticles;
    }
    private void depthFirstSearch(Verticle u){
        u.i = t;
        u.lowlink = t;
        t++;

        u.setColor(0);
        for(Verticle w: u.getConnected()){
            if(w.getColor()==1){
                w.pi = u;
              addDepth();System.out.print("*Visited from "+w.getIndex()+":\n");
              DEPTH_LEVEL++;
            depthFirstSearch(w);
              addDepth();System.out.print("---\n");
              DEPTH_LEVEL--;
            }
            // t++;
            u.transformTime = t;
        }
    }
    private void addDepth(){
        printTabulations(DEPTH_LEVEL);
    }
    private void printTabulations(int numberofTabulations){

        for(int i=0;i<numberofTabulations;i++){
            System.out.print("   ");
        }
    }

    public static void testBreadthFirstSearch(Graph g){
            System.out.println("BREADTH GRAPH SEARCH START");
        List<Verticle> connectedBuild = new LinkedList<>();
            Graph temp = g;
            temp.breadthFirstSearch();
            System.out.println("BREADTH GRAPH SEARCH END");
    }
    public  void breadthFirstSearch(){
        DEPTH_LEVEL = 0;
        for(Verticle s: verticles){
            s.color = 1;
            s.pi = null;
        }
        for(Verticle s: verticles){
            if(s.color==1){
                System.out.print("BREADTH 0: "+s.getIndex()+"\n");
                breadthFirstSearch(s);
            }
        }
    }
    private static void breadthFirstSearch(Verticle s){
        HashMap<Integer, List<Verticle>> breadthVerts = new HashMap<>();
        s.setColor(0);
        List<Verticle> connected = s.connected;
        while(!connected.isEmpty()) {
            Verticle u = connected.remove(0);
            for(Verticle v: u.connected){
                if(v.color==1){
                    System.out.println("    Breadth: "+u);
                    v.color = 0;
                    v.pi = u;
                    connected.add(v);
                }
            }
        }
    }


    public static int time = 0;
    public void replaceConnections(List<Verticle> newConnections){
        for(Verticle v: verticles){
            int index = v.getIndex();
            for(Verticle u: newConnections) {
                if(u.getIndex()==v.getIndex()){
                    v.connected = u.connected;
                }
            }
        }

    }
    public void strongConnect(){
        Stack<Verticle> S = new Stack();
       for(Verticle v: verticles){
           v.setColor(1);
       }
       for(Verticle v: verticles){
           if(v.getColor()==1){
               DFSStack(v, S);
           }
       }
       List<Verticle> newConnections =  Transpose.transposeGraph(this);
       replaceConnections(newConnections);
       for(Verticle v: verticles) v.setColor(1);
       int cn = 0;

       while(!S.isEmpty()){
           Verticle v = S.pop();

           if(v.getColor()==0) continue;
       //    System.out.println("HALO");
           cn++;
           System.out.println("SCC "+cn+": ");
           DFSprint(v);
       }
    }

    public void DFSStack(Verticle start, Stack S){
       start.color = 0;
       for(Verticle connect: start.connected){
           if(connect.getColor()==1){
               DFSStack(connect, S);
           }
       }
        S.push(start);
        //  System.out.println("STACK SIZE "+S.size());
    }

    public void DFSprint(Verticle v){
        v.setColor(0);
        System.out.println(v.getIndex());
        for(Verticle u: v.connected){
            if(u.getColor()==1) DFSprint(u);
        }
    }

    public void saveAsNeighbourGroup(String filename){
        StringBuilder txt = new StringBuilder();
        for(Verticle k: neighbourList.keySet()){
            txt.append(k.getIndex()+": ");
            for(Verticle c: neighbourList.get(k)){
                txt.append(c.getIndex()+" ");
            }
            txt.append("\n");
        }
        try{
            ReadWrite.writeToFile(filename, txt.toString());
        }
        catch (Exception e){
            System.err.println("ERROR WITH NEIGHBOUR LIST IMPORT");
        }
    }

}
