package com.dmn;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.pow;

public class PatternFinder {

    public static List<Integer> naiveSearch(String pattern, String text) {
        List<Integer> numberOfPatternFinds = new LinkedList<>();
        char[] textArray = text.toCharArray();
        try {
            for (int i = 0; i < textArray.length-1; i++) {
                int j = 0;
                for (char patternLetter : pattern.toCharArray()) {
                    if (textArray[i + j] != patternLetter) break;
                    j++;
                }
                if (j == pattern.length()) numberOfPatternFinds.add(i);
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Array end reached");
        }
        return numberOfPatternFinds;
    }

    public static List<Integer> karpRabinSearch(String pattern, String text){
        int patterLen = pattern.length();
        long patternHash = hash(pattern, patterLen);
        long textHash = hash(text, patterLen);
        int i = 0;
        List<Integer> foundPatterns = new LinkedList<>();

        while(textHash!=-1){
            if(patternHash == textHash){
                if(pattern.compareTo(text.substring(i, i+patterLen))==0){
                    foundPatterns.add(i);
                }
            }
            i++;
            if(i-1 == text.length()-patterLen)
                break;

            textHash = (textHash - (text.charAt(i - 1) - 65)) +text.charAt(i+patterLen-1)-65;
        }
        return foundPatterns;
    }

    private static long hash(String str, int len){
        try {
            long hash = 0;

            for (int i = 0; i < len; i++) {
                hash = hash + (str.charAt(i)-65);
            }
            return hash;
        }
        catch(StringIndexOutOfBoundsException e){
            return -1;
        }
    }

    public static List<Integer> KMPSearch(String pattern, String text){
        List<Integer> patternIndexes= new LinkedList<>();
        pattern = "\n"+pattern;
        text = "\n"+text;
        int[] pi = prefixArray(pattern);
        int q=0;
        int numberOfPatterns = 0;

        for(int i=1; i<text.length()-1;i++){
            while(q>0 && pattern.charAt(q+1)!= text.charAt(i)){
                q=pi[q];
            }
            if(pattern.charAt(q+1)==text.charAt(i)){
                q++;
            }
            if(q==pattern.length()-1){
                patternIndexes.add(i-pattern.length()+1);
                numberOfPatterns++;
                q=pi[q];
            }
        }
        return patternIndexes;
    }

    private static int[] prefixArray(String pattern){
        int[] KMP = new int[pattern.length()];
        KMP[1]=0;
        int k=0;

        for(int i=2;i<pattern.length();i++){
            while(k>0 && pattern.charAt(k+1)!=pattern.charAt(i)){
                k=KMP[k];
            }
            if(pattern.charAt(k+1)==pattern.charAt(i)){
                k++;
            }
            KMP[i]=k;
        }

        return KMP;
    }
}
