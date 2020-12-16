package day15;

public class NumberInfo {
    private int lastSpoken;
    private Integer lastSpokenBeforeLastSpoken;
    private boolean hasBeenMoreThanOnce;

    public NumberInfo(int lastSpoken) {
        this.lastSpoken = lastSpoken;
        this.hasBeenMoreThanOnce = false;
        this.lastSpokenBeforeLastSpoken = null;
    }

    public int getDifference() {
        return lastSpoken - lastSpokenBeforeLastSpoken;
    }

    public void setLastSpoken(int lastSpoken) {
        lastSpokenBeforeLastSpoken = this.lastSpoken;
        this.lastSpoken = lastSpoken;
        hasBeenMoreThanOnce = true;
    }

    public boolean hasBeenMoreThanOnce() {
        return hasBeenMoreThanOnce;
    }
}
