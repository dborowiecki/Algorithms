import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFromFile{
    public static String readFromFile(String path){
        try {
            FileReader fR = new FileReader(path);

            BufferedReader bF = new BufferedReader(fR);
            String line;
            String allText = "";
            while((line = bF.readLine()) != null) {
                System.out.println(line);
                allText+=line;
            }
            bF.close();
            return allText;
        } catch (Exception e){ System.out.println("File not found"); }
        return null;
    }
}