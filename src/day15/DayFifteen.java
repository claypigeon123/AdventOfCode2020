package day15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayFifteen {
    public static void main(String[] args) {
        List<Integer> startingNumbers = List.of(18, 11, 9, 0, 5, 1);

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The 2020th number spoken is: " + partOne(startingNumbers, 2020));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The 30000000th number spoken is: " + partOne(startingNumbers, 30000000));
    }

    private static int partOne(List<Integer> startingNumbers, int iterations) {
        Map<Integer, NumberInfo> data = new HashMap<>();

        for (int i = 0; i < startingNumbers.size(); i++) {
            data.put(startingNumbers.get(i), new NumberInfo(i + 1));
        }

        int lastNumberHeard = startingNumbers.get(startingNumbers.size() - 1);
        
        for (int i = startingNumbers.size(); i < iterations; i++) {
            NumberInfo currentInfo = data.get(lastNumberHeard);
            if (currentInfo.hasBeenMoreThanOnce()) {
                int key = currentInfo.getDifference();
                lastNumberHeard = key;
                if (data.containsKey(key)) {
                    data.get(key).setLastSpoken(i + 1);
                } else {
                    data.put(key, new NumberInfo(i + 1));
                }
            } else {
                data.get(0).setLastSpoken(i + 1);
                lastNumberHeard = 0;
            }
        }

        return lastNumberHeard;
    }
}
