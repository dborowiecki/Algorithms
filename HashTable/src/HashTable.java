import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

class readFromFile{
    public static List readIntegers(String path){
        Scanner sc;
        try { sc = new Scanner(new File(path));}
        catch (Exception e){
            System.out.println("File not found");
            return null;
        }
        List elements=new ArrayList();
        while(sc.hasNextInt()){
            elements.add(sc.nextInt());
        }
        return elements;
    }
    public static List readWords(String path){
        Scanner sc;
        try { sc = new Scanner(new File(path));}
        catch (Exception e){
            System.out.println("File not found");
            return null;
        }
        List<String> elements = new ArrayList();
        while(sc.hasNextLine()){
            elements.add(sc.nextLine());
        }
        return elements;
    }


}
class HashTest{

    List<Integer> numbersList= new ArrayList();
    int zeros=0, average=0, max=0;
    int[] hashKeys;

    public int assignToNumber(String s, int len){
        if(len==0) return s.charAt(0)*111;
        else return assignToNumber(s, len-1)*111+s.charAt(len);
    }
    public List stringToInt(List<String> words){
        List<Integer> digits = new ArrayList();
        for(String word: words){digits.add(assignToNumber(word, word.length()-1));}
        return digits;
    }


    public void primeTest(){
        numbersList = readFromFile.readIntegers("liczbyPierwsze.txt");
        if(numbersList==null){System.err.println("Prime numbers error"); }
        int m=numbersList.size()/2;
        hashKeys = new int[m];
        for(Integer number: numbersList){
            hashKeys[HashTable.returnHashKey(number, "linear", m)]++;
        }
        for(int number: hashKeys){
            if(number==0) zeros++;
            else {
                if(number>max){
                    average+=max;
                    max=number;
                }
                else average+=number;
            }
        }

        System.out.println("\nDla liczb pierwszych: \n    " +
                "\n   Ilość zer:   "+zeros+
                "\n   Srednio:     "+average/(m-zeros-1)+
                "\n   Max wartosc: "+max);

    }
    public void stringTest(){
        average=0;
        max=0;
        zeros=0;
        numbersList = stringToInt(readFromFile.readWords("strings.txt"));
        if(numbersList==null){System.err.println("Prime numbers error"); }
        int m=numbersList.size()/2;
        hashKeys = new int[m];
        for(Integer number: numbersList){

            hashKeys[Math.abs(HashTable.returnHashKey(number, "linear", m))]++;
        }
        for(int number: hashKeys){
            if(number==0) zeros++;
            else {
                average+=number;
                if(number>max){
                    max=number;
                }
               // else average+=number;
            }
        }

        System.out.println("\nDla ciągu znaków: \n    " +
                "\n   Ilość zer:   "+zeros+
                "\n   Srednio:     "+(float)average/(m-zeros-1)+
                "\n   Max wartosc: "+max);

    }


    public void Test() {
        numbersList=null;
        primeTest();
        numbersList=null;
        stringTest();

    }


}




public class HashTable {

    static int m = 13; //array size
    int[] array = new int[m];

    private static int hashFunction1(int value){ return value%m; }
    private static int hashFunction1(int value, int m){ return value%m; }

    private static int hashFunction2(int value){ return 1+ (value % (m-2)); }
    private static int hashFunction2(int value, int m){ return 1+ (value % (m-2)); }

    private static int getHash(int  value, String method, int i){
        int h=hashFunction1(value);
        switch (method){
            case "linear": return (h+(i))%m;
            case "square": return (h+((i)*(i)))%m;
            case "doubleHash:": return (h+(i)*hashFunction2(value))%m;
            default: return ++i;
        }
    }

    public static int getHash(int  value, String method, int i, int m){
        int h=hashFunction1(value, m);
        switch (method){
            case "linear": return (h+(i))%m;
            case "square": return (h+((i)*(i)))%m;
            case "doubleHash:": return (h+(i)*hashFunction2(value, m))%m;
            default: return ++i;
        }
    }

    public static int returnHashKey(int value, String method, int m){
        int h;
        h=getHash(value, method, 0, m);
        return h;
    }
    void insertValue(int value, String method){
        int i=0;
        int h;
        System.out.println("Wstawianie liczby "+value);
        while(i<m){
            h=getHash(value, method, i++);
            if(array[h]<=0){
                array[h]=value;
                System.out.println("    "+value+" wstawiono na pozycji "+h);
                return;
            }
            System.out.println("    "+value+" próbowano wstawić na pozycji: "+h);
        }
        if(i==m) System.out.println("Nie udało się wstawić wartości, tablica zapełniona");
    }

    void insertValueLinear(int... values){
        for(int value: values) insertValue(value, "linear");
    }
    void insertValueSquare(int... values){
        for(int value: values) insertValue(value, "square");
    }
    void insertValueDoubleHash(int... values){
        for(int value: values) insertValue(value, "doubleHash");
    }

    void deleteElement(int value, String method){
        System.out.println("Deleting element");
        int i=searchForValue(value, method);
        if(i>=0) {
            try{
            array[i]=-1;
            System.out.println("Object was removed\n");
            } catch(IndexOutOfBoundsException e){
                System.out.println("Deletion failed \nError: "+e);
            }
        }
    }
    void deleteElementLinear(int value) { deleteElement(value, "linear");}
    void deleteElementSquare(int value) { deleteElement(value, "square");}
    void deleteElementDoubleHash(int value) { deleteElement(value, "doubleHash");}

    int searchForValue(int value, String method){
        int i=0;
        int h;
        while(i<m){
            h=getHash(value, method, i++);
            if(array[h]==0){
                System.out.println("Value "+value  +"wasn't found in array");
                return -1;
            }
            else{
                if(array[h]==value){
                    System.out.println("Element" + " "+value+" found on position "+h);
                    return h;
                }
            }
        }
        return -1;
    }
    void searchForValueLinear(int value) { searchForValue(value, "linear");}
    void searchForValueSquare(int value) { searchForValue(value, "square");}
    void searchForValueDoubleHash(int value) { searchForValue(value, "doubleHash");}

    public static void printArray(int[] array){
        System.out.println("TABLICA: ");
        int l=2;
        for(int x : array){
            System.out.print((x==0)? "-,":x+",");
        }
        System.out.println();
    }
    public static void printArray(Names[] array){
        System.out.println("Surnames array: ");
        int l=2;
        for(Names x : array){
            System.out.print((x==null)? "---,":x.number+" "+x.surname+",");
        }
        System.out.println();
    }
    void resetArray(){
        for(int i=0; i<m; i++){
            array[i]=0;
        }
    }



    public static void main(String[] args) {

        HashTable h = new HashTable();
        //6.1
        System.out.println("LINIOWO:");
        h.insertValueLinear(6, 19, 28, 41, 54);
        printArray(h.array);
        h.deleteElementLinear(41);
        h.searchForValueLinear(54);
        h.resetArray();

        System.out.println("\nKWADRATOWO:");
        h.insertValueSquare(6, 19, 28, 41, 54, 67);
        printArray(h.array);
        h.deleteElementSquare(54);
        h.searchForValueSquare(67);
        h.resetArray();

        System.out.println("\n PODWÓJNE HASHOWANIE");
        h.insertValueDoubleHash(50, 69, 72, 79, 98);
        h.insertValueDoubleHash(14);
        printArray(h.array);
        h.deleteElementDoubleHash(98);
        h.searchForValueDoubleHash(14);

        //6.2
        HashTest test = new HashTest();
        test.Test();

        //6.3
        //SPRAWDZANIE ILE SREDNIO POSZUKIWAN
        Names[] hashedNames;
        List<String> allNames = readFromFile.readWords("nazwiska.txt");
        hashedNames= new Names[allNames.size()];
        int average=0;
        int howMany=0;
        StringHash.m=allNames.size();

        checkAverage(0.5f, allNames);
        checkAverage(0.7f, allNames);
        checkAverage(0.9f, allNames);

        //SPRAWDZANIE WYSZUKIWAN
        for(String name: allNames){
            Names newName = new Names(name);
            average+=StringHash.insertValue(hashedNames, newName, "linear");
            howMany++;
            if(howMany>(allNames.size()*0.8)) break;
        }

        int k=0;
        average=0;
        for(k=0; k<60; k++){
            int l=LosujZakres(500, 5000);
            average+=StringHash.searchForValue(hashedNames, new Names(allNames.get(l)).surname, "linear");
        }
        System.out.println("Srednia dla szukania: "+(float)average/k);

        //USUANIE ELEMENTU
        for(k=0; k<0.4*hashedNames.length; k++){
            int l=LosujZakres(0, hashedNames.length);
            if(hashedNames[l]==null) k--;
            else StringHash.deleteElement(hashedNames, hashedNames[l].surname, "linear");
        }

        for(k=0; k<0.4*hashedNames.length; k++){
            int l=LosujZakres((int)(0.4*hashedNames.length), hashedNames.length);
                Names newName=new Names(allNames.get(l));
                StringHash.insertValue(hashedNames, newName, "linear");
        }
        int howManyDeleted=0;
        for(Names name: hashedNames){
            if(name==null)continue;
            if(name.surname.equals("DEL")) howManyDeleted++;
        }
        System.out.println("\"DEL\" w tablicy: "+howManyDeleted);
    }

    public static int LosujZakres(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max);
        return randomNum;

    }
    public static void checkAverage(float fillPercent, List<String> allNames, String method){
        Names[] hashedNames;
        int average=0;
        int av; int howMany;
        hashedNames=new Names[allNames.size()];
        av=0; howMany=0;
        StringHash.m=allNames.size();
        for(String name: allNames){
            Names newName = new Names(name);
            average+=StringHash.insertValue(hashedNames, newName, method);
            howMany++;
            if(howMany>(allNames.size()*fillPercent)) break;
        }
        System.out.println("Srednia dla "+(int)(fillPercent*100)+"% tablicy: "+String.format("%.2f",(float)average/howMany));
    }
    public static void checkAverage(float fillPercent, List<String> allNames){
        Names[] hashedNames;
        int average=0;
        int av; int howMany;
        hashedNames=new Names[allNames.size()];
        av=0; howMany=0;
        StringHash.m=allNames.size();
        for(String name: allNames){
            Names newName = new Names(name);
            average+=StringHash.insertValue(hashedNames, newName, "linear");
            howMany++;
            if(howMany>(allNames.size()*fillPercent)) break;
        }
        System.out.println("Srednia dla "+(int)(fillPercent*100)+"% tablicy: "+String.format("%.2f",(float)average/howMany));
    }
}
class StringHash{
    static int m = 7;

    private static int hashFunction1(int value){ return value%m; }
    private static int hashFunction1(int value, int m){ return value%m; }
    private static int hashFunction2(int value){ return 1+ (value % (m-2)); }
    private static int hashFunction2(int value, int m){ return 1+ (value % (m-2)); }


    public static int getHash(String  value, String method, int i){
        int h=findFindNumberForString(value, m);
        switch (method){
            case "linear": return (h+(i))%m;
            case "square": return (h+((i)*(i)))%m;
            case "doubleHash:": return (h+(i)*hashFunction2(value.length(), m))%m;
            default: return ++i;
        }
    }


    public static int insertValue(Names[] array, Names name, String method){
        int i=0;
        int h;
        while(i<m){

            h=getHash(name.surname, method, i++);

            if(array[h]==null || array[h].surname=="DEL"){
                array[h]=name;
                return i;
            }
        }
        if(i==m) System.out.println("Nie udało się wstawić wartości, tablica zapełniona");
        return i;
    }
    public static int findFindNumberForString(String s, int m){
        int hash = 3;
        int strlen=s.length();
        for (int i = 0; i < strlen; i++) {
             hash = (hash*31 + s.charAt(i))%m;
        }
        return hash;
    }

    public static int searchForValue(Names[] array, String nazwisko, String method){
        int i=0;
        int h;
        while(i<m){
            h=getHash(nazwisko, method, i++);
            if(array[h]==null){
                System.out.println("Nie znaleziono "+nazwisko  +" w tablicy");
                return 0;
            }
            else{
                if(array[h].surname.equals(nazwisko)){
                    return i;
                }
            }
        }
        return i;
    }
    public static Names searchForDelete(Names[] array, String nazwisko, String method){
        int i=0;
        int h;
        while(i<m){
            h=getHash(nazwisko, method, i++);
            if(array[h]==null){
                return null;
            }
            else{
                if(array[h].surname.equals(nazwisko)){
                    return array[h];
                }
            }
        }
        return null;
    }
    public static void deleteElement(Names[] array, String nazwisko, String method){
        Names i=searchForDelete(array, nazwisko, method);
        if(i!=null) {
            try{
                i.surname="DEL";
            } catch(IndexOutOfBoundsException e){
                System.out.println("Nie udało się usunąć elementu");
            }
        }
    }
}


class Names{
    String surname = new String();
    String number = new String();

    public Names(String name){
        insertName(name);
    }
    public  void insertName(String newName){
        int i=0;
        char c=newName.charAt(i);
        while (c!=' '){
            i++;
            c=newName.charAt(i);
        }
        if(i>newName.length()) System.out.println("Błędna struktura nazwiska");
        else{
            surname= newName.substring(i+1);
            number= newName.substring(0, i);
        }

    }
}

