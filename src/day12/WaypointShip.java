package day12;

public class WaypointShip extends Ship {
    private int waypointHorizontalPosition;
    private int waypointVerticalPosition;

    public WaypointShip() {
        super();
        this.waypointHorizontalPosition = 10;
        this.waypointVerticalPosition = 1;
    }

    @Override
    public void executeCommand(Command cmd) {
        switch (cmd.getAction()) {
            case 'N':
                waypointVerticalPosition += cmd.getValue();
                break;
            case 'S':
                waypointVerticalPosition -= cmd.getValue();
                break;
            case 'E':
                waypointHorizontalPosition += cmd.getValue();
                break;
            case 'W':
                waypointHorizontalPosition -= cmd.getValue();
                break;
            case 'L':
                turn(Math.floorMod(Math.negateExact(cmd.getValue()), 360));
                break;
            case 'R':
                turn(Math.floorMod(cmd.getValue(), 360));
                break;
            case 'F':
                move(cmd.getValue());
                break;
        }
    }

    @Override
    protected void turn(int value) {
        switch (value) {
            case 90:
                int temp90 = waypointHorizontalPosition;
                waypointHorizontalPosition = waypointVerticalPosition;
                waypointVerticalPosition = -temp90;
                break;
            case 180:
                waypointHorizontalPosition = -waypointHorizontalPosition;
                waypointVerticalPosition = -waypointVerticalPosition;
                break;
            case 270:
                int temp270 = waypointHorizontalPosition;
                waypointHorizontalPosition = -waypointVerticalPosition;
                waypointVerticalPosition = temp270;
                break;
        }
    }

    @Override
    protected void move(int value) {
        for (int i = 0; i < value; i++) {
            horizontalPosition += waypointHorizontalPosition;
            verticalPosition += waypointVerticalPosition;
        }
    }
}
