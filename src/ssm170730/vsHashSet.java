package ssm170730;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class vsHashSet {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc;
        File file = new File(args[0]);
        sc = new Scanner(file);
        String operation;
        int operand;
        long modValue = 1000000009;
        long result = 0;

        Timer timer = new Timer();
        Set<Integer> set = new HashSet<>();
        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    operand = sc.nextInt();
                    if(set.add(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Remove": {
                    operand = sc.nextInt();
                    if (set.remove(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Contains":{
                    operand = sc.nextInt();
                    if (set.contains(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        System.out.println();
        System.out.println("HashSet result : "+ result);
        System.out.println("HashSet size : "+ set.size());
        System.out.println(timer.end());

        file = new File(args[0]);
        sc = new Scanner(file);
        result = 0;

        timer.start();
        RobinHood<Integer> dm = new RobinHood<>();
        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    operand = sc.nextInt();
                    if(dm.add(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Remove": {
                    operand = sc.nextInt();
                    if (dm.remove(operand) != null) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Contains":{
                    operand = sc.nextInt();
                    if (dm.contains(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        System.out.println();
        System.out.println("RobinHood result : "+ result);
        System.out.println("RobinHood size : "+ dm.getSize());
        System.out.println(timer.end());

    }
}