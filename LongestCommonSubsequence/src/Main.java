import java.util.List;

public class Main {

    public static void main(String[] args) {
        LongestSubsequence sub = new LongestSubsequence();
        sub.s1 = "110100";
        sub.s2 = "10101";
        sub.stringArray = new int[sub.s1.length()+1][sub.s2.length()+1];
        System.out.println("Longest subsequence: "+sub.longestSubsequence());
        sub.printSubstring(sub.s1.length(), sub.s2.length());
        System.out.println("\n");
        List<String> allSubs = sub.printAllSubstrings(sub.s1.length(),sub.s2.length());

        allSubs = sub.removeDuplicates(allSubs);
        System.out.println("All subsequences:");
        for(String s: allSubs){
            System.out.println(s);
        }
    }
}
