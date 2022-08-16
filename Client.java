import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static int flag = 0;
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        String host = args.length==2 ?args[0]:"localhost";
        int port = args.length==2 ?Integer.parseInt(args[1]) :5000;
        Socket clientSocketObj = new Socket(host, port);
        System.out.println("Connecting to:"+clientSocketObj.getRemoteSocketAddress());
        DataInputStream socketReciver = new DataInputStream(clientSocketObj.getInputStream());
        DataOutputStream socketSender = new DataOutputStream(clientSocketObj.getOutputStream());
        inputThread in=new inputThread(socketReciver,socketSender);
        Thread inp = new Thread(in);
        outputThread out=new outputThread(socketReciver,socketSender);
        Thread outp = new Thread(out);
        in.getThread(outp);
        out.getThread(inp);
        inp.start();
        outp.start();
        while(inp.isAlive() && outp.isAlive()){
        }
        if(inp.isAlive())
            {inp.interrupt();}
        if(outp.isAlive())
            {outp.interrupt();}
        Thread.sleep(1000);
        clientSocketObj.close();
        System.exit(0);

    }
}

class inputThread implements Runnable {

    DataInputStream socketReciver;
    DataOutputStream socketSender;
    Scanner myScanner = new Scanner(System.in);
    Thread t;

    inputThread(DataInputStream din,DataOutputStream dout) {
        socketReciver = din;
        socketSender = dout;
    }

    public void getThread(Thread in){
        t=in;
    }

    @Override
    public void run() {
        String StringRecived = "";

        while (!StringRecived.equals("exit") && Client.flag == 0) {
            try {
                StringRecived = socketReciver.readUTF();
            } catch (IOException e) {
                Client.flag = 1;
                break;
               
                // System.out.println(e.getMessage());
                // e.printStackTrace();
            }
            System.out.println("Server: " + StringRecived);

            // if (StringRecived.equals("exit"))
            //    { Client.flag = 1;
            //     break;}
        }
        try {
            this.socketSender.writeUTF("exit");
        } catch (IOException e) {
            System.err.println("Exited");
        }
        // while(!t.isInterrupted())
        //     t.interrupt();


    }
}

class outputThread implements Runnable {

    DataOutputStream socketSender;
    DataInputStream socketReciver;
    Scanner myScanner = new Scanner(System.in);
    Thread t;
    outputThread(DataInputStream din,DataOutputStream dout) {
        socketReciver = din;
        socketSender = dout;
    }


    public void getThread(Thread in){
        t=in;
    }

    @Override
    public void run() {
        String StringToSend = "";

        while (!StringToSend.equals("exit")&& Client.flag == 0) {
            if (myScanner.hasNextLine()) {
                StringToSend = myScanner.nextLine();

            }
            try {
                socketSender.writeUTF(StringToSend + "\n");
                socketSender.flush();
            } catch (IOException e) {
                Client.flag = 1;
                break;

            }
            
        }
        
        while(!t.isInterrupted())
            t.interrupt();


    }

}
