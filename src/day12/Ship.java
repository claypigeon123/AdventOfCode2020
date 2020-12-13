package day12;

public class Ship {
    protected int direction;

    protected int horizontalPosition;
    protected int verticalPosition;

    public Ship() {
        this.direction = 90;
        this.horizontalPosition = 0;
        this.verticalPosition = 0;
    }

    public void executeCommand(Command cmd) {
        switch (cmd.getAction()) {
            case 'N':
                verticalPosition += cmd.getValue();
                break;
            case 'S':
                verticalPosition -= cmd.getValue();
                break;
            case 'E':
                horizontalPosition += cmd.getValue();
                break;
            case 'W':
                horizontalPosition -= cmd.getValue();
                break;
            case 'L':
                turn(Math.negateExact(cmd.getValue()));
                break;
            case 'R':
                turn(cmd.getValue());
                break;
            case 'F':
                move(cmd.getValue());
                break;
        }
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }
    public int getVerticalPosition() {
        return verticalPosition;
    }

    protected void turn(int value) {
        direction = Math.floorMod(direction + value, 360);
    }

    protected void move(int value) {
        switch (direction) {
            case 0:
                verticalPosition += value;
                break;
            case 90:
                horizontalPosition += value;
                break;
            case 180:
                verticalPosition -= value;
                break;
            case 270:
                horizontalPosition -= value;
                break;
        }
    }
}
