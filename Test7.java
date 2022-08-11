import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test7 {

    private static void fileReader(String path) {
        FileInputStream myFile;
        try {
            myFile = new FileInputStream(path);
            Scanner input = new Scanner(myFile);
            int index = 1;
            while (input.hasNextLine()) {
                System.out.println(index + ". " + input.nextLine());
                index += 1;
            }
            input.close();
        } catch (FileNotFoundException Filenotfound) {
            System.err.println(Filenotfound.getMessage());
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            for (int index = 0; index < args.length; index += 1) {
                fileReader(args[index]);
            }
        } catch (ArrayIndexOutOfBoundsException outBoundEx) {
            System.err.println(outBoundEx.getMessage());
        }
    }
}
