import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.io.FileWriter;

public class Test10 {
    public static void main(String[] args) {
        Contacts myContacts = new Contacts();
        // myContacts.add("Kabi",new Long(1234567890));
        // myContacts.add("roshan",new Long(1234567800));
        // myContacts.add("kishore",new Long(1234667890));
        // myContacts.add("Kabiraj",new Long(1234567890));
        // myContacts.add("roshanRino",new Long(1234567800));
        // myContacts.add("kishorek",new Long(1234667890));

        // myContacts.listContatcs();
        // myContacts.remove("Kabiraj");
        // myContacts.listContatcs();

        // // myContacts.search("Kabi");
        // myContacts.saveFile();

        Scanner myScanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.Add numbers \n2.Delete numbers\n3.List all \n4.Search\n5.Exit");
            int option = myScanner.nextInt();
            if (option == 1) {
                System.out.print("[enter name]: ");
                String myString = myScanner.next();
                System.out.print("[enter the mobile number]: ");
                long Number = myScanner.nextLong();
                myContacts.add(myString, Number);
                System.out.println("[Inserted] "+myString+":"+Number);

            } else if (option == 2) {
                System.out.print("[enter name]: ");
                String myString = myScanner.next();
                if (myContacts.remove(myString)) {
                    System.out.println(myString + " Removal Success");
                } else {
                    System.out.println(myString + " Removal Failed");
                }
            } else if (option == 3) {
                myContacts.listContatcs();
            } else if (option == 4) {
                System.out.print("[enter name]: ");
                String myString = myScanner.next();
                myContacts.search(myString);
            } else if (option == 5) {
                myContacts.saveFile();
                break;
            }

        }
        myScanner.close();

    }
}

class Contacts {

    private LinkedHashMap<String, Object> contactDirectory = new LinkedHashMap<String, Object>();
    private FileInputStream dataFile;

    Contacts() {
        try {
            dataFile = new FileInputStream("Data.txt");
            Scanner myScanner = new Scanner(dataFile);
            while (myScanner.hasNextLine()) {
                String myString = myScanner.nextLine();
                this.add(myString.split(" ")[0], new Long(myString.split(" ")[1]));
            }
            myScanner.close();
        } catch (FileNotFoundException FileNotFound) {
            System.err.println(FileNotFound.getMessage());
            this.createDataFile();

        } catch (Exception ex) {
            System.err.println("Exception block Debug");
        }
    }

    public void createDataFile() {
        try {
            FileOutputStream newFile = new FileOutputStream("Data.txt");
            newFile.close();
            dataFile = new FileInputStream("Data.txt");
        } catch (FileNotFoundException ex) {
            System.err.println("Exception Create new file");
        } catch (Exception ex) {
            System.err.println("Exception block Debug");
        }

    }

    public void add(String Name,Long Number) {
        if(this.contactDirectory.containsKey(Name)){
            System.out.println("[name existed overwriting number]");
        this.contactDirectory.put(Name, Number);
        }
        else{
        this.contactDirectory.put(Name, Number);
        }
    }

    public Boolean remove(String Name) {
        if (this.contactDirectory.containsKey(Name)) {
            this.contactDirectory.remove(Name);
            return new Boolean(true);
        } else {
            return false;
        }
    }

    public void search(String Name) {
        if (this.contactDirectory.containsKey(Name)) {
            System.out.println(Name + ":" + this.contactDirectory.get(Name));
        } else {
            System.out.println(Name + " !Not found");
        }
    }

    public void listContatcs() {
        System.out.println("[Contacts List]");
        this.contactDirectory.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });
        System.out.println();
    }

    public void saveFile() {
        try {
            FileWriter newFile = new FileWriter("Data.txt");
            this.contactDirectory.forEach((key, value) -> {
                try {
                    newFile.write(key + " " + value + "\n");
                } catch (IOException e) {
                    System.err.println("Exception block Debug");
                    e.printStackTrace();
                }
            });
            newFile.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Exception Create new file");
        } catch (IOException ex) {
            System.err.println("Exception block Debug");
        }
    }

    @Override
    protected void finalize() {
        // this.saveFile();
        System.out.println("Object is destroyed by the Garbage Collector");
    }
}
