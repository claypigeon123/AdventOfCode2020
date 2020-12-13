package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayThirteen {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("inputs/input13.txt"));

        long timestamp = Long.parseLong(scan.nextLine());
        String[] rawIds = scan.nextLine().split(",");

        // P1
        System.out.println("\nPart 1:");
        System.out.println("ID * Wait time = " + partOne(timestamp, rawIds));

        // P2
        System.out.println("\nPart 2:");
        System.out.println("The earliest timestamp: " + partTwo(rawIds));
    }

    private static int partOne(long timestamp, String[] rawIds) {
        List<Integer> buses = new ArrayList<>();
        for (String id : rawIds) {
            if (!id.equals("x")) {
                buses.add(Integer.parseInt(id));
            }
        }

        Long departsAt = null;
        int myBusID = 0;

        for (long i = timestamp; departsAt == null; i++) {
            for (int ID : buses) {
                if (i % ID == 0) {
                    departsAt = i;
                    myBusID = ID;
                    break;
                }
            }
        }

        return (int)(myBusID * (departsAt - timestamp));
    }

    private static BigInteger partTwo(String[] rawIDs) {
        List<BigInteger> crtValues = new ArrayList<>();
        for (int i = 0; i < rawIDs.length; i++) {
            if (rawIDs[i].equals("x")) {
                continue;
            }

            BigInteger val = BigInteger.valueOf(0 - i);
            val = val.mod(BigInteger.valueOf(Long.parseLong(rawIDs[i])));
            crtValues.add(val);
        }

        BigInteger[] busIDs = new BigInteger[0];
        for (String str : rawIDs) {
            if (str.equals("x")) continue;
            BigInteger[] newArray = new BigInteger[busIDs.length + 1];
            newArray[newArray.length - 1] = new BigInteger(str);
            for (int i = 0; i < busIDs.length; i++) {
                newArray[i] = busIDs[i];
            }
            busIDs = newArray;
        }
        BigInteger[] crt = crtValues.toArray(new BigInteger[0]);

        return chineseRemainder(busIDs, crt);
    }

    // Chinese remainder code has been obtained from https://rosettacode.org/wiki/Chinese_remainder_theorem
    // And has been modified to work with big integers

    private static BigInteger chineseRemainder(BigInteger[] n, BigInteger[] a) {
        BigInteger prod = new BigInteger(Arrays.stream(n).reduce(new BigInteger("1"), BigInteger::multiply).toString());

        BigInteger p;
        BigInteger sm = new BigInteger("0");
        for (int i = 0; i < n.length; i++) {
            p = prod.divide(n[i]);
            sm = sm.add( (a[i].multiply(mulInv(p, n[i]))).multiply(p) );
        }
        return sm.mod(prod);
    }

    private static BigInteger mulInv(BigInteger a, BigInteger b) {
        BigInteger b0 = b;
        BigInteger x0 = new BigInteger("0");
        BigInteger x1 = new BigInteger("1");

        if (b.compareTo(new BigInteger("1")) == 0)
            return new BigInteger("1");

        while (a.compareTo(new BigInteger("1")) == 1) {
            BigInteger q = a.divide(b);
            BigInteger amb = a.mod(b);
            a = b;
            b = amb;
            BigInteger xqx = x1.subtract(q.multiply(x0));
            x1 = x0;
            x0 = xqx;
        }

        if (x1.compareTo(new BigInteger("0")) <= -1)
            x1 = x1.add(b0);

        return x1;
    }
}
