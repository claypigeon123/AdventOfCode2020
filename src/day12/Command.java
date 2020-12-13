package day12;

public class Command {
    private final char action;
    private final int value;

    public Command(char action, int value) {
        this.action = action;
        this.value = value;
    }

    public char getAction() {
        return action;
    }
    public int getValue() {
        return value;
    }
}
