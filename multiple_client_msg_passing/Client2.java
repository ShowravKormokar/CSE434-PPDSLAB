package multiple_client_msg_passing;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5005);
            System.out.println("Client2 Connected!");

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg = "", reply = "";

            while (!msg.equalsIgnoreCase("stop")) {

                reply = dis.readUTF();
                System.out.println("Client1: " + reply);

                if (reply.equalsIgnoreCase("stop"))
                    break;

                System.out.print("Client2: ");
                msg = br.readLine();
                dos.writeUTF(msg);
                dos.flush();
            }

            s.close();

        } catch (Exception e) {
            System.err.println("Client2 error: " + e);
        }
    }
}