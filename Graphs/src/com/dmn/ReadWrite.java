package com.dmn;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadWrite {
    public static  List<Verticle> readVerticlesFile(String fileName){
        List<Verticle> verticleList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            Integer numberOfVerts = 0;
            String line;
            line =  br.readLine();
            System.out.println(line);
            numberOfVerts = Integer.parseInt(line);
            //pusta lista wierzchołków
            for(int i=0; i<numberOfVerts; i++){
                verticleList.add(new Verticle(i+1));
            }

            int index = 0;
            //wypełnienie
            while((line =  br.readLine())!=null){
                List<Integer> intsInLine = getIntsFromString(line);
                int iterator = 0;
                for(Integer connection: intsInLine){
                    if(connection == 1) verticleList.get(index)
                                                    .addConnection(verticleList.get(iterator));
                    iterator++;
                }
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
        return verticleList;
    }

    public static  List<Verticle> readTransponedVerticlesFile(String fileName){
        List<Verticle> verticleList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            Integer numberOfVerts = 0;
            String line;
            line =  br.readLine();
            System.out.println(line);
            numberOfVerts = Integer.parseInt(line);
            //pusta lista wierzchołków
            for(int i=0; i<numberOfVerts; i++){
                verticleList.add(new Verticle(i+1));
            }

            int index = 0;
            //wypełnienie
            while((line =  br.readLine())!=null){
                List<Integer> intsInLine = getIntsFromString(line);
                int iterator = 0;
                for(Integer connection: intsInLine){
                    if(connection == 1) verticleList.get(iterator)
                            .addConnection(verticleList.get(index));
                    iterator++;
                }
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::println);
        return verticleList;
    }

    public  static List<Integer> getIntsFromString(String str){
        List<Integer> listOfInts = new LinkedList<>();
        str = str.replaceAll("[^-?0-9]+", " ");
        List<String> asString = Arrays.asList(str.trim().split(" "));
        for(String s: asString){
            listOfInts.add(Integer.parseInt(s));
        }
        return listOfInts;
    }

    public static void writeToFile(String filename, String content)throws FileNotFoundException{
            PrintWriter writer = new PrintWriter(filename);
            writer.println(content);
            writer.close();

    }

}
