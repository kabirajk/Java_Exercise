
// import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test6 {
    public static void main(String[] args) throws IOException {
        try {
            Filecopyer myfile = new Filecopyer(args[0]);
            myfile.copyTo(args[1]);
        } catch (ArrayIndexOutOfBoundsException indexEx) {
            System.err.println("no args are passed");
        }
    }
}

class Filecopyer {
    private FileInputStream srcfile;
    private FileOutputStream destfile;

    Filecopyer(String source) {
        try {
            srcfile = new FileInputStream(source);
        } catch (FileNotFoundException Fnex) {
            System.err.println(Fnex.getMessage());
            System.exit(0);
        }
    }

    public void copyTo(String destination) {
        try {
            destfile = new FileOutputStream(destination);
            int data;
            while ((data = srcfile.read()) != -1) {
                destfile.write(data);
            }
        } catch (FileNotFoundException Fnex) {
            System.err.println(Fnex.getMessage() + "Creating new file");
        } catch (IOException Ioex) {
            System.err.println(Ioex.getMessage());
            Ioex.printStackTrace();
        }

        System.out.println("File copied:" + destination);

    }

    protected void finalize() throws IOException {
        srcfile.close();
        destfile.close();
        System.out.println("Filecopyer.finalize()");
        System.out.println("objects are closed");
    }
}
