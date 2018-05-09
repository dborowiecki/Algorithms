class Tree{
    Node root;
    public boolean insertNode(Node n){
        Node x=root;
        Node y = null;
        if(x==null){
            root=n;
            return false;
        }
        while(x!=null){
            y=x;
            if(x.value==n.value) {
                x.elements++;
                return true;
            }
            x = y.value>n.value ? y.left : y.right;
        }
        if(y.value>=n.value) y.left = n;
        else y.right = n;

        n.parent=y;
        return false;
    }

    public  void insertValues(int... values){
        for(int n: values){
            insertNode(new Node(n));
        }

    }
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

    public Node subtreeMinimum(Node x){
        while(x.left!=null){
            x=x.left;
        }
        return x;
    }

    public Node subtreeMaximum(Node x){
        while(x.right!=null){
            x=x.right;
        }
        return x;
    }

    public Node deleteValue(int v){
        return deleteElement(searchForElement(v));
    }

    public Node deleteElement(Node z) {
        if(z.elements>1){
            z.elements--;
            return null;
        }
        if (z.left == null) {
            transplant(z, z.right);
            return z.right;
        }
        else{
            if (z.right == null) {
                transplant(z, z.left);
                return z.left;
            }
        }
        Node y = subtreeMinimum(z.right);
        y = PrintTree.subtreeHeight(z.right)>PrintTree.subtreeHeight(z.left)? subtreeMinimum(z.right) : subtreeMaximum(z.left);
        if(y.parent != z) {
            transplant(y, y.right);
            y.right = z.right;
            y.right.parent = y;
        }
        transplant(z, y);
        y.left = z.left;
        y.left.parent = y;
        return y;
    }

    public void transplant(Node where, Node n){
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
}

class Node{
    int value;
    int elements = 0;

    private String color="None";
    Node left = null;
    Node right = null;
    Node parent = null;

    public Node(int value){
        this.value=value;
        elements+=1;
    }
    public void setBlack(){
        color="Black";
    }
    public void setRed(){
        color="Red";
    }
    public void setColor(String color) { if(color.equals("Red")||color.equals("Black")) this.color=color;}
    public String getColor() {
        return color;
    }
}

class RBNode extends Node{

    private String color="None";

    public RBNode(int value){
        super(value);
    }

    public void setBlack(){
        color="Black";
    }
    public void setRed(){
        color="Red";
    }
    public void setColor(String color) { if(color.equals("Red")||color.equals("Black")) this.color=color;}
    public String getColor() {
        return color;
    }
}