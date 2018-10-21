//import java.util.Hashtable;
/*
public class RobinHood<T>{

    int size;
    Entry<T>[] table;
    int iniSize;
    int maxD;

    static class Entry<T>{
        T element;
        boolean isDeleted;

        public Entry(T x){
            this.element = x;
            this.isDeleted = false;
        }
    }

    RobinHood(){

        this.size = 0;
        this.iniSize = 1024;
        this.maxD = 0;
        this.table = new Entry[iniSize];
    }

    static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ ( h >>> 7) ^ (h >>> 4);
    }
    static int indexFor(int h, int length){
        return h & (length - 1);
    }

    private int h(T x){
        return indexFor(hash(x.hashCode()), table.length);
    }

    public int find(T x){
        int k = 0;
        int i = h(x);
        //System.out.println(i);
        int index;// = (i + k) % table.length;
        while(true){
            index = (i + k) % table.length;
            if(table[index] == null) {
                return index;
            }
            else if((T) table[index].element == x && !table[index].isDeleted){
                return index;
            }
            else if(table[index].isDeleted){
                break;
            }else{
                k++;
            }
        }
        int xSpot = index;
        while(true){
            k++;
            if(table[index].element == x){
                return index;
            }
            if(table[index] == null){
                return xSpot;
            }
        }
        //return index;
    }

    public T remove(T x){
        int index = find(x);
        if(table[index] != null){
            T ele = table[index].element;
            table[index].isDeleted = true;
            size--;
            return ele;
        }
        return null;
    }

    public boolean contains(T x){
        int loc = find(x);
        if(table[loc] != null && (table[loc].element == x && !table[loc].isDeleted)){
            return true;
        }
        //System.out.println("weird behaviour of contains");
        return false;
    }

    private int displacement(T x, int loc){
        return loc >= h(x) ? (loc - h(x)) : (table.length + (loc - h(x)));
    }

    public boolean add(T x){
        if(contains(x)){
            return false;
        }
        int loc = h(x);
        int d = 0;
        while(true){
            if(table[loc] == null || table[loc].isDeleted){
                table[loc] = new Entry<>(x);
                size++;
                if(size == ((3 * table.length) / 9)){
                    resize();
                }

                return true;
            }
            else if(displacement(table[loc].element, loc) >= d){
                d = d + 1;
                loc = (loc + 1) % table.length;
            }else{
                Entry<T> temp = table[loc];
                table[loc] = new Entry<>(x);

                x = temp.element;

                loc = (loc + 1) % table.length;
                d = displacement(x, loc);
            }
        }
    }

    public void printer(){
        if(size == 0){
            System.out.println("HashTable is empty");
        }else{
            for (int i = 0; i < table.length; i++) {
                if(table[i] != null && !table[i].isDeleted){
                    System.out.print(i + ":" + table[i].element + "  ");
                }
            }
        }
    }

    static<T> int distinctElements(T[ ] arr){
        RobinHood<T> dist = new RobinHood<>();
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] != null)
                dist.add(arr[i]);
        }
        //dist.printer();
        return dist.size;
    }

    private void resize(){
        size = 0;
        maxD = 0;
        Entry[] temp = new Entry[iniSize];
        for (int j = 0; j < table.length; j++) {
            if(table[j] != null && !table[j].isDeleted){
                temp[j] = this.table[j];
            }
        }
        iniSize *= 2;
        this.table = new Entry[iniSize];
        for (int i = 0; i < temp.length; i++) {
            if(temp[i] != null && !temp[i].isDeleted){
                this.add((T) temp[i].element);
            }
        }
    }

    public static void main(String[] args) {
        RobinHood<Integer> hash = new RobinHood<>();
        hash.add(129);
        hash.add(129);
        hash.printer();
    }
}
*/



import java.util.Arrays;

public class RobinHood<T> {
    int size = 1024;
    int elementSize = 0;
    int maxDisp = 0;
    Object table[];

    public RobinHood() {
        table = new Object[size];
        Arrays.fill(table, null);
    }

    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    // length = table.length is a power of 2
    static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private int h(T x) {
        return indexFor(hash(x.hashCode()), table.length);
    }

    private int displace(T x, int loc) {
        return loc >= h(x) ? loc - h(x) : table.length + loc - h(x);
    }

    public boolean add(T x) {
        if (contains(x)) return false;
        int loc = h(x);
        int d = 0;
        while (true) {
            if (table[loc] == null) {
                table[loc] = x;
                elementSize++;
                if (elementSize > (table.length / 2))
                    resize();
                return true;
            } else if (displace((T) table[loc], loc) >= d) {
                d = d + 1;
                maxDisp = Math.max(maxDisp, d);
                loc = (loc + 1) % table.length;
            } else {
                T temp = (T) table[loc];
                table[loc] = x;
                x = temp;
                loc = (loc + 1) % table.length;
                d = displace(x, loc);
            }
        }
    }

    public T remove(T x) {
        int index = find(x);
        if (table[index] == x) {
            T temp = (T) table[index];
            table[index] = null;
            elementSize--;
            return temp;
        }
        return null;
    }

    private void resize() {
        Object[] tempArr = new Object[size];
        for (int j = 0; j < table.length; j++)
            tempArr[j] = this.table[j];

        size *= 2;
        this.table = new Object[size];
        Arrays.fill(table, null);
        elementSize = 0;
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] != null)
                this.add((T) tempArr[i]);
        }
    }

    //Commit Pseudo

    private int find(T x) {
        int index = h(x);
        for (int k = 0; k <= maxDisp; k++) {
            int nextIndex = index + k;
            nextIndex = nextIndex % table.length;
            if (table[nextIndex] == null || table[nextIndex].equals(x)) {
                return nextIndex;
            }
        }
        return index;
    }

    public boolean contains(T x) {
        int index = find(x);
        if (table[index] != null && table[index].equals(x))
            return true;
        return false;
    }

    public void printer() {
        if (elementSize == 0)
            System.out.println("HashTable is empty");
        else {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null)
                    System.out.print(i + ":" + table[i] + "  ");

            }
        }
        System.out.println();
        System.out.println("distinct = " + elementSize);
    }

    static <T> int distinctElements(T[] arr) {
        RobinHood<T> dist = new RobinHood<>();
        boolean flag = false;
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null)
                flag = dist.add(arr[i]);
                if(flag)
                    c++;
        }
        System.out.println(c);
        //dist.printer();
        return dist.elementSize;
    }


    public static void main(String[] args) {
        RobinHood<Integer> hash = new RobinHood<>();
        hash.add(2);
        hash.add(3);
        hash.add(4);
        hash.remove(4);
        hash.remove(1);
        hash.printer();
    }
}