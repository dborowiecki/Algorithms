import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Main {
    static Integer[] testArray, valueArray5k, valueArray10k, valueArray20k;
    int valueArraySize;
    static List<Integer> tempList;

    public static void main(String[] args) throws Exception {

        time qSortTime = new time();
        time randSortTime = new time();
        time medianSortTime = new time();
        time withInsertTime = new time();

        time qSortTimeD = new time();
        time randSortTimeD = new time();
        time medianSortTimeD = new time();
        time withInsertTimeD = new time();

        int i;
        int valueArraySize;
        FileReader in = ReadWrite.createFileReader("in.txt");
        FileReader big = ReadWrite.createFileReader("Rand5k.txt");
        FileWriter fw = ReadWrite.writeToFile("out.txt");


        tempList = ReadWrite.intsFromFile(in);
        testArray = new Integer[tempList.size()];
        testArray = tempList.toArray(testArray);
        valueArraySize = testArray.length;
        tempList.clear();

        System.out.println("UNSORTED: ");
        PrintArray.printArray(testArray);

        //Dla małej tablicy z zapisaniem do pliku

        //Wybrac sortowanie
        //quickSort(testArray, 0, valueArraySize-1);
        //randSort(testArray, 0, valueArraySize-1);
        // medianSort(testArray, 0, valueArraySize-1);
        //quickSortWithInsert(testArray, 0, valueArraySize-1, 9);

        String out = ReadWrite.writeInLines(testArray);
        fw.write(out);
        fw.close();
        System.out.println("SORTED: ");
        PrintArray.printArray(testArray);


        tempList = ReadWrite.intsFromFile(big);
        valueArray5k = new Integer[tempList.size()];
        valueArray5k = tempList.toArray(testArray);
        valueArraySize = testArray.length;

        //Dla 5tys elemntów
        qSortTime.FiveK = HowLong.sortDuration("quickSort", valueArray5k);
        valueArray5k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTime.FiveK += HowLong.sortDuration("randSort", valueArray5k);
            valueArray5k = tempList.toArray(testArray);
        }
        randSortTime.FiveK/=5;

        medianSortTime.FiveK = HowLong.sortDuration("medianSort", valueArray5k);
        valueArray5k = tempList.toArray(testArray);

        withInsertTime.FiveK = HowLong.sortDuration("insertSort", valueArray5k);

        //System.out.println("QSort: " + qSortTime.FiveK + " RanomQuickSort: " + randSortTime.FiveK + " MedianQuickSort:" + medianSortTime.FiveK + " InsertSort: " + withInsertTime.FiveK);
        //PrintArray.printArray(valueArray5k);
        tempList.clear();

        big.close();

        //Dla 10tys elemntów
        big = ReadWrite.createFileReader("Rand10k.txt");
        tempList = ReadWrite.intsFromFile(big);
        valueArray10k = new Integer[tempList.size()];
        valueArray10k = tempList.toArray(testArray);

        qSortTime.TenK = HowLong.sortDuration("quickSort", valueArray10k);
        valueArray10k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTime.TenK += HowLong.sortDuration("randSort", valueArray10k);
            valueArray10k = tempList.toArray(testArray);
        }
        randSortTime.TenK/=5;

        medianSortTime.TenK = HowLong.sortDuration("medianSort", valueArray10k);
        valueArray10k = tempList.toArray(testArray);

        withInsertTime.TenK = HowLong.sortDuration("insertSort", valueArray10k);

        // System.out.println("QSort: " + qSortTime.TenK + " RanomQuickSort: " + randSortTime.TenK + " MedianQuickSort:" + medianSortTime.TenK + " InsertSort: " + withInsertTime.TenK);
        // PrintArray.printArray(valueArray10k);
        tempList.clear();

        big.close();

        //Dla 20tys elemntów
        big = ReadWrite.createFileReader("Rand20k.txt");
        tempList = ReadWrite.intsFromFile(big);
        valueArray20k = new Integer[tempList.size()];
        valueArray20k = tempList.toArray(testArray);

        qSortTime.TwentyK = HowLong.sortDuration("quickSort", valueArray20k);
        valueArray20k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTime.TwentyK += HowLong.sortDuration("randSort", valueArray20k);
            valueArray20k = tempList.toArray(testArray);
        }
        randSortTime.TwentyK/=5;

        // medianSortTime.TwentyK=HowLong.sortDuration("medianSort", valueArray20k);
        //valueArray20k = tempList.toArray(testArray);

        withInsertTime.TwentyK=HowLong.sortDuration("insertSort", valueArray20k);

        //System.out.println("QSort: " + qSortTime.TwentyK + " RanomQuickSort: " + randSortTime.TwentyK + " MedianQuickSort:" + medianSortTime.TwentyK + " InsertSort: " + withInsertTime.TwentyK);
        //  PrintArray.printArray(valueArray20k);
        tempList.clear();


        //Dla 5tys elemntów desc

        big = ReadWrite.createFileReader("Desc5k.txt");
        tempList = ReadWrite.intsFromFile(big);
        valueArray20k = new Integer[tempList.size()];
        valueArray20k = tempList.toArray(testArray);

        qSortTimeD.FiveK = HowLong.sortDuration("quickSort", valueArray5k);
        valueArray5k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTimeD.FiveK += HowLong.sortDuration("randSort", valueArray5k);
            valueArray5k = tempList.toArray(testArray);
        }
        randSortTimeD.FiveK/=5;

        medianSortTimeD.FiveK = HowLong.sortDuration("medianSort", valueArray5k);
        valueArray5k = tempList.toArray(testArray);

        withInsertTimeD.FiveK = HowLong.sortDuration("insertSort", valueArray5k);

        //System.out.println("QSort: "+qSortTimeD.FiveK+" RanomQuickSort: "+randSortTimeD.FiveK+" MedianQuickSort:"+medianSortTimeD.FiveK+" InsertSort: "+withInsertTimeD.FiveK);
        // PrintArray.printArray(valueArray5k);
        tempList.clear();

        big.close();

        //Dla 10tys elemntów
        big = ReadWrite.createFileReader("Desc10k.txt");
        tempList = ReadWrite.intsFromFile(big);
        valueArray10k = new Integer[tempList.size()];
        valueArray10k = tempList.toArray(testArray);

        qSortTimeD.TenK = HowLong.sortDuration("quickSort", valueArray10k);
        valueArray10k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTimeD.TenK += HowLong.sortDuration("randSort", valueArray10k);
            valueArray10k = tempList.toArray(testArray);
        }
        randSortTimeD.TenK/=5;

        medianSortTimeD.TenK = HowLong.sortDuration("medianSort", valueArray10k);
        valueArray10k = tempList.toArray(testArray);

        withInsertTimeD.TenK = HowLong.sortDuration("insertSort", valueArray10k);

        // System.out.println("QSort: "+qSortTime.TenK+" RanomQuickSort: "+randSortTime.TenK+" MedianQuickSort:"+medianSortTime.TenK+" InsertSort: "+withInsertTime.TenK);
        // PrintArray.printArray(valueArray10k);
        tempList.clear();

        big.close();

        //Dla 20tys elemntów
        big = ReadWrite.createFileReader("Desc20k.txt");
        tempList = ReadWrite.intsFromFile(big);
        valueArray20k = new Integer[tempList.size()];
        valueArray20k = tempList.toArray(testArray);

        qSortTimeD.TwentyK = HowLong.sortDuration("quickSort", valueArray20k);
        valueArray20k = tempList.toArray(testArray);

        for(i=0; i<5; i++) {
            randSortTimeD.TwentyK += HowLong.sortDuration("randSort", valueArray20k);
            valueArray20k = tempList.toArray(testArray);
        }
        randSortTimeD.TwentyK/=5;

        medianSortTimeD.TwentyK = HowLong.sortDuration("medianSort", valueArray20k);
        valueArray20k = tempList.toArray(testArray);

        withInsertTimeD.TwentyK=HowLong.sortDuration("insertSort", valueArray20k);

        //System.out.println("QSort: "+qSortTimeD.TwentyK+" RanomQuickSort: "+randSortTimeD.TwentyK+" MedianQuickSort:"+medianSortTimeD.TwentyK+" InsertSort: "+withInsertTimeD.TwentyK);
        //PrintArray.printArray(valueArray20k);
        tempList.clear();
        WypiszDane("LOSOWA  ", qSortTime, randSortTime, medianSortTime, withInsertTime);
        WypiszDane("MALEJACA", qSortTimeD, randSortTimeD, medianSortTimeD, withInsertTimeD);
    }

    public static void WypiszDane(String jaka, time Q, time R, time M, time I) {
        System.out.println("DANE TABLICA | QUICKSORT   | RANDSORT    | MEDIANSORT |INSERTQUICKSORT|");
        System.out.format("%s   5k|% 13d|% 13d|% 13d|% 14d|\n",jaka, Q.FiveK, R.FiveK, M.FiveK, I.FiveK);
        System.out.format("%s  10k|% 13d|% 13d|% 13d|% 14d|\n",jaka, Q.TenK, R.TenK, M.TenK, I.TenK);
        System.out.format("%s  20k|% 13d|% 13d|% 13d|% 14d|\n",jaka, Q.TwentyK, R.TwentyK, M.TwentyK, I.TwentyK);
    }
}
