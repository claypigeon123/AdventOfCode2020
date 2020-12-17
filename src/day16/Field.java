package day16;

public class Field {
    private String name;
    private int order;

    public Field(String name) {
        this.name = name;
        order = -1;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
}
