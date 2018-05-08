import java.util.LinkedList;
import java.util.List;


public class LongestSubsequence {
    String s1 = " ";
    String s2 = " ";
    int stringArray[][];

    private int max(int v1, int v2){
        return v1>v2 ? v1 : v2;
    }
    public int longestSubsequence(){
        int len1 = s1.length()+1;
        int len2 = s2.length()+1;
        int i, j;

        for(i=1; i<len1; i++){
            for(j=1; j<len2; j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) stringArray[i][j]=stringArray[i-1][j-1]+1;
                else stringArray[i][j]= max(stringArray[i-1][j], stringArray[i][j-1]);
            }
        }
        printTable();
        return stringArray[len1-1][len2-1];
    }
    public void printTable(){
        int len1 = s1.length()+1;
        int len2 = s2.length()+1;
        int i, j;
        for(i=0; i<len2; i++){
            for(j=0;j<len1;j++){
                System.out.print(stringArray[j][i]);
            }
            System.out.println("");
        }
    }
    public void printSubstring(int xPos, int yPos){
        String out = "";
        if(xPos==0||yPos==0) return;
        else{
            if(xPos>=1 && stringArray[xPos-1][yPos]==stringArray[xPos][yPos]) printSubstring(xPos-1, yPos);
            else {
                if(yPos>=1 && stringArray[xPos][yPos-1]==stringArray[xPos][yPos]) printSubstring(xPos, yPos-1);
                else {
                    out+=s1.charAt(xPos-1);
                    printSubstring(xPos-1, yPos-1);
                }
            }
        }
        System.out.print(out);
    }
    public List<String> printAllSubstrings(int xPos, int yPos){
        List<String> strings = new LinkedList();
        if(xPos==0 || yPos==0)strings.add("");
        else {
            if (s1.charAt(xPos - 1)== s2.charAt(yPos - 1)) {
                List<String> tempStrings = printAllSubstrings(xPos - 1, yPos - 1);
                for (String str : tempStrings) strings.add(str + s1.charAt(xPos - 1));
            }
            else {
                if (stringArray[xPos - 1][yPos] >= stringArray[xPos][yPos]) strings.addAll(printAllSubstrings(xPos-1, yPos));

                if (stringArray[xPos][yPos] <= stringArray[xPos][yPos - 1]) strings.addAll(printAllSubstrings(xPos, yPos - 1));
            }
        }
        return strings;
    }
    public List removeDuplicates(List<String> lis){
        List<String> temp = new LinkedList<>();

        for(String s: lis){
            if(temp.indexOf(s)==-1) temp.add(s);
        }
        return temp;
    }
}
