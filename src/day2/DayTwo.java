package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input2.txt");
        Scanner scan = new Scanner(file);

        List<Entry> input = new ArrayList<>();

        while (scan.hasNextLine()) {
            String[] data = scan.nextLine().split(" ");
            String[] minmax = data[0].split("-");

            input.add(Entry.makeEntry(
                    Integer.parseInt(minmax[0]),
                    Integer.parseInt(minmax[1]),
                    data[1].charAt(0),
                    data[2]
            ));
        }

        // P1 & P2
        int countP1 = 0, countP2 = 0;
        for (Entry entry : input) {
            if (entry.isValidPartOne()) {
                countP1++;
            }
            if (entry.isValidPartTwo()) {
                countP2++;
            }
        }

        System.out.println("\nPart 1:");
        System.out.println("Valid passwords: " + countP1);

        System.out.println("\nPart 2:");
        System.out.println("Valid passwords: " + countP2);
    }
}
