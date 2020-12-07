package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaySeven {

    private static final String MY_BAG = "shiny gold";
    private static List<Rule> rules = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input7.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            line = line.replace("contain ", "");
            line = line.replace(",", "");
            line = line.replace(".", "");
            String[] split = line.split(" ");

            Rule rule = new Rule(split[0] + " " + split[1]);

            if (line.contains("no other bags")) {
                rules.add(rule);
                continue;
            }

            for (int i = 3; i < split.length; i+= 4) {
                String bagName = split[i + 1] + " " + split[i + 2];
                Bag innerBag = new Bag(bagName, Integer.parseInt(split[i]));
                rule.getBags().add(innerBag);
            }

            rules.add(rule);
        }

        // P1
        System.out.println("\nPart 1:");
        System.out.println("This many colors can eventually contain at least one " + MY_BAG + " bag: " + rules.stream().filter(DaySeven::bagFits).count());

        // P2
        System.out.println("\nPart 2:");
        System.out.println("A single " + MY_BAG + " bag must contain this many other bags: " + countInnerBags(MY_BAG, 1));
    }

    private static boolean bagFits(Rule rule) {
        for (Bag bag : rule.getBags()) {
            if (bag.getName().equals(MY_BAG) || bagFits(rules.stream().filter(checkRule -> checkRule.getBagName().equals(bag.getName())).findFirst().orElseThrow())) {
                return true;
            }
        }
        return false;
    }

    private static int countInnerBags(String bagName, int multiplier) {
        int count = 0;

        for (Rule rule : rules) {
            if (rule.getBagName().equals(bagName)) {
                for (Bag bag : rule.getBags()) {
                    count += bag.getNum() * multiplier;
                    count += countInnerBags(bag.getName(), multiplier * bag.getNum());
                }
            }
        }
        return count;
    }
}
