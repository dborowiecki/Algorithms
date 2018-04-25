import java.util.ArrayList;
import java.util.List;

public class PrintTree{

    private static String nullNodeValue = "-4312342";

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static void printTree(Tree t){
        int currentHeight=0;
        List<Node> nodesOnLevel= new ArrayList<>();
        int treeHeight = treeHeight(t);
        int firstSeparator = getSeparatorLen(treeHeight);
        for(currentHeight=1; currentHeight<=treeHeight; currentHeight++){
            nodesOnLevel=getNodesOnLevel(t, currentHeight, nodesOnLevel);
            printLevel(nodesOnLevel, firstSeparator, firstSeparator*2+1, currentHeight);
            firstSeparator=getSeparatorLen(treeHeight(t)-currentHeight);
        }


    }
    private static int getSeparatorLen(int level){
        double l=1;
        for(int i=1; i<level; i++){
            l = l%2==0 ? l*2 : l*2+1;
        }
        return (int)l;

    }
    private static List<Node> getNodesOnLevel(Tree t, int height, List<Node> previousLevelNodes){
        List<Node> thisLevelNodes = new ArrayList<>();
        if(height==1){
            thisLevelNodes.add(t.root);
            return thisLevelNodes;
        }
        else {
            for (Node n : previousLevelNodes) {
                if (n.value == nullNodeValue || n==null) {
                    thisLevelNodes.add(new Node(nullNodeValue));
                    thisLevelNodes.add(new Node(nullNodeValue));
                    continue;
                }

                if(n.left==null) thisLevelNodes.add(new Node(nullNodeValue));
                else thisLevelNodes.add(n.left);
                if(n.right==null)  thisLevelNodes.add(new Node(nullNodeValue));
                else thisLevelNodes.add(n.right);
            }
        }
        return thisLevelNodes;
    }
    private static void printLevel(List<Node> nodesOnLevel, int firstSeparator, int valueSeparator, int level){
        String textInLine, nextValues;
        textInLine=padRight("", firstSeparator);
        for(Node n : nodesOnLevel){
            if(n.value.toString()==nullNodeValue) nextValues="-"+padRight("", valueSeparator);
            else nextValues= n.value.toString()+padRight("", valueSeparator);
            if(nodesOnLevel.indexOf(n)==nodesOnLevel.size()/2 && level==2) {
                textInLine += "";
            }

            textInLine+=nextValues;
        }
        System.out.print(textInLine+"\n");
    }

    public static int treeHeight(Tree t){
        int h = subtreeHeight(t.root);
        return h;
    }
    private static int subtreeHeight(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            int lDepth = subtreeHeight(node.left);
            int rDepth = subtreeHeight(node.right);

            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
}