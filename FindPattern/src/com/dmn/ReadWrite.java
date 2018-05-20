package com.dmn;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadWrite {

    public static String readFromFile(String filePath){
        try {
            Scanner scanner =  new Scanner(Paths.get(filePath));
            StringBuilder s = new StringBuilder();

            while (scanner.hasNextLine()){
                s.append(scanner.nextLine());
            }

            return s.toString();
        }
        catch (IOException e){
            System.out.println("File "+filePath+" not found");
        }

        return null;
    }
}
