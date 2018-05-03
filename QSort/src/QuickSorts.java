import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;



public class QuickSorts {
    Integer valueArray[];

    public QuickSorts(){

    }

    public static Integer[] sort(Integer array[], String method){
        QuickSorts q = new QuickSorts();
        q.valueArray = array;
        int c=20000;
        switch (method) {
            case "quickSort":   q.quickSort(0, array.length-1);
                break;
            case "randSort":    q.randSort(0, array.length-1);
                break;
            case "medianSort":  q.medianSort(0, array.length-1);
                break;
            case "insertSort":  q.quickSortWithInsert(0, array.length-2, c);
                break;
            default: q.quickSort(0, array.length);
                break;
        }
        return q.valueArray;
    }

    static void insertSort(Integer valueArray[], int size) {
        int i;

        for (i = 1; i <= size; i++) {
            int key = valueArray[i];
            int j = i - 1;
            while (j >= 0 && valueArray[j] > key) {
                valueArray[j + 1]=valueArray[j];
                j--;
            }
            valueArray[j + 1]=(key);
        }
    }

    static int random(int min, int max) {
        int x = ThreadLocalRandom.current().nextInt(min, max + 1);
        return x;
    }

    void swap(Integer a, Integer b) {
       int index;
        index = valueArray[a];
        valueArray[a] = b;
        valueArray[b] = index;
    }

     int MedianOfThree(int left, int right) {
        int mid = (left + right) / 2;
        if (valueArray[right] < valueArray[left]) swap(left, right);
        if (valueArray[mid] < valueArray[left]) swap(mid, left);
        if (valueArray[right] < valueArray[mid]) swap(right, mid);
        return mid;
    }

     int partition(int l, int p) {

        int pivot = valueArray[p];
        int i = l - 1;
        int j;
        for (j = l; j <= p; j++) {
            if (valueArray[j] <= pivot) {
                i++;
                swap(i, j);

            }
        }
        //swap(valueArray[i + 1], valueArray[p]);
        return (i );
    }

     int randomPartition(int l, int p) {

        int q = random(l, p);
        swap(q,p);
        return partition(l, p);
    }

     int medianPartition(int l, int p) {

        int q = MedianOfThree(l, p);
        swap(q,p);
        return partition(l,p);

    }

     void quickSort(int l, int p) {

        int q;
        if (l < p) {
            q = partition(l, p);
            quickSort(l, q - 1);
            quickSort(q + 1, p);
        }
    }

     void randSort(int l, int p) {

        int q;
        if (l < p) {

            q = randomPartition(l, p);
            randSort(l, q - 1);
            randSort(q + 1, p);
        }

    }

     void medianSort(int l, int p) {
        int q;
        if (l < p) {
            q = medianPartition(l, p);
            medianSort(l, q-1);
            medianPartition(q + 1, p);
        }
    }

     void insertQuickSort(int l, int p, int c) {
        int q;
        if (l < p && (p - l) > c) {

            q = partition(l, p);

            insertQuickSort(l, q - 1, c);
            insertQuickSort( q + 1, p, c);
        }
        //DOROBI INSERT SORTA W TYM MIEJSCU
    }

     void quickSortWithInsert(int l, int p, int c) {
        insertQuickSort(l, p, c);

        insertSort(valueArray, p);
    }





}