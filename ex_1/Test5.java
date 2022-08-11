package ex_1;

import java.text.SimpleDateFormat;

import java.util.Date;

//ex 5 date time
public class Test5 {
    public static void main(String[] args) {
        String resultString;
        SimpleDateFormat formatter;
        // Day MMM DD hr.min.sec TIMEZONE YYYY
        formatter = new SimpleDateFormat("[ EEE MMM HH:mm:ss zzz yyyy ]");
        resultString = formatter.format(new Date());
        // System.out.println(resultString);
        while (true) {
            resultString = formatter.format(new Date());
            System.out.print("\r" + resultString);
        }
    }
}
