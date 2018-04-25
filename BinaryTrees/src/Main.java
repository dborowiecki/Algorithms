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
