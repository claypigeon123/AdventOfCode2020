package daytwo;

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
            String line = scan.nextLine();
            String[] data = new String[4];
            Scanner lineScanner = new Scanner(line);

            lineScanner.useDelimiter("-");
            data[0] = lineScanner.next();

            lineScanner.useDelimiter(" ");
            data[1] = lineScanner.next();

            lineScanner.useDelimiter(":");
            data[2] = lineScanner.next();

            lineScanner.useDelimiter(": ");
            data[3] = lineScanner.next();

            input.add(Entry.makeEntry(
                    Integer.parseInt(data[0]),
                    Math.abs(Integer.parseInt(data[1])),
                    data[2].charAt(1),
                    data[3]
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
