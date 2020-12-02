package daytwo;

class Entry {
    private boolean isValidPartOne;
    private boolean isValidPartTwo;

    static Entry makeEntry(int min, int max, char letter, String password) {
        // P1
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                count++;
            }
        }

        boolean isValidPartOne = false;
        if (count >= min && count <= max) {
            isValidPartOne = true;
        }

        // P2
        boolean isValidPartTwo = false;
        if (password.charAt(min - 1) == letter || password.charAt(max - 1) == letter) {
            isValidPartTwo = true;
        }
        if (password.charAt(min - 1) == letter && password.charAt(max - 1) == letter) {
            isValidPartTwo = false;
        }

        return new Entry(isValidPartOne, isValidPartTwo);
    }

    private Entry(boolean isValidPartOne, boolean isValidPartTwo) {
        this.isValidPartOne = isValidPartOne;
        this.isValidPartTwo = isValidPartTwo;
    }

    boolean isValidPartOne() {
        return isValidPartOne;
    }
    boolean isValidPartTwo() {
        return isValidPartTwo;
    }
}
