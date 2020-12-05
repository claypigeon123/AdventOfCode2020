package day5;

public class BoardingPass {
    private int id;

    public static BoardingPass makeBoardingPass(String data) {
        byte rowMax = 127, rowMin = 0;
        byte columnMax = 8, columnMin = 0;

        for (int i = 0; i < data.length(); i++) {
            if (i < 7) {
                if (data.charAt(i) == 'F') {
                    rowMax = (byte)((rowMin + rowMax) / 2);
                } else {
                    rowMin = (byte)((rowMin + rowMax) / 2);
                }
            } else {
                if (data.charAt(i) == 'L') {
                    columnMax = (byte)((columnMax + columnMin) / 2);
                } else {
                    columnMin = (byte)((columnMax + columnMin) / 2);
                }
            }
        }

        return new BoardingPass(((rowMax * 8) + (columnMax - 1)));
    }

    private BoardingPass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
