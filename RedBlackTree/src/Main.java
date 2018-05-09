
public class Main {
    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();
        t.insertValues(5, 10, 15, 7, 20, 25, 13);

        System.out.println("\n\n\n\nSKONCZONE DRZEWO:");
        PrintTree.printTree(t);
        System.out.println("Wypisywanie inorder:");
        PrintOrderTree.printInorder(t);
        System.out.println("\nSzukanie:");
        t.searchForElement(20);
        t.deleteValue(t.root.value);

        t.insertValues(11, 12);
        PrintTree.printTree(t);

        t.deleteValue(13);
        //  System.out.println(t.root.parent.value);
        PrintTree.printTree(t);

    }
}
