
public class RedBlackTree extends Tree{

    private void setColor(Node n){
        n.setRed();
        if(n==root){
            n.setBlack();
            return;
        }
        Node parentBrother;
        Node grandfather = n.parent.parent;
        if(n.parent.getColor().equals("Black")) return;
        if(grandfather==null) return;

        parentBrother = grandfather.left==n.parent ? grandfather.right : grandfather.left;
        if(parentBrother==null){
            parentBrother = new Node(0);
            parentBrother.setBlack();
        }

        //Przypadek pierwszy
        if(parentBrother.getColor().equals("Red")){
            n.parent.setBlack();
            parentBrother.setBlack();
            grandfather.setRed();
            setColor(grandfather);
            return;
        }
        //Przypadek drugi
        if(parentBrother.getColor().equals("Black")){
            if(grandfather.left==n.parent&& n.parent.right==n || grandfather.right==n.parent&&n.parent.left==n){
                if(grandfather.left==n.parent){
                    swap(n, n.parent);
                    n.parent.left=n;
                    Node T1=n.left, T2=n.right;
                    n.parent.right=T2;
                    if(T2!=null) T2.parent=n.parent;
                    n.right=T1;
                    n.left=null;
                    setColor(n);
                }
                else{
                    swap(n, n.parent);
                    n.parent.right=n;
                    Node T1=n.left, T2=n.right;
                    n.parent.left=T1;
                    if(T1!=null) T1.parent=n.parent;
                    n.left=T2;
                    n.right=null;
                    setColor(n);
                }
            }
        }
        //Przypadek trzeci
        if(parentBrother.getColor().equals("Black")&&(grandfather.left==n.parent&&n.parent.left==n||grandfather.right==n.parent&&n.parent.right==n)){
            if(n.parent.left==n){
                Node temp = n.parent.right;
                n.parent.parent=grandfather.parent;
                if(grandfather.parent!=null) {
                    if (grandfather.parent.left == grandfather) grandfather.parent.left = n.parent;
                    else grandfather.parent.right = n.parent;
                }
                n.parent.right=grandfather;
                if(grandfather==root) root=n.parent;
                grandfather.left=temp;
                grandfather.parent=n.parent;
                if(temp!=null)temp.parent=grandfather;
                grandfather.setRed();
                n.parent.setBlack();
                return;
            }
            if(n.parent.right==n){
                Node temp = n.parent.left;
                n.parent.parent=grandfather.parent;
                if(grandfather.parent!=null) {
                    if (grandfather.parent.left == grandfather) grandfather.parent.left = n.parent;
                    else grandfather.parent.right = n.parent;
                }
                n.parent.left=grandfather;
                if(grandfather==root) root=n.parent;
                grandfather.right=temp;
                grandfather.parent=n.parent;
                if(temp!=null)temp.parent=grandfather;
                grandfather.setRed();
                n.parent.setBlack();
                return;
            }
        }

    }


    private void swap(Node a, Node b){
        int vA = a.value;
        a.value=b.value;
        b.value=vA;

    }
    @Override
    public boolean insertNode(Node n){
        /**
         * @insertNode Zwraca true jesli element juz sie pojawił
         */
        boolean isRepeated = super.insertNode(n);
        if(isRepeated) return true;
        System.out.println("----------\nKOLOROWANY WEZEL: "+n.value+"\nPRZED KOLOROWANIEM: ");
        PrintTree.printTree(this);
        setColor(n);
        System.out.println("\nPO KOLOROWANIU: ");
        PrintTree.printTree(this);
        System.out.println("----------");
        return false;
    }
    public void insertValues(int ...values){
        for(int value: values){
            insertNode(new Node(value));
        }
    }
    @Override
    public Node searchForElement(int v){
        Node x = root;
        while(x!=null){
            if(x.value==v){
                System.out.println("Znalazlem element "+v+" na pozycji: " + x + "W ilosci: "+x.elements);
                return x;
            }
            else x = x.value>v ? x.left : x.right;
        }
        System.out.println("Nie znalazlem elementu " +v);
        return null;
    }


    @Override
    public Node deleteElement(Node z) {
        String originalColor = z.getColor();
        Node newOnSpot = super.deleteElement(z);

        if(newOnSpot==null) return null;

        if(originalColor.equals("Black")) normalizeAfterDelete(newOnSpot);
        else newOnSpot.setRed();

        if(z.parent==null) newOnSpot.parent=null;

        return newOnSpot;
    }

    private void normalizeAfterDelete(Node z){

        Node parent = z.parent;
        Node brother = z.parent.left == z ? z.parent.right : z.parent.left;
        //Przypadek 0
        if (z.getColor().equals("Red")) {//
            System.out.println("Przypadek 0");
            z.setBlack();
            return;
        }
        //Przypadek 1
        if (brother!=null&&brother.getColor().equals("Red")) {
            Node temp;
            if (parent.left == z) {
                System.out.println("Przypadek 1.1");
                if(parent.parent==null) root=brother;
                if(brother.left!=null) brother.left.parent=parent;
                parent.right=brother.left;
                brother.left=parent;
                brother.parent=parent.parent;
                brother.left=parent;
                parent.parent=brother;
                brother.setBlack();
                parent.setRed();
                normalizeAfterDelete(z);
                return;
            }
            if (parent.right == z) {
                System.out.println("Przypadek 1.2");
                if(parent.parent==null) root=brother;
                if(brother.right!=null) brother.right.parent=brother.parent;
                parent.left=brother.right;
                brother.right=parent;
                brother.parent=parent.parent;
                brother.right=parent;
                parent.parent=brother;
                brother.setBlack();
                parent.setRed();
                normalizeAfterDelete(z);
                return;
            }
        }
        //Przypadek 2
        if (isSecondCondition(brother)) {
            System.out.println("PRZYPADEK @");
            brother.setRed();
            normalizeAfterDelete(parent);
            return;
        }
        //Przypadek 3 i 4
        System.out.println(z.value);
        Node temp = new Node(0);
        temp.setBlack();
        if(brother.left==null) brother.left=temp;
        if(brother.right==null) brother.right=temp;
        if (brother.getColor().equals("Black") && !brother.left.getColor().equals(brother.right.getColor())) {
            //Przypadek 3
            Node n;

            System.out.println("Przypadek 3");
            if ((brother.left.getColor().equals("Red") && parent.right == brother)) {
                n = brother.left;
                if(n.right!=null) n.right.parent=brother;
                brother.left=n.right;
                n.right = brother;
                brother.parent=n;
                n.parent = parent;
                parent.right=n;
                n.setBlack();
                brother.setRed();
                normalizeAfterDelete(z);
                return;
            }
            if (brother.right.getColor().equals("Red") && parent.left == brother) {
                n = brother.right;
                if(n.left!=null) n.left.parent = brother;
                brother.right = n.left;
                n.left = brother;
                brother.parent=n;
                n.parent=parent;
                parent.left=n;
                n.setBlack();
                brother.setRed();
                normalizeAfterDelete(z);
                return;
            }
            //Przypadek 4

            System.out.println("Przypadek 4");
            if (parent.right == brother && brother.right.getColor().equals("Red")) {
                parent.right = brother.left;
                brother.left.parent = parent;
                if(parent.parent!=null) {
                    if (parent.parent.left == parent) parent.parent.left = brother;
                    else parent.parent.right = brother;
                }
                else  root=parent;
                brother.left = parent;
                parent.parent = brother;
                if (parent.getColor().equals("Black")) brother.setBlack();
                else brother.setRed();
                parent.setBlack();
                brother.right.setBlack();
                temp=null;
                return;
            }
            if (parent.left == brother && brother.left.getColor().equals("Red")) {
                parent.left = brother.right;
                brother.right.parent = parent;
                if(parent.parent!=null) {
                    if (parent.parent.left == parent) parent.parent.left = brother;
                    else parent.parent.right = brother;
                }
                else root=parent;
                brother.right = parent;
                parent.parent = brother;
                if (parent.getColor().equals("Black")) brother.setBlack();
                else brother.setRed();
                parent.setBlack();
                brother.left.setBlack();
                temp=null;
                return;
            }
        }
    }

    private boolean isSecondCondition(Node brother){
        if(brother.left==null){
            if(brother.right==null) return true;
            if(brother.right.getColor().equals("Black")) return true;
            return false;
        }
        if(brother.right==null){
            if(brother.left.getColor().equals("Black")) return true;
        }
        if(!brother.left.getColor().equals("Red")&&!brother.right.getColor().equals("Red")) return true;
        return false;
    }
}
class PrintOrderTree{

    private static void printInorderNodes(Node n){
        if(n.left!=null)    printInorderNodes(n.left);
        System.out.print(n.value+n.getColor().substring(0,1)+ " ");
        if(n.right!=null)   printInorderNodes(n.right);
    }

    public static void printInorder(Tree t){
        printInorderNodes(t.root);
    }
}
