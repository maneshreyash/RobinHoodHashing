package ssm170730;

/**
 * This class creates a RobinHood hashing technique which will use the techniques of probing to improve hash clash resolution.
 * @param <T>
 */
public class RobinHood<T>{

    private int size;
    private Entry<T>[] table;
    private int iniSize;
    private int maxD;

    /**
     * Every element in the HashTable will be created using an Entry class.
     * @param <T>: The element to be entered.
     */
    static class Entry<T>{
        T element;
        boolean isDeleted;

        /**
         * Constructor for Entry class;
         * @param x: Element to be stored in the Table.
         */
        private Entry(T x){
            this.element = x;
            this.isDeleted = false;
        }
    }

    /**
     * Constructor for RobinHood Hash Table.
     */
    RobinHood(){

        this.size = 0;
        this.iniSize = 1024;
        this.maxD = 0;
        this.table = new Entry[iniSize];
    }

    /**
     *
     * @param h: hashcode() of x.
     * @return: gives the hash key for x.
     */
    private static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ ( h >>> 7) ^ (h >>> 4);
    }

    /**
     *
     * @param h: hash key from hash().
     * @param length: length of the Entry array which will hold elements for the table.
     * @return: gives the index where x can be added in the table Entry array.
     */
    private static int indexFor(int h, int length){
        return (h & (length - 1));
    }

    private int h(T x){
        return indexFor(hash(x.hashCode()), table.length);
    }

    /**
     * @param x: Element x to be found in the HashTable.
     * @return: gives the index where x could be added or might have been.
     */
    private int find(T x){
        int k = 0;
        int index = 0;
        boolean flag = false;
        while(k <= maxD){
            index = (h(x) + k) % table.length;
            if(table[index] == null || (table[index].element.equals(x) && !table[index].isDeleted)){
                return index;
            }
            else if(table[index].isDeleted){
                flag = true;
                break;
            }else{
                k++;
            }
        }
        if(flag) {
            int xSpot = index;
            while (k <= maxD) {
                k++;
                index = (h(x) + k) % table.length;
                if (table[index] != null && table[index].element.equals(x)) {
                    return index;
                }
                if (table[index] == null) {
                    return xSpot;
                }
            }
        }
        return index;
    }

    /**
     *
     * @return: gives the number of elements in the HashTable.
     */
    public int getSize(){
        return this.size;
    }

    /**
     *
     * @param x: Element x to be removed from the HashTable.
     * @return: gives the element that was removed, else returns null if element was not added earlier or was deleted previously.
     */
    public T remove(T x){
        int index = find(x);
        if(table[index] != null && table[index].element.equals(x) && !table[index].isDeleted){
            T ele = table[index].element;
            table[index].isDeleted = true;
            this.size--;
            return ele;
        }
        return null;
    }

    /**
     *
     * @param x: Element x to be checked for containment.
     * @return: gives true if element is present in the HashTable or returns false if element is absent.
     */
    public boolean contains(T x){
        int loc = find(x);
        return table[loc] != null && (table[loc].element.equals(x) && !table[loc].isDeleted);
    }

    /**
     * Finds how far the element is from its designated index.
     * @param x: Element that is displaced.
     * @param loc: hashed location of x.
     * @return: displacement of x from hashed index.
     */
    private int displacement(T x, int loc){
        return loc >= h(x) ? (loc - h(x)) : (table.length + (loc - h(x)));
    }

    /**
     *
     * @param x: Element to be added.
     * @return: True if add was successful else false.
     */
    public boolean add(T x){
        if(contains(x)){
            return false;
        }
        int loc = h(x);
        int d = 0;
        while(true){
            if(table[loc] == null || table[loc].isDeleted){
                table[loc] = new Entry<>(x);
                this.size++;
                double load = (this.size * 1.0) / (table.length * 1.0);
                if(load > 0.75){
                    resize();
                }
                return true;
            }
            else if(displacement(table[loc].element, loc) >= d){
                d = d + 1;
                loc = (loc + 1) % table.length;
            }else{
                Entry<T> temp = table[loc];
                temp.element = table[loc].element;

                table[loc] = new Entry<>(x);

                x = temp.element;

                loc = (loc + 1) % table.length;
                d = displacement(x, loc);

                if(d > maxD) {
                    maxD = d;
                }
            }
        }
    }

    /*public void printer(){
        if(size == 0){
            System.out.println("HashTable is empty");
        }else{
            for (int i = 0; i < table.length; i++) {
                if(table[i] != null && !table[i].isDeleted){
                    System.out.print(i + ":" + table[i].element + "  ");
                }
            }
        }
    }*/

    /**
     * Calculate distinct elements in an array
     * @param arr: Array of Integers which may or may not have duplicates.
     * @return: returns the count of distinct elements in the provided array.
     */
    static<T> int distinctElements(T[ ] arr){
        RobinHood<T> dist = new RobinHood<>();
        for (T i: arr){
            if(i != null)
                dist.add(i);
        }
        return dist.getSize();
    }

    /**
     * To increase the size of the table in case there are a lot of additions to reduce the number of probes.
     */
    private void resize(){

        Entry[] temp = this.table;
        this.iniSize = iniSize * 2;
        this.table = new Entry[iniSize];
        size = 0;
        maxD = 0;
        for (Entry i: temp){
            if(i != null && !i.isDeleted){
                this.add((T) i.element);
            }
        }
    }

    public static void main(String[] args) {
        RobinHood<Integer> hash = new RobinHood<>();
        hash.add(129);
        hash.remove(129);
        hash.add(129);
        //hash.printer();
    }
}