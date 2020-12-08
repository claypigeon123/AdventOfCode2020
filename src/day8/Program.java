package day8;

import java.util.List;

public class Program {
    public Result runProgram(List<Instruction> instructions) {
        long accumulator = 0;
        boolean isCyclical = false;

        for (int i = 0; i < instructions.size(); i++) {
            Instruction instruction = instructions.get(i);
            if (instruction.hasRun()) {
                isCyclical = true;
                break;
            }
            long arg = instruction.getArgument();

            switch (instruction.getCommand()) {
                case "acc":
                    accumulator += arg;
                    break;
                case "jmp":
                    i += arg - 1;
                    break;
                case "nop":
                    break;
            }

            instruction.setHasRun(true);
        }

        cleanup(instructions);
        return new Result(isCyclical, accumulator);
    }

    private void cleanup(List<Instruction> instructions) {
        for (Instruction i : instructions) {
            i.setHasRun(false);
        }
    }
}
