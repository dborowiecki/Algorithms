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