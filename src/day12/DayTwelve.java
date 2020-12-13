package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwelve {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input12.txt"));

        List<Command> commands = new ArrayList<>();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            commands.add(new Command(line.charAt(0), Integer.parseInt(line.substring(1))));
        }

        // P1
        Ship ship = new Ship();
        commands.forEach(ship::executeCommand);

        System.out.println("\nPart 1:");
        System.out.println("Manhattan distance from original position: " + (Math.abs(ship.getHorizontalPosition()) + Math.abs(ship.getVerticalPosition())));

        // P2
        Ship waypointShip = new WaypointShip();
        commands.forEach(waypointShip::executeCommand);

        System.out.println("\nPart 2:");
        System.out.println("Manhattan distance from original position: " + (Math.abs(waypointShip.getHorizontalPosition()) + Math.abs(waypointShip.getVerticalPosition())));
    }
}
