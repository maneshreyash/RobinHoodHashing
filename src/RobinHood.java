//import java.util.Hashtable;

public class RobinHood<T extends Comparable<? super T>>{

    int size;
    Entry<T>[] table;
    int iniSize = 4;

    static class Entry<T>{
        T element;
        boolean isDeleted;

        public Entry(T x){
            this.element = x;
            this.isDeleted = false;
        }
    }

    RobinHood(){
        this.table = new Entry[iniSize];
        this.size = 0;
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
        int index;
        while(true){
            index = (i + k) % table.length;
            if(table[index] == null || table[index].element == x){
                return index;
            }else if(table[index].isDeleted){
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
        if(table[loc] == null || table[loc].isDeleted){
            return false;
        }
        return true;
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
                if(size == (3 * table.length / 4)){
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
                    System.out.print(table[i].element + "  ");
                }
            }
        }
    }


    private void/*RobinHood<T>*/ resize(/*RobinHood<T> IncreasedSize*/){

        Entry[] temp = new Entry[iniSize];
        //Entry[] temp = this.table;
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
        RobinHood<Integer> hash = new RobinHood();
        hash.add(120);
        hash.add(120);
        hash.add(121);
        hash.add(122);
        hash.add(45);
        //hash.add(125);
        //hash.remove(122);
        //hash.add(135);
        hash.printer();
        /*System.out.println(hash.add(120));
        System.out.println(hash.add(120));*/
    }
}