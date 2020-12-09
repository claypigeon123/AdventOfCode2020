package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DayNine {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input9.txt");
        Scanner scan = new Scanner(file);

        List<Long> data = new ArrayList<>();

        while (scan.hasNextLine()) {
            data.add(Long.parseLong(scan.nextLine()));
        }

        // P1
        long oddOneOut = findOddOneOut(data, 25);

        System.out.println("\nPart 1:");
        System.out.println("The first number that doesn't adhere to the rules is: " + oddOneOut);

        // P2
        long encryptionWeakness = findEncryptionWeakness(data, oddOneOut);

        System.out.println("\nPart 2:");
        System.out.println("The encryption weakness is: " + encryptionWeakness);
    }

    private static long findOddOneOut(List<Long> data, int offset) {
        long oddOneOut = 0;
        // Offset i by the specified value to skip the ignored n. of entries
        for (int i = offset; i < data.size(); i++) {
            long current = data.get(i);

            // Get the 'offset' number of entries before the current entry ...
            List<Long> prev = new ArrayList<>();
            for (int k = i - offset; k < i; k++) {
                // ... and add them to the list
                prev.add(data.get(k));
            }

            // Check each element against every other element
            boolean valid = false;
            for (long a : prev) {
                for (long b : prev) {
                    if ((a + b) == current) {
                        // If any two elements sum up to the value of the 'current' entry, ...
                        // ... then it's not what we're looking for
                        valid = true;
                        break;
                    }
                }

                if (valid) break;
            }

            // When we've found the invalid entry, this function has completed its purpose
            if (!valid) {
                oddOneOut = current;
                break;
            }
        }

        return oddOneOut;
    }

    private static long findEncryptionWeakness(List<Long> data, long oddOneOut) {
        List<Long> sublist = new ArrayList<>();

        // For each element in 'data' ...
        for (int i = 0; i < data.size(); i++) {
            // ... sum the next entries ...
            boolean found = false;
            long sum = data.get(i);
            for (int k = (i + 1); k < data.size(); k++) {
                sum += data.get(k);
                if (sum == oddOneOut) { // until the total reaches 'oddOneOut' (good!) ...
                    sublist = data.subList(i, k + 1);
                    found = true;
                    break;
                } else if (sum > oddOneOut) { // ... or goes over it (bad!)
                    break;
                }
            }
            // If we've found the range of numbers we're looking for, break the loop
            if (found) {
                break;
            }
        }

        // Find min ...
        long min = sublist.stream().mapToLong(l -> l).min().orElseThrow(NoSuchElementException::new);
        // ... and max elements of the found sublist ...
        long max = sublist.stream().mapToLong(l -> l).max().orElseThrow(NoSuchElementException::new);

        // ... then return their sum
        return min + max;
    }
}
