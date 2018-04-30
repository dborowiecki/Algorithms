

public class Main{


    public static void main(String[] args) {
        DirectionList<String> lista = new DirectionList();
        lista.insert("Apple");
        lista.insert("SNSV");
        lista.insert("MGM");
        lista.insert("Przydluga Nazwa");
        lista.insert("Tom Ford");
        lista.insert("Armani");
        lista.insert("Armani");
        lista.insert("Gucci");
        lista.insert("XIAOMI");

        System.out.print("Created list: ");
        lista.print();
        DirectionList bezPowtorzen = lista.removeDoubles();
        System.out.print("\nNo doubles:  ");
        bezPowtorzen.print();
        System.out.print("Deleting  SNSV\n" + "After delete: ");
        lista.delete("SNSV");
        lista.print();

        System.out.print("\nSearch for Gucci.\nResault: "+lista.search("Gucci")+"\n\n");

        System.out.print("List merge: ");
        DirectionList l = new DirectionList();
        l.insert("Apple");
        l.insert("SNSV");
        l.insert("Tom Ford");
        l.insert("Armani");
        l.insert("Armani");
        l.insert("Gucci");
        l.insert("XIAOMI");
        DirectionList.merge(lista, l).print();

    }
}