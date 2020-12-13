package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayEleven {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input11.txt"));

        SeatMap[] seatMaps = new SeatMap[]{new SeatMap(), new SeatMap()};
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            seatMaps[0].addRow(line);
            seatMaps[1].addRow(line);
        }

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The number of occupied seats after calculations: " + seatMaps[0].performSeatingAndCountOccupied(1));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The number of occupied seats after calculations: " + seatMaps[1].performSeatingAndCountOccupied(2));
    }
}
