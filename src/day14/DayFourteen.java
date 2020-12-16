package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFourteen {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input14.txt"));

        List<String> input = new ArrayList<>();
        while (scan.hasNextLine()) {
            input.add(scan.nextLine());
        }

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The sum of values left in memory: " + new ProgramV1().execute(input));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The sum of values left in memory: " + new ProgramV2().execute(input));
    }
}
