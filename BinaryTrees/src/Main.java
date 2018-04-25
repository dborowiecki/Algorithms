import java.util.ArrayList;
import java.util.List;
class Tree<T extends Comparable<T>>{
    Node<T> root;
    private   void insertNode(Node<T> n){
        Node x=root;
        Node y = null;
        if(x==null){
            root=n;
            return;
        }
        while(x!=null){
            y=x;
            if(x.value.compareTo(n.value)==0) {
                x.elements++;
                return;
            }
           // x = y.value>n.value ? y.left : y.right;
            x = y.value.compareTo(n.value)>0 ? y.left : y.right;
        }
        if(y.value.compareTo(n.value)>=0) y.left = n;
        else y.right = n;

        n.parent=y;
    }
    private   void insertNodeDuplicatesLeft(Node<T> n){
        Node x=root;
        Node y = null;
        if(x==null){
            root=n;
            return;
        }
        while(x!=null){
            y=x;
           // x = y.value>=n.value ? y.left : y.right;
            x = y.value.compareTo(n.value)>=0 ? y.left : y.right;
        }
        if(y.value.compareTo(n.value)>=0) y.left = n;
        else {
            y.right = n;
        }

        n.parent=y;
    }
    public  void insertValues(T... values){
        for(T n: values){
            insertNode(new Node(n));
        }

    }
    public void insertValuesDuplicatesLeft(T... values){
        for(T n: values){
            insertNodeDuplicatesLeft(new Node(n));
        }
    }
    public Node searchForElement(T v){
        Node x = root;
        while(x!=null){
            if(x.value.compareTo(v)==0){
                System.out.println("Found element "+v+" on position: " + x + "Count: "+x.elements);
                return x;
            }
           // else x = x.value>v ? x.left : x.right;
            else x = x.value.compareTo(v)>0 ? x.left : x.right;
        }
        System.out.println("Element " +v+" not found.");
        return null;
    }
    public List<Node> searchForElementDoubleValuesLeft(T v){
        Node x = root;
        List<Node> matched = new ArrayList<>();
        while(x!=null){
            if(x.value.compareTo(v)==0){
                matched.add(x);
            }
          //  x = x.value>=v ? x.left : x.right;
            x = x.value.compareTo(v)>=0 ? x.left : x.right;
        }
        System.out.print("Znalazłem obiekt "+v+" na pozycjach: \n");
        for(Node n: matched){
            System.out.println(n);
        }
        System.out.print("----------------");
        return matched;
    }

    public Node subtreeMinimum(Node<T> x){
        while(x.left!=null){
            x=x.left;
        }
        return x;
    }
    public void deleteElement(Node<T> z) {
        if(z==null) return;
        if(z.elements>1){
            z.elements--;
            return;
        }
        if (z.left == null) {
            transplant(z, z.right);
            return;
        }
        if (z.right == null) {
            transplant(z, z.left);
            return;
        }
        Node y = subtreeMinimum(z.right);
        if(y.parent != z) {
            transplant(y, y.right);
            y.right = z.right;
            y.right.parent = y;
        }
        transplant(z, y);
        y.left = z.left;
        y.left.parent = y;
    }

    public void transplant(Node<T> where, Node<T> n){
        if(where.parent==null) {
            root=n;
            return;
        }
        if(where==where.parent.left){  // u jest lewym synem
            where.parent.left=n;
        }
        else {
            where.parent.right = n;   // u jest prawym synem
        }
        if(n != null) {
            n.parent = where.parent;
        }
    }

    public void deleteFromDuplicateOnLeft(T value){
        List<Node> nodesToDelete = searchForElementDoubleValuesLeft(value);
        for(Node n: nodesToDelete){
            deleteElement(n);
        }
    }

}
class Node<T extends Comparable<T>>{
    T value;
    int elements = 0;
    Node left = null;
    Node right = null;
    Node parent = null;

    public Node(T value){
        this.value=value;
        elements+=1;
    }
}
public class Main {

    public static void main(String[] args) {
        Tree<String> t = new Tree();
        Tree<Integer> tDuplicateOnLeft = new Tree();
        System.out.println("Tree with duplicates stored on left side");
        tDuplicateOnLeft.insertValuesDuplicatesLeft(17,18,11,60,30,18,19,18,17,22,17,23,18,18,26,17);
        PrintTree.printTree(tDuplicateOnLeft);
        tDuplicateOnLeft.searchForElementDoubleValuesLeft(6);
        tDuplicateOnLeft.searchForElementDoubleValuesLeft( 5);
        tDuplicateOnLeft.deleteFromDuplicateOnLeft(18);
        tDuplicateOnLeft.deleteFromDuplicateOnLeft(17);

        System.out.println("\nUsuwanie wszystkich  18 i 17: ");
        PrintTree.printTree(tDuplicateOnLeft);
        System.out.println("\n\nTree with duplicates stored in same node");
        t.insertValues("A", "B", "C", "D", "E");
        t.insertValues("B");
        PrintTree.printTree(t);
        t.searchForElement("B");
        t.searchForElement("5");
        System.out.println("Deleting 6: ");
        t.deleteElement(t.searchForElement("6"));
        System.out.println("Deleting C: ");
        t.deleteElement(t.searchForElement("C"));
        PrintTree.printTree(t);
    }
}
