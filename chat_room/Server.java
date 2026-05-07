package chat_room;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    // Shared list of clients
    static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6000);
            System.out.println("Chat Server started on port 6000...");

            while (true) {
                Socket s = ss.accept();

                // Create handler
                ClientHandler ch = new ClientHandler(s);

                // Add to client list
                clients.add(ch);

                // Start thread
                new Thread(ch).start();
            }

        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}

// Hanler for each client connection
class ClientHandler implements Runnable {

    Socket socket;
    DataInputStream din;
    DataOutputStream dout;

    String name;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            // First message = client name
            name = din.readUTF();
            System.out.println(name + " joined the chat.");

            broadcast(name + " joined the chat.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        String msg;

        try {
            while (true) {
                msg = din.readUTF();

                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println(name + " left the chat.");

                    broadcast(name + " left the chat.");
                    Server.clients.remove(this);

                    socket.close();
                    break;
                }

                System.out.println(name + ": " + msg);

                // Brodcast to others
                broadcast(name + ": " + msg);
            }

        } catch (Exception e) {
            System.out.println("Connection error: " + e);
        }
    }

    // Broadcast message to all clients except sender
    void broadcast(String message) {
        for (ClientHandler client : Server.clients) {
            try {
                if (client != this) { // Don't send to sender
                    client.dout.writeUTF(message);
                    client.dout.flush();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}