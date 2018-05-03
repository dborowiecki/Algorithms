import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadWrite {
    public static FileReader createFileReader(String name) {
        FileReader fr;
        try {
            fr = new FileReader(name);

        } catch (FileNotFoundException e) {
            System.out.println("FILE READ ERROR");
            return null;
        }
        return fr;
    }
    public static List<Integer> intsFromFile(FileReader fr){

        List<Integer> l = new ArrayList<>();
        Scanner sc = new Scanner(fr);

        while((sc.hasNextInt())){
            l.add(new Integer(sc.nextInt()));
        }
        return l;
    }
    public static String writeInLines(Integer[] array){
        String s = new String();
        for(Integer i:array){
            s+=i+System.lineSeparator();
        }
        return s;
    }
    public static void writeToFile(String directory, String toWrite) {
        FileWriter fw;
        try {
            fw = new FileWriter(directory);
            fw.write(toWrite);
        } catch (IOException e) {
            System.out.println("Some error");
            return;
        }
    }

    public static FileWriter writeToFile(String fileName){
        FileWriter fw;
        try {
            fw = new FileWriter(fileName);
        }
        catch (IOException a){
            System.out.println("FILE NOT FOUND");
            return null;
        }
        return fw;
    }
}
