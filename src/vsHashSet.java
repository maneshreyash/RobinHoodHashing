import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class vsHashSet {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc;
        int i = 0;
        if (args.length > 0) {
            File file = new File("C:\\Users\\manes\\Desktop\\Balaji\\Long Projects\\2\\lp2-inputs\\lp2-in03.txt");
            sc = new Scanner(file);
        } else {
            sc = new Scanner(System.in);
        }
        String operation;
        int operand;

        // Initialize the timer
        Timer timer = new Timer();
        RobinHood<Integer> robin = new RobinHood<>();

        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    i++;
                    operand = sc.nextInt();
                    robin.add(operand);
                    break;
                }
                case "Remove": {
                    i++;
                    operand = sc.nextInt();
                    robin.remove(operand);
                    break;
                }
                case "Contains": {
                    i++;
                    operand = sc.nextInt();
                    robin.contains(operand);
                    break;
                }
                case "p": { //Print list
                    robin.printer();
                    break;
                }
            }
        }

        // End Time
        timer.end();
        System.out.println(timer);
        System.out.println("Number of Operation: "+ i);






        //For HashSet

        i = 0;
        if (args.length > 0) {
            File file = new File("C:\\Users\\manes\\Desktop\\Balaji\\Long Projects\\2\\lp2-inputs\\lp2-in03.txt");
            sc = new Scanner(file);
        } else {
            sc = new Scanner(System.in);
        }

        Timer timerset = new Timer();
        HashSet<Integer> hashset = new HashSet<>();

        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    i++;
                    operand = sc.nextInt();
                    hashset.add(operand);
                    break;
                }

                case "Remove": {
                    i++;
                    operand = sc.nextInt();
                    hashset.remove(operand);
                    break;
                }
                case "Contains": {
                    i++;
                    operand = sc.nextInt();
                    hashset.contains(operand);
                    break;
                }
            }
        }

        // End Time
        timerset.end();
        System.out.println(timerset);
        System.out.println("Number of Operation: "+ i);
    }
}