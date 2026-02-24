package multiple_client_msg_passing;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            System.out.println("Server is running...");

            // Accept Client 1
            Socket s1 = ss.accept();
            System.out.println("Client 1 connected!");

            // Accept Client 2
            Socket s2 = ss.accept();
            System.out.println("Client 2 connected!");

            DataInputStream dis1 = new DataInputStream(s1.getInputStream());
            DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());

            DataInputStream dis2 = new DataInputStream(s2.getInputStream());
            DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream());

            String msg1 = "", msg2 = "";

            while (true) {

                // Receive from Client 1
                msg1 = dis1.readUTF();
                System.out.println("Client1: " + msg1);

                // Send to Client 2
                dos2.writeUTF(msg1);
                dos2.flush();

                if (msg1.equalsIgnoreCase("stop"))
                    break;

                // Receive from Client 2
                msg2 = dis2.readUTF();
                System.out.println("Client2: " + msg2);

                // Send to Client 1
                dos1.writeUTF(msg2);
                dos1.flush();

                if (msg2.equalsIgnoreCase("stop"))
                    break;
            }

            s1.close();
            s2.close();
            ss.close();

        } catch (Exception e) {
            System.err.println("Server error: " + e);
        }
    }
}
