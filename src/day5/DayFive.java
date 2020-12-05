package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayFive {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input5.txt");
        Scanner scan = new Scanner(file);

        Map<Integer, BoardingPass> passes = new HashMap<>();

        while (scan.hasNextLine()) {
            BoardingPass pass = BoardingPass.makeBoardingPass(scan.nextLine());
            passes.put(pass.getId(), pass);
        }

        // P1
        int max = 0;
        for (Integer id : passes.keySet()) {
            if (id > max) {
                max = id;
            }
        }

        System.out.println("\nPART 1:");
        System.out.println("The highest pass ID: " + max);

        // P2
        int mySeat = 0;
        for (int i = 0; i < max; i++) {
            if (passes.containsKey(i - 1) && passes.containsKey(i + 1) && !passes.containsKey(i)) {
                mySeat = i;
                break;
            }
        }

        System.out.println("\nPART 2:");
        System.out.println("My seat ID: " + mySeat);
    }
}
