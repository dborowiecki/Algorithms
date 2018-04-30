
class DirectionList<T> {
    Node head;

    void insert(T s){
        Node<T> newNode= new Node(s,head);
        head=newNode;
    }

    void print(){
        Node x=head;
        while(x!=null) {
            System.out.print(x.value+", ");
            x=x.next;
        }
        System.out.println();
    }

    Node search(T s){
        Node iterator=head;
        Node y=null;
        while(iterator!=null) {
            if(iterator.value==s) y=iterator;
            iterator=iterator.next;
        }
        return y;
    }


    void delete(T s){
        Node iterator=head;
        if(head.value==s)head=head.next;
        while(iterator.next!=null) {
            if(iterator.next.value.equals(s)) iterator.next=iterator.next.next;
            iterator=iterator.next;
        }
    }

    void kasuj(){ head=null;}

    DirectionList removeDoubles(){
        DirectionList l=this;
        Node iterator = l.head;
        while(iterator.next!=null){
            Node temp = iterator.next;
            while(temp.next!=null){
                if(iterator.value.equals(temp.value)){
                    iterator.next=temp.next;
                }
                temp=temp.next;
            }
            iterator=iterator.next;
        }
        return l;
    }


    public static DirectionList merge(DirectionList l1, DirectionList l2){
        if(l1.head==l2.head){
            System.out.println("Cannot merge");
            return null;
        }
        DirectionList merged;
        merged =  l1;
        Node temp=l1.head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=l2.head;
        return merged;
    }

}
