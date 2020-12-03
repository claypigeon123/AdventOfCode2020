package daythree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {

    private static final char TREE = '#';

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input3.txt");
        Scanner scan = new Scanner(file);

        List<Character[]> rows = new ArrayList<>();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Character[] row = new Character[line.length()];

            for (int i = 0; i < line.length(); i++) {
                row[i] = line.charAt(i);
            }

            rows.add(row);
        }

        // P1
        int partOneResult = partOne(rows);

        // P2
        partTwo(rows, partOneResult);
    }

    private static int partOne(List<Character[]> rows) {

        int treeCount = countTrees(rows, 3, 1);

        System.out.println("\nPart 1:");
        System.out.println("Encountered trees: " + treeCount);
        return treeCount;
    }

    private static void partTwo(List<Character[]> rows, int partOneResult) {
        Integer[] results = new Integer[5];
        results[0] = partOneResult;
        results[1] = countTrees(rows, 1, 1);
        results[2] = countTrees(rows, 5, 1);
        results[3] = countTrees(rows, 7, 1);
        results[4] = countTrees(rows, 1, 2);

        long finalResult = 1;
        for (Integer result : results) {
            finalResult *= result;
        }

        System.out.println("\nPart 2:");
        System.out.println("Multiplying the results of " + results.length + " paths: " + finalResult);
    }

    private static int countTrees(List<Character[]> rows, int stepsRight, int stepsDown) {
        int treeCount = 0, x = 0;
        for (int y = 0; y < rows.size(); y += stepsDown) {
            if (x >= rows.get(y).length) {
                x = (x - rows.get(y).length);
            }
            if (rows.get(y)[x] == TREE) {
                treeCount++;
            }

            x+= stepsRight;
        }

        return treeCount;
    }
}
