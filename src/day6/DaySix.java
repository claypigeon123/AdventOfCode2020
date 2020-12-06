package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class DaySix {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input6.txt");
        Scanner scan = new Scanner(file);

        List<List<String>> groups = new ArrayList<>();

        List<String> currentGroup = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();

            if (!line.isBlank()) {
                currentGroup.add(line);
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
            }
        }
        groups.add(currentGroup);    // add last group

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The sum of positive answers in groups (OR): " + countPositivesOR(groups));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The sum of positive answers in groups (AND): " + countPositivesAND(groups));
    }

    private static int countPositivesOR(List<List<String>> groups) {
        int count = 0;

        for (List<String> answers : groups) {
            Set<Character> set = getUniqueChars(answers);
            count += set.size();
        }

        return count;
    }

    private static int countPositivesAND(List<List<String>> groups) {
        int count = 0;

        for (List<String> answers : groups) {
            Set<Character> set = getUniqueChars(answers);

            for (Character c : set) {
                boolean valid = true;
                for (String ans : answers) {
                    if (!ans.contains(c.toString())) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    count++;
                }
            }
        }

        return count;
    }

    private static Set<Character> getUniqueChars(List<String> answers) {
        Set<Character> set = new HashSet<>();

        for (String ans : answers) {
            for (char c : ans.toCharArray()) {
                set.add(c);
            }
        }

        return set;
    }
}
