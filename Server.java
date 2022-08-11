import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.net.ServerSocket;




class Server {
    public static int flag = 0;
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = args.length ==1 ?Integer.parseInt(args[0]):5000;
        ServerSocket clientSocket = new ServerSocket(port);
        System.out.println("Server: Waiting for conection"+clientSocket.getLocalSocketAddress());
        Socket server = clientSocket.accept();
        System.out.println("Connected"+server.getRemoteSocketAddress());
        DataInputStream socketReciver = new DataInputStream(server.getInputStream());
        DataOutputStream socketSender = new DataOutputStream(server.getOutputStream());
        inputThread in=new inputThread(socketReciver,socketSender);

        Thread inp = new Thread(in);
        outputThread out=new outputThread(socketSender);
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
        server.close();
        // myScanner.close();
        clientSocket.close();
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

        while (!StringRecived.equals("exit")&& Server.flag == 0) {
            try {
                StringRecived = socketReciver.readUTF();
            } catch (IOException e) {
                Server.flag = 1;
                break;
            }
            System.out.println("Client: " + StringRecived);
        }
        try {
            this.socketSender.writeUTF("exit");
        } catch (IOException e) {
            System.err.println("Exited");
        }
        while(!t.isInterrupted())
            t.interrupt();

    }
}

class outputThread implements Runnable {

    DataOutputStream socketSender;
    Thread t;
    Scanner myScanner = new Scanner(System.in);

    outputThread(DataOutputStream dout) {
        socketSender = dout;
    }
    public void getThread(Thread in){
        t=in;
    }

    @Override
    public void run() {
        String StringToSend = "";

        while (!StringToSend.equals("exit")&& Server.flag ==0) {
            if (myScanner.hasNextLine()) {
                StringToSend = myScanner.nextLine();
            }
            try {
                socketSender.writeUTF(StringToSend + "\n");
                socketSender.flush();
            } catch (IOException e) {
                Server.flag = 1;
                break;
            }
            
        }
        while(!t.isInterrupted())
            t.interrupt();

    }

}
