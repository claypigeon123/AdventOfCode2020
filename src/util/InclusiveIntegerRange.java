package util;

import java.util.List;
import java.util.stream.IntStream;

public class InclusiveIntegerRange {
    private final int min;
    private final int max;

    public InclusiveIntegerRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public boolean isWithinRange(int num) {
        return num >= min && num <= max;
    }

    public boolean areAllWithinRange(List<Integer> nums) {
        for (int num : nums) {
            if (num > max || num < min) {
                return false;
            }
        }

        return true;
    }

    public boolean areAllWithinRange(IntStream intStream) {
        return intStream.allMatch(i -> i >= min && i <= max);
    }

    public int getMin() {
        return min;
    }
    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "MIN: " + min + " & MAX: " + max;
    }
}
