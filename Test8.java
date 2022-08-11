
public class Test8 {
    public static void main(String[] args) throws Exception {
        String substituteString, key;
        try {
            if (args[0].equals("encrypt") || args[0].equals("decrypt")) {

                substituteString = args[1];
                key = args[2];

            } else {
                throw new Help();
            }
            if (args[0].equals("encrypt")) {
                System.out.println("Encrypted= >|" + SecurityString.encryptScrString(substituteString, key)+"<|");
            }
            if (args[0].equals("decrypt")) {
                System.out.println("Decrypted= >|" + SecurityString.dencryptScrString(substituteString, key)+"<|");
            }
        } catch (ArrayIndexOutOfBoundsException noArgs) {
            System.err.println(
                    "Less Arguments: /> encrypt|decrypt \"<String to be encrypted>\" \"<Key string for encryption>\" ");
            System.err.println(noArgs.getMessage());
        } catch (Help ex) {
            System.err.println("Help:" + ex);
        } finally {
            System.out.println("Program exited !");
        }

        // String substituteString;
        // substituteString= SecurityString.encryptScrString("Hello im kabiraj this
        // string to be encrypted", "abtdwv23@");
        // System.out.println(substituteString);
        // // Olssv pt rhipyhq `opz z`ypun `v il lujy@w`ld
        // substituteString= SecurityString.dencryptScrString(substituteString,
        // "abtdwv23@");
        // System.out.println(substituteString);
    }
}

class SecurityString {
    private static String originalKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`~1!2@3#4$5%6^7&8*9(0)_+-={}[]:|<>,. \"\\/\'";
    private static String encrypteKey;


    public static String Move(String substutionString, int index) {
        char[] charArr = substutionString.toCharArray();

        for (int iter = 0; iter < index; iter++) {
            int front=0;
            char temp = charArr[front];
            for (int i = front; i < charArr.length - 1; i++) {
                charArr[i] = charArr[i + 1];
            }
            front++;
            charArr[charArr.length - 1] = temp;
        }
        System.out.println(new String(charArr));
        return new String(charArr);
    }

    // public static String MoveBack(String substutionString, int index) {
    //     char[] charArr = substutionString.toCharArray();
    //     for (int iter = 0; iter < index; iter++) {
    //         char temp = charArr[charArr.length - 1];
    //         for (int i = charArr.length - 1; i >= 1; i--) {
    //             charArr[i] = charArr[i - 1];
    //         }
    //         charArr[0] = temp;
    //     }
    //     return new String(charArr);
    // }

    public static String encryptScrString(String stringTobeEncrypted, String key) {
        int num = 0;
        char[] charArr = stringTobeEncrypted.toCharArray();
        // char[] encryptionkey = key.toCharArray();
        for (int index = 0; index < key.length(); index++) {
            num += (int) key.charAt(index);
            // System.out.println(num);
        }
        // num = num % 93;
        if (num == 0) {
            System.out.println("need differnet key");
            return "";
        }
        encrypteKey = Move(originalKey, num);

        char[] enc = encrypteKey.toCharArray();

        for (int pointer = 0; pointer < charArr.length; pointer += 1) {

            charArr[pointer] = enc[originalKey.indexOf((int) charArr[pointer])];
        }
        return String.copyValueOf(charArr);
    }

    public static String dencryptScrString(String stringTobeDecrypted, String key) {
        int num = 0;
        try{
            num=Integer.parseInt(key);
        }
        catch(NumberFormatException nfe){
            num=0;
        }

        char[] charArr = stringTobeDecrypted.toCharArray();
        
        if(num != 0){
        for (int index = 0; index < key.length(); index++) {
            num += (int) key.charAt(index);}}
        // num = num % 26;

        encrypteKey = Move(originalKey, num);
        char[] org = originalKey.toCharArray();
        System.out.println(org);
        for (int pointer = 0; pointer < charArr.length; pointer += 1) {
            // charArr[pointer] = encrypteKey[originalKey.indexOf((int)charArr[pointer])]
            // System.out.println(charArr[pointer] + " " + originalKey.indexOf((int)
            // charArr[pointer])
            // + " enc[originalKey.indexOf((int)charArr[pointer])];");
            charArr[pointer] = org[encrypteKey.indexOf((int) charArr[pointer])];
        }
        ;
        return String.copyValueOf(charArr);
    }
}

class Help extends Exception {

    @Override
    public String toString() {
        return " encrypt|decrypt \"<String to be encrypted>\" \"<Key string for encryption>\"";
    }
}