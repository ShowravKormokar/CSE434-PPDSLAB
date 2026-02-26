package multiple_client_msgPassing_thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5000);
            System.out.println("waiting for client...");
            int count = 0;

            while (true) {
                Socket s = ss.accept();
                System.out.println("New client connected.");
                count++;
                ClientHandler ch = new ClientHandler(s, count);
                (new Thread(ch)).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientHandler implements Runnable {

    Socket s;
    Scanner input;
    DataInputStream din;
    DataOutputStream dout;
    String str1 = "", str2 = "";
    int count;

    public ClientHandler(Socket st, int count) {
        try {
            s = st;
            this.din = new DataInputStream(s.getInputStream());
            this.dout = new DataOutputStream(s.getOutputStream());
            this.input = new Scanner(System.in);
            this.count = count;
        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public void run() {
        try {
            while (!str1.equals("exit")) {
                str1 = din.readUTF();
                System.out.println(count + " Client says: " + str1 + "\n  Server: ");
                str2 = input.nextLine();
                dout.writeUTF(str2);
                dout.flush();
            }
            dout.close();
            din.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
