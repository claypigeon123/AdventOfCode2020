package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DayEleven {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input11.txt"));

        SeatMap seatMap = new SeatMap();
        while (scan.hasNextLine()) {
            seatMap.addRow(scan.nextLine());
        }

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The number of occupied seats after calculations: " + seatMap.performSeatingAndCountOccupied());
    }
}
