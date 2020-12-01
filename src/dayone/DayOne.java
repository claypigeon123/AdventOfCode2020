package dayone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayOne {

    private static final int SUM = 2020;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input1.txt");
        Scanner scan = new Scanner(file);

        List<Integer> input = new ArrayList<>();
        while (scan.hasNextLine()) {
            input.add(Integer.parseInt(scan.nextLine()));
        }
        scan.close();

        // P1
        int p1_numOne = 0, p1_numTwo = 0;
        for (int i = 0; i < input.size(); i++) {
            boolean found = false;
            for (int k = 0; k < input.size(); k++) {
                if (input.get(i) + input.get(k) == SUM) {
                    p1_numOne = input.get(i);
                    p1_numTwo = input.get(k);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        System.out.println("\nPart 1:");
        System.out.println(p1_numOne + " + " + p1_numTwo + " = " + (p1_numOne + p1_numTwo));
        System.out.println(p1_numOne + " * " + p1_numTwo + " = " + (p1_numOne * p1_numTwo));

        // P2
        int p2_numOne = 0, p2_numTwo = 0, p2_numThree = 0;
        for (int i = 0; i < input.size(); i++) {
            boolean found = false;
            for (int k = 0; k < input.size(); k++) {
                if (input.get(i) + input.get(k) > SUM) {
                    continue;
                }
                for (int j = 0; j < input.size(); j++) {
                    if (input.get(i) + input.get(k) + input.get(j) == SUM) {
                        found = true;
                        p2_numOne = input.get(i);
                        p2_numTwo = input.get(k);
                        p2_numThree = input.get(j);
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        System.out.println("\nPart 2:");
        System.out.println(p2_numOne + " + " + p2_numTwo + " + " + p2_numThree + " = " + (p2_numOne + p2_numTwo + p2_numThree));
        System.out.println(p2_numOne + " * " + p2_numTwo + " * " + p2_numThree + " = " + (p2_numOne * p2_numTwo * p2_numThree));
    }
}
