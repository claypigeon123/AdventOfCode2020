package day8;

public class Result {
    private final boolean isCyclical;
    private final long accumulator;

    public Result(boolean isCyclical, long accumulator) {
        this.isCyclical = isCyclical;
        this.accumulator = accumulator;
    }

    public Result() {
        this.isCyclical = false;
        this.accumulator = 0;
    }

    public boolean isCyclical() {
        return isCyclical;
    }
    public long getAccumulator() {
        return accumulator;
    }
}
