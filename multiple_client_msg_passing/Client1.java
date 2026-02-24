package multiple_client_msg_passing;

import java.io.*;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 5005);
            System.out.println("Client1 Connected!");

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msg = "", reply = "";

            while (!msg.equalsIgnoreCase("stop")) {

                System.out.print("Client1: ");
                msg = br.readLine();
                dos.writeUTF(msg);
                dos.flush();

                if (msg.equalsIgnoreCase("stop"))
                    break;

                reply = dis.readUTF();
                System.out.println("Client2: " + reply);
            }

            s.close();

        } catch (Exception e) {
            System.err.println("Client1 error: " + e);
        }
    }
}