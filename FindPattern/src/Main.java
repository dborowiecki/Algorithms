import com.dmn.*;

public class Main {


    public static void main(String[] args) {
	    String p = ReadWrite.readFromFile("wzorzec1.txt");
	    String txt = ReadWrite.readFromFile("tekst2.txt");

        Test.runTestForAllMethods(p, txt);

	    /*test body
        start = System.nanoTime();
        numberofAppears = PatternFinder.KarpRabinSearch(p, txt);
        elapse = System.nanoTime() - start;
        printResault("Karp-Rabin", numberofAppears, elapse);
        */
    }


}
