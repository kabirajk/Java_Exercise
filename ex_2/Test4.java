package ex_2;

import java.util.ArrayList;
import java.util.Scanner;

public class Test4 {
    public static ArrayList<Long> myArrayList = new ArrayList<Long>();

    static public Long fibo(Long num) {
        if (myArrayList.get(num.intValue()) != 0 && num != 0) {
            return myArrayList.get(num.intValue());
        }
        if (num <= 1) {
            return num;
        }

        long value = fibo(num - 1) + fibo(num - 2);
        myArrayList.set(num.intValue(), value);
        return value;
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                throw new ZeroArgsException("No Arguments are passed");
            }
        } catch (ZeroArgsException exceptionObject) {
            System.err.println("Argumentry exception: <arg1> <arg2> ... <argn> !not found");
            Scanner myScanner = new Scanner(System.in);
            String myString = myScanner.nextLine();
            myScanner.close();
            args = myString.split(" ");
        } catch (Exception exceptionObject) {
            System.err.println(
                    "Argumentry exception:try to pass arguments while executing ex:/>java filename.java <arg1> <arg2> ... <argn>");
            // System.exit(0);
        }
        for (int index = 0; index < args.length; index += 1) {
            Long number = Long.valueOf(args[index]);
            // max correcect limit 12200160415121876738
            for (int i = myArrayList.size(); i <= number; i++) {
                myArrayList.add(0l);
            }
            for (long i = 0; i <= number; i += 1) {
                fibo(i);
            }
            System.out.println(fibo(number));
        }
        // System.out.println(myArrayList.get(number.intValue()));
    }
}

class ZeroArgsException extends Exception {
    ZeroArgsException(String s) {
        super(s);
    }
}
