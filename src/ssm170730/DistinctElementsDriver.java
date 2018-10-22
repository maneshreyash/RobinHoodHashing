package ssm170730;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class DistinctElementsDriver {

    public static void main(String[] args) {

        Random rand = new Random();
        int rand_int;
        Integer[] arr = new Integer[10000000];

        for (int i = 0; i < arr.length; i++) {
            rand_int = rand.nextInt();
            arr[i] = rand_int;
        }

        Timer timer = new Timer();
        System.out.println("Distinct Elements in RobinHoodHash: " + RobinHood.distinctElements(arr));
        timer.end();
        System.out.println(timer);

        System.out.println();

        Timer timerSet = new Timer();
        HashSet<Integer> hashSet = new HashSet<>(Arrays.asList(arr));

        int dist = hashSet.size();

        timerSet.end();
        System.out.println("Distinct Elements in HashSet: " + dist);
        System.out.println(timerSet);

    }
}
