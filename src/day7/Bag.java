package day7;

public class Bag {
    private String name;
    private int num;

    public Bag(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}
