package chat_room;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6000);

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Send client name to server
            System.out.print("Enter your name: ");
            String name = br.readLine();
            dout.writeUTF(name);
            dout.flush();

            Thread receiveThread = new Thread(() -> {
                // Listen for messages from server
                try {
                    String msg;
                    while (true) {
                        msg = din.readUTF();
                        System.out.println(msg);
                    }
                } catch (Exception e) {
                    System.out.println("Disconnected.");
                }
            });

            receiveThread.start();

            // Read user input and send to server
            String msg;
            while (true) {
                msg = br.readLine();
                dout.writeUTF(msg);
                dout.flush();

                if (msg.equalsIgnoreCase("exit")) {
                    s.close();
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Client error: " + e);
        }
    }
}