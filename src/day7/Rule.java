package day7;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private String bagName;
    private List<Bag> bags;

    public Rule(String bagName) {
        this.bagName = bagName;
        bags = new ArrayList<>();
    }

    public String getBagName() {
        return bagName;
    }
    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    public List<Bag> getBags() {
        return bags;
    }
    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }
}
