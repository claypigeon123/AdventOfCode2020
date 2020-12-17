package day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ticket {
    private List<Integer> values;

    public Ticket() {
        this.values = new ArrayList<>();
    }

    public Ticket(String[] rawValues) {
        this.values = new ArrayList<>();
        Arrays.stream(rawValues).mapToInt(Integer::parseInt).forEach(i -> values.add(i));
    }

    public List<Integer> getValues() {
        return values;
    }
    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
