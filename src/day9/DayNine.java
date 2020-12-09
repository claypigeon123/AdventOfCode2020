package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayNine {

    private static final int OFFSET = 25;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input9.txt");
        Scanner scan = new Scanner(file);

        List<Long> data = new ArrayList<>();

        while (scan.hasNextLine()) {
            data.add(Long.parseLong(scan.nextLine()));
        }

        // P1
        long oddOneOut = findOddOneOut(data);

        System.out.println("\nPart 1:");
        System.out.println("The first number that doesn't adhere to the rules is: " + oddOneOut);

        // P2
        long encryptionWeakness = findEncryptionWeakness(data, oddOneOut);

        System.out.println("\nPart 2:");
        System.out.println("The encryption weakness is: " + encryptionWeakness);
    }

    private static long findOddOneOut(List<Long> data) {
        long oddOneOut = 0;
        for (int i = OFFSET; i < data.size(); i++) {
            long current = data.get(i);
            List<Long> prev = new ArrayList<>();
            for (int k = i - OFFSET; k < i; k++) {
                prev.add(data.get(k));
            }
            boolean valid = false;

            for (long a : prev) {
                for (long b : prev) {
                    if ((a + b) == current) {
                        valid = true;
                        break;
                    }
                }

                if (valid) break;
            }

            if (!valid) {
                oddOneOut = current;
            }
        }

        return oddOneOut;
    }

    private static long findEncryptionWeakness(List<Long> data, long oddOneOut) {
        List<Long> sublist = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            long sum = data.get(i);
            for (int k = (i + 1); k < data.size(); k++) {
                sum += data.get(k);
                if (sum == oddOneOut) {
                    sublist = data.subList(i, k + 1);
                } else if (sum > oddOneOut) {
                    break;
                }
            }
        }

        long min = findSmallestLong(sublist);
        long max = findBiggestLong(sublist);


        return min + max;
    }

    private static long findSmallestLong(List<Long> data) {
        long min = Long.MAX_VALUE;
        for (long x : data) {
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    private static long findBiggestLong(List<Long> data) {
        long max = Long.MIN_VALUE;
        for (long x : data) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }
}
