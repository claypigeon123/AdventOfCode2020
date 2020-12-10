package day10;

import util.CounterMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DayTen {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input10.txt"));

        List<Integer> adapterRatings = new ArrayList<>();

        // Read the adapter ratings ...
        while (scan.hasNextLine()) {
            adapterRatings.add(Integer.parseInt(scan.nextLine()));
        }
        // ... and add 0 (the outlet) and the max+3 value (the device's inbuilt adapter) ...
        adapterRatings.addAll(List.of(0, ((adapterRatings.stream().mapToInt(n -> n).max().orElse(0)) + 3)));
        // ... then sort the list.
        adapterRatings.sort(Comparator.naturalOrder());

        // P1
        int[] differences = countJoltDifferences(adapterRatings);
        System.out.println("\nPart 1:");
        System.out.println("1-jolt differences multiplied by 3-jolt differences: " + differences[0] * differences[2]);

        // P2
        System.out.println("\nPart 2:");
        System.out.println("Number of possible combinations: " + countCases(adapterRatings));
    }

    private static int[] countJoltDifferences(List<Integer> adapterRatings) {
        // Array of size 3, 1-jolt differences, 2-jolt differences and 3-jolt differences respectively
        int[] differences = new int[]{0, 0, 0};

        // For each adapter rating ...
        for (int i = 0; i < adapterRatings.size() - 1; i++) {
            // ... calculate the difference between it and the next rating ...
            int difference = adapterRatings.get(i + 1) - adapterRatings.get(i);

            // ... and record the difference in the array ...
            if (difference == 1) {
                differences[0]++;
            } else if (difference == 2) {
                differences[1]++;
            } else if (difference == 3) {
                differences[2]++;
            }
        }

        // ... then return the differences.
        return differences;
    }

    private static long countCases(List<Integer> adapterRatings) {
        // Custom hash map helps us to count the number of routes, see implementation in util package
        CounterMap<Long> routes = new CounterMap<>();

        // Add the device's inbuilt adapter rating to the map
        routes.increment((long)(adapterRatings.get(adapterRatings.size() - 1)));


        // Start the outer loop backwards, skipping over the inbuilt adapter rating ...
        for (int i = adapterRatings.size() - 2; i >= 0; i--) {
            // Start the inner loop forwards, from the outer loop's current position + 1, check for maximum 3 loops ...
            for (int k = i + 1; k < adapterRatings.size() && k <= i + 3; k++) {
                // ... and if the difference between the two ratings is <= 3, as per the rules ...
                if (adapterRatings.get(k) - adapterRatings.get(i) <= 3) {
                    // ... then record the route in the custom map accordingly
                    routes.increment((long)adapterRatings.get(i), routes.get((long)adapterRatings.get(k)));
                }
            }
        }

        // The value that belongs to the 0 key in the custom map is the ...
        // ... most number of possible (and valid) combinations that exist.
        return routes.get(0L);
    }
}
