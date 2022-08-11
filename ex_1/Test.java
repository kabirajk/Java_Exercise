package ex_1;

// import java.util.Scanner;
import java.io.DataInputStream;
import java.io.IOException;

class Test {
    public static void main(String[] args) throws IOException {
        // Scanner inputScanner=new Scanner(System.in);

        // reads unctil a sapce character
        // String myString=inputScanner.next();

        // reads until new line character
        DataInputStream dataInpStream = new DataInputStream(System.in);
        // bit levl data abstraction so no
        int myString = dataInpStream.readInt();
        dataInpStream.close();
        // get data by right bitshifting 2^index from right 2
        myString = myString >> 24;
        myString = myString - (myString >> 16);
        System.out.println(Integer.toBinaryString((myString)));

        // System.out.println( (((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) <<
        // 8) | (d & 0xff)) );
        // (((a & 0xff) << 24) | ((b & 0xff) << 16) |
        // ((c & 0xff) << 8) | (d & 0xff))
        // System.out.println(myString);
    }
}