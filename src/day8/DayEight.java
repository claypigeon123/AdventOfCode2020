package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayEight {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input8.txt");
        Scanner scan = new Scanner(file);

        List<Instruction> instructions = new ArrayList<>();

        while (scan.hasNextLine()) {
            String[] split = scan.nextLine().split(" ");
            instructions.add(new Instruction(split[0], Long.parseLong(split[1])));
        }

        Program program = new Program();

        // P1
        System.out.println("\nPart 1:");
        Result resultP1 = program.runProgram(List.copyOf(instructions));
        System.out.println("Accumulator upon reaching the first duplicate instruction: " + resultP1.getAccumulator());

        // P2
        Result resultP2 = new Result();

        for (Instruction instruction : instructions) {
            switchCommand(instruction);
            resultP2 = program.runProgram(List.copyOf(instructions));
            if (resultP2.isCyclical()) {
                switchCommand(instruction);
            } else {
                break;
            }
        }

        System.out.println("\nPart 2:");
        System.out.println("Value of the accumulator after fixing the program: " + resultP2.getAccumulator());
    }

    private static void switchCommand(Instruction i) {
        if (i.getCommand().equals("jmp")) {
            i.setCommand("nop");
        } else if (i.getCommand().equals("nop")) {
            i.setCommand("jmp");
        }
    }
}
