import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class DistinctElementsDriver {

    public static void main(String[] args) {

        Random rand = new Random();
        int rand_int;
        Integer[] arr = new Integer[Integer.MAX_VALUE / 100];

        for (int i = 0; i < arr.length; i++) {
            rand_int = rand.nextInt();
            arr[i] = rand_int;
        }

        Timer timer = new Timer();
        System.out.println("Distinct Elements in RobinHoodHash: " + RobinHood.distinctElements(arr));
        timer.end();
        System.out.println(timer);

        System.out.println();

        Timer timerset = new Timer();
        HashSet<Integer> hashset = new HashSet<>();
        hashset.addAll(Arrays.asList(arr));

        int dist = hashset.size();

        timerset.end();
        System.out.println("Distinct Elements in HashSet: " + dist);
        System.out.println(timerset);

    }
}
