public class RobinHood<T extends Comparable<? super T>>{

    int size;
    int disp;
    Entry<T>[] table;

    static class Entry<T>{
        T element;
        int probe;
        boolean isDeleted;

        public Entry(T element){
            this.element = element;
            this.probe = 0;
            this.isDeleted = false;
        }
    }

    RobinHood(){
        this.size = 547;
        this.disp = 0;
    }

    private int h(int x){
        return x % size;
    }

    public int find(T x){
        int k = 0;
        int i = h((int)x);

        while(true){
            if(table[i + k].element == null || table[i + k].element == x){
                return i + k;
            }else if(table[i + k].isDeleted){
                break;
            }else{
                k++;
            }
        }
        int xSpot = i + k;
        while(true){
            k++;
            if(table[i + k].element == x){
                return i + k;
            }
            if(table[i + k] == null){
                return xSpot;
            }
        }
    }

    public T remove(T x){
        int index = find(x);
        if(table[index] != null){
            T ele = table[index].element;
            table[index].isDeleted = true;
            return ele;
        }
        return null;
    }

    public boolean contains(T x){
        int loc = find(x);
        if(table[loc].element == x){
            return true;
        }
        return false;
    }

    private int displacement(T x, int loc){
        return loc >= h((int)x) ? (loc - h((int)x)) : (this.size + (loc - h((int)x)));
    }

    public boolean add(T x){
        if(contains(x)){
            return false;
        }
        int loc = h((int)x);
        int d = 0;
        while(true){
            if(table[loc] == null || table[loc].isDeleted){
                table[loc].element = x;
                return true;
            }
            else if(displacement(table[loc].element, loc) >= d){
                d = d + 1;
                loc = (loc + 1) % this.size;
            }else{
                Entry<T> temp = table[loc];
                table[loc].element = x;
                add(temp.element);
                return true;
            }
        }
    }


    public static void main(String[] args) {
        RobinHood<Integer> hash = new RobinHood();
        System.out.println(hash.add(120));

    }

}