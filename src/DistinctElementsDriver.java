import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class DistinctElementsDriver {

    public static void main(String[] args) {

        Random rand = new Random();
        int rand_int;
        Integer[] arr = new Integer[(Integer.MAX_VALUE / 100) - 1];

        for (int i = 0; i < arr.length; i++) {
            rand_int = rand.nextInt();
            arr[i] = rand_int;
        }

        long start = Timer.start();
        System.out.println("Distinct Elements in RobinHoodHash: " + RobinHood.distinctElements(arr));
        long end = Timer.end();
        System.out.println("Time taken " + Timer.diff(start, end) + "ms");

        System.out.println();

        start = Timer.start();
        HashSet<Integer> hashset = new HashSet<>();
        hashset.addAll(Arrays.asList(arr));

        int dist = hashset.size();

        end = Timer.end();
        System.out.println("Distinct Elements in HashSet: " + dist);
        System.out.println("Time taken " + Timer.diff(start, end) + "ms");
    }
}
