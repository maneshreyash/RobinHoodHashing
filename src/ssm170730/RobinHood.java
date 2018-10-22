package ssm170730;

public class RobinHood<T>{

    private int size;
    private Entry<T>[] table;
    private int iniSize;
    private int maxD;

    static class Entry<T>{
        T element;
        boolean isDeleted;

        private Entry(T x){
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

    private static int hash(int h){
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ ( h >>> 7) ^ (h >>> 4);
    }
    private static int indexFor(int h, int length){
        return (h & (length - 1));
    }

    private int h(T x){
        return indexFor(hash(x.hashCode()), table.length);
    }

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

    public int getSize(){
        return this.size;
    }

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

    public boolean contains(T x){
        int loc = find(x);
        return table[loc] != null && (table[loc].element.equals(x) && !table[loc].isDeleted);
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

    static<T> int distinctElements(T[ ] arr){
        RobinHood<T> dist = new RobinHood<>();
        for (T i: arr){
            if(i != null)
                dist.add(i);
        }
        return dist.getSize();
    }

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