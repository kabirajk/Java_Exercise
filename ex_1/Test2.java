package ex_1;

// import java.io.BufferedReader;
import java.io.IOException;
// import java.io.InputStreamReader;
import java.util.Scanner;
// import javax.swing.KeyStroke;
// import sun.misc.Signal;
// import javafx.scene.input.KeyEvent;

class Test2 {
    public static void main(String[] args) throws IOException {
        Scanner myScanner = new Scanner(System.in);
        // myScanner.close();// if not closed by closable interface invokes itself
        // finsih key press event
        // Signal signal = new Signal("INT");
        while (myScanner.hasNextLine()) {
            // System.out.print("\r program is still running");
        String myString = myScanner.nextLine();
        System.out.println(myString.length()) ;
        // Signal.handle(new Signal("INT"),signal -> System.out.println("Interrupted by Ctrl+C"));

            // BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            // String stringInt = consoleIn.readLine();
            // System.out.println(stringInt);
        }
        System.out.print("\r Main ended");
        myScanner.close();
    }
}
