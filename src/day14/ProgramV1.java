package day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramV1 {
    protected static final int LENGTH = 36;

    protected Map<Long, Long> memory;
    protected String mask;

    public ProgramV1() {
        memory = new HashMap<>();
        mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
    }

    public long execute(List<String> input) {
        for (String line : input) {
            String[] split = line.split(" = ");
            long address = parseCommand(split[0]);

            if (address == -1) {
                mask = split[1];
                continue;
            }

            commitToMemory(address, applyMaskToValue(Long.parseLong(split[1])));
        }

        return countValuesInMemory();
    }

    // Returns -1 if cmd is "mask" or the memory address if cmd is "mem"
    protected int parseCommand(String cmd) {
        if (cmd.equals("mask")) {
            return -1;
        } else {
            return Integer.parseInt(cmd.replaceAll("mem\\[", "").replaceAll("]", ""));
        }
    }

    protected long applyMaskToValue(Long val) {
        StringBuilder binaryVal = new StringBuilder(convertToBinary(val, LENGTH));

        for (int i = 0; i < LENGTH; i++) {
            if (mask.charAt(i) != 'X') {
                binaryVal.setCharAt(i, mask.charAt(i));
            }
        }

        return Long.parseLong(binaryVal.toString(), 2);
    }

    protected Long countValuesInMemory() {
        return memory.values().stream().mapToLong(l -> l).sum();
    }

    protected String convertToBinary(Long val, int pad) {
        return String.format("%1$" + pad + "s", Long.toBinaryString(val)).replaceAll(" ", "0");
    }

    protected void commitToMemory(long address, long val) {
        memory.put(address, val);
    }
}
