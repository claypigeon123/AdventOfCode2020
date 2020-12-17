package day16;

import util.InclusiveIntegerRange;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DaySixteen {

    private static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input16.txt"));

        Map<Field, List<InclusiveIntegerRange>> rules = new HashMap<>();

        String line = scan.nextLine();
        while (!line.isBlank()) {
            String[] split = line.split(": ");
            String[] rawRanges = split[1].split(" or ");
            List<InclusiveIntegerRange> ranges = new ArrayList<>();

            for (String s : rawRanges) {
                String[] values = s.split("-");
                ranges.add(new InclusiveIntegerRange(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
            }
            rules.put(new Field(split[0]), ranges);

            line = scan.nextLine();
        }


        scan.nextLine();
        line = scan.nextLine();
        Ticket myTicket = new Ticket(line.split(","));


        scan.nextLine(); scan.nextLine();

        while (scan.hasNextLine()) {
            tickets.add(new Ticket(scan.nextLine().split(",")));
        }

        scan.close();

        // P1
        System.out.println("\nPart 1:");
        System.out.println("The ticket scanning error rate is: " + calcErrorRate(rules));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The departure values multiplied: " + findOrderAndCalcProduct(myTicket, rules));
    }

    private static int calcErrorRate(Map<Field, List<InclusiveIntegerRange>> rules) {
        List<Integer> invalidValues = new ArrayList<>();
        List<Ticket> invalidTickets = new ArrayList<>();

        for (Ticket ticket : tickets) {
            boolean invalidTicket = false;
            for (Integer value : ticket.getValues()) {
                boolean valid = false;
                for (List<InclusiveIntegerRange> ranges : rules.values()) {
                    for (InclusiveIntegerRange range : ranges) {
                        if (range.isWithinRange(value)) {
                            valid = true;
                            break;
                        }
                    }
                }
                if (!valid) {
                    invalidValues.add(value);
                    invalidTicket = true;
                    break;
                }
            }
            if (invalidTicket) invalidTickets.add(ticket);
        }

        tickets.removeAll(invalidTickets);
        return invalidValues.stream().mapToInt(i -> i).sum();
    }

    // Big mess, but I'm sticking with the data structure I used for part 1
    private static long findOrderAndCalcProduct(Ticket myTicket, Map<Field, List<InclusiveIntegerRange>> rules) {
        tickets.add(myTicket);
        List<Field> possibleFields = rules.keySet().stream().filter(r -> r.getOrder() == -1).collect(Collectors.toList());
        while (possibleFields.size() > 0) {
            for (int currentFieldIndex = 0; currentFieldIndex < rules.size(); currentFieldIndex++) {
                for (Map.Entry<Field, List<InclusiveIntegerRange>> rule : rules.entrySet()) {
                    for (Ticket ticket : tickets) {
                        if (!rule.getValue().get(0).isWithinRange(ticket.getValues().get(currentFieldIndex)) && !rule.getValue().get(1).isWithinRange(ticket.getValues().get(currentFieldIndex))) {
                            possibleFields.remove(rule.getKey());
                            break;
                        }
                    }
                }
                if (possibleFields.size() == 1) {
                    List<Field> finalPossibleFields = possibleFields;
                    rules.keySet().stream().filter(r -> r.getName().equals(finalPossibleFields.stream().findFirst().get().getName())).findFirst().get().setOrder(currentFieldIndex);
                }

                possibleFields = rules.keySet().stream().filter(r -> r.getOrder() == -1).collect(Collectors.toList());
            }
        }

        Set<Integer> indexes = new HashSet<>();
        for (Field field : rules.keySet()) {
            if (field.getName().contains("departure")) {
                indexes.add(field.getOrder());
            }
        }

        long product = 1;
        for (int index : indexes) {
            product *= myTicket.getValues().get(index);
        }

        return product;
    }
}
