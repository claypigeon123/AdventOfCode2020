package day11;

import java.util.ArrayList;
import java.util.List;

public class SeatMap {
    private List<List<Character>> rows;

    public SeatMap() {
        this.rows = new ArrayList<>();
    }

    public void addRow(String line) {
        List<Character> row = new ArrayList<>();
        for (char c : line.toCharArray()) {
            row.add(c);
        }
        rows.add(row);
    }

    public int performSeatingAndCountOccupied(int part) {
        performSeating(part == 1 ? 4 : 5);
        return countOccupiedSeats();
    }

    private void performSeating(int tolerance) {
        int changes;
        List<List<Character>> newPlan;
        do {
            changes = 0;
            newPlan = makeDeepCopy(rows);
            for (int y = 0; y < rows.size(); y++) {
                for (int x = 0; x < rows.get(y).size(); x++) {
                    List<List<Character>> result = handleSeat(y, x, newPlan, tolerance);
                    if (result != null) {
                        changes++;
                        newPlan = result;
                    }
                }
            }
            rows = makeDeepCopy(newPlan);
        } while (changes != 0);
    }

    private List<List<Character>> handleSeat(int seatY, int seatX, List<List<Character>> newPlan, int tolerance) {
        char seat = rows.get(seatY).get(seatX);

        if (seat == '.') {
            return null;
        }

        int adjacentOccupiedSeats = tolerance == 4 ? countAdjacentOccupiedSeatsPart1(seatY, seatX) : countAdjacentOccupiedSeatsPart2(seatY, seatX);

        if (seat == 'L') {
            if (adjacentOccupiedSeats == 0) {
                newPlan.get(seatY).set(seatX, '#');
                return newPlan;
            }
        } else if (seat == '#') {
            if (adjacentOccupiedSeats >= tolerance) {
                newPlan.get(seatY).set(seatX, 'L');
                return newPlan;
            }
        }

        return null;
    }

    private int countAdjacentOccupiedSeatsPart1(int seatY, int seatX) {
        int adjacentOccupiedSeats = 0;

        int fromY = seatY == 0 ? 0 : seatY - 1;
        int toY = seatY == rows.size() - 1 ? seatY + 1 : seatY + 2;

        int fromX = seatX == 0 ? 0 : seatX - 1;
        int toX = seatX == rows.get(seatY).size() - 1 ? seatX + 1 : seatX + 2;

        for (int y = fromY; y < toY; y++) {
            for (int x = fromX; x < toX; x++) {
                if (y == seatY && x == seatX) continue;

                if (rows.get(y).get(x) == '#') {
                    adjacentOccupiedSeats++;
                }
            }
        }

        return adjacentOccupiedSeats;
    }

    private int countAdjacentOccupiedSeatsPart2(int seatY, int seatX) {
        int adjacentOccupiedSeats = 0;

        // Count horizontal left
        int x = seatX, y = seatY;
        while (x >= 0) {
            if (rows.get(y).get(x) == '#' && x != seatX) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && x != seatX) break;
            x--;
        }

        // Count horizontal right
        x = seatX; y = seatY;
        while (x < rows.get(y).size()) {
            if (rows.get(y).get(x) == '#' && x != seatX) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && x != seatX) break;
            x++;
        }

        // Count vertical up
        x = seatX; y = seatY;
        while (y >= 0) {
            if (rows.get(y).get(x) == '#' && y != seatY) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && y != seatY) break;
            y--;
        }

        // Count vertical down
        x = seatX; y = seatY;
        while (y < rows.size()) {
            if (rows.get(y).get(x) == '#' && y != seatY) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && y != seatY) break;
            y++;
        }

        // Count diagonal \ up
        x = seatX; y = seatY;
        while (y >= 0 && x >= 0) {
            if (rows.get(y).get(x) == '#' && (y != seatY && x != seatX)) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && (y != seatY && x != seatX)) break;
            y--;
            x--;
        }

        // Count diagonal \ down
        x = seatX; y = seatY;
        while (y < rows.size() && x < rows.get(y).size()) {
            if (rows.get(y).get(x) == '#' && (y != seatY && x != seatX)) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && (y != seatY && x != seatX)) break;
            y++;
            x++;
        }

        // Count diagonal / up
        x = seatX; y = seatY;
        while (y >= 0 && x < rows.get(y).size()) {
            if (rows.get(y).get(x) == '#' && (y != seatY && x != seatX)) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && (y != seatY && x != seatX)) break;
            y--;
            x++;
        }

        // Count diagonal / down
        x = seatX; y = seatY;
        while (y < rows.size() && x >= 0) {
            if (rows.get(y).get(x) == '#' && (y != seatY && x != seatX)) {
                adjacentOccupiedSeats++;
                break;
            } else if (rows.get(y).get(x) == 'L' && (y != seatY && x != seatX)) break;
            y++;
            x--;
        }

        return adjacentOccupiedSeats;
    }

    private int countOccupiedSeats() {
        int count = 0;
        for (List<Character> row : rows) {
            for (char c : row) {
                if (c == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private List<List<Character>> makeDeepCopy(List<List<Character>> original) {
        List<List<Character>> copy = new ArrayList<>();
        for (List<Character> row : original) {
            copy.add(new ArrayList<>(row));
        }

        return copy;
    }

    public void display() {
        for (List<Character> row : rows) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }
}
