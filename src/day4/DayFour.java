package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class DayFour {

    private static final String[] REQUIRED_FIELDS = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final String[] OPTIONAL_FIELDS = new String[]{"cid"};

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("inputs/input4.txt");
        Scanner scan = new Scanner(file);
        scan.useDelimiter(Pattern.compile("^\\s*$", Pattern.MULTILINE));

        List<Map<String, String>> input = new ArrayList<>();

        while (scan.hasNext()) {
            String[] rawData = scan.next().replaceAll("\r\n", " ").split(" ");
            Map<String, String> data = new HashMap<>();

            for (String item : rawData) {
                if (!item.isEmpty()) {
                    String[] pair = item.split(":");
                    data.put(pair[0], pair[1]);
                }
            }

            input.add(data);
        }

        // P1
        List<Map<String, String>> wholePassports = new ArrayList<>();

        for (Map<String, String> passport : input) {
            if (passport.keySet().containsAll(List.of(REQUIRED_FIELDS))) {
                wholePassports.add(passport);
            }
        }

        System.out.println("\nPart 1:");
        System.out.println("Passports that have all required fields: " + wholePassports.size());

        // P2
        int validCount = 0;

        for (Map<String, String> passport : wholePassports) {
            // byr
            int byr = Integer.parseInt(passport.get("byr"));
            if (byr < 1920 || byr > 2002) continue;

            // iyr
            int iyr = Integer.parseInt(passport.get("iyr"));
            if (iyr < 2010 || iyr > 2020) continue;

            // eyr
            int eyr = Integer.parseInt(passport.get("eyr"));
            if (eyr < 2020 || eyr > 2030) continue;

            // hgt
            String hgt = passport.get("hgt");
            int hgtNum = Integer.parseInt(hgt.replaceAll(String.valueOf(Pattern.compile("in|cm")), ""));
            if (hgt.contains("cm")) {
                if (hgtNum < 150 || hgtNum > 193) continue;
            } else if (hgt.contains("in")) {
                if (hgtNum < 59 || hgtNum > 76) continue;
            } else continue;

            // hcl
            String hcl = passport.get("hcl");
            if (hcl.charAt(0) != '#' || hcl.length() != 7) continue;

            // ecl
            String ecl = passport.get("ecl");
            if (!ecl.equals("amb") && !ecl.equals("blu") && !ecl.equals("brn") && !ecl.equals("gry") && !ecl.equals("grn") && !ecl.equals("hzl") && !ecl.equals("oth")) continue;

            // pid
            String pid = passport.get("pid");
            if (pid.length() != 9) continue;

            validCount++;
        }

        System.out.println("\nPart 2:");
        System.out.println("Passports that have all required fields & valid data: " + validCount);
    }
}
