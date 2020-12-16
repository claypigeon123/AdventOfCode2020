package day14;

import java.util.List;

public class ProgramV2 extends ProgramV1 {

    @Override
    public long execute(List<String> input) {
        for (String line : input) {
            String[] split = line.split(" = ");
            long address = parseCommand(split[0]);

            if (address == -1) {
                mask = split[1];
                continue;
            }

            commitToMemory(address, Long.parseLong(split[1]));
        }

        return countValuesInMemory();
    }

    @Override
    protected void commitToMemory(long address, long val) {
        String binaryAddress = applyMaskToAddress(convertToBinary(address, LENGTH));
        long count = binaryAddress.chars().filter(c -> c == 'X').count();

        for (int i = 0; i < Math.pow(2, count); i++) {
            String binaryI = convertToBinary(Integer.valueOf(i).longValue(), Long.valueOf(count).intValue());

            StringBuilder binaryAddressBuilder = new StringBuilder(binaryAddress);
            for (int k = 0; k < binaryI.length(); k++)  {
                binaryAddressBuilder.setCharAt(binaryAddressBuilder.lastIndexOf("X"), binaryI.charAt(k));
            }

            long addressCase = Long.parseLong(binaryAddressBuilder.toString(), 2);
            memory.put(addressCase, val);
        }
    }

    protected String applyMaskToAddress(String binaryAddress) {
        StringBuilder maskedBinaryAddress = new StringBuilder(binaryAddress);
        for (int i = 0; i < LENGTH; i++) {
            if (mask.charAt(i) != '0') {
                maskedBinaryAddress.setCharAt(i, mask.charAt(i));
            }
        }
        return maskedBinaryAddress.toString();
    }
}
