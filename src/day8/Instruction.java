package day8;

public class Instruction {
    private String command;
    private long argument;
    private boolean hasRun;

    public Instruction(String command, long argument) {
        this.command = command;
        this.argument = argument;
        this.hasRun = false;
    }

    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    public long getArgument() {
        return argument;
    }
    public void setArgument(long argument) {
        this.argument = argument;
    }

    public boolean hasRun() {
        return hasRun;
    }
    public void setHasRun(boolean hasRun) {
        this.hasRun = hasRun;
    }
}
