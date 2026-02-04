# 🔗 Simple Client-Server Message Passing in Java

This note explains a **basic client-server system** in Java that can send and receive messages.  
It uses Java **Socket programming** with built-in networking and I/O packages.

---

## 🛠️ Required Packages

| Package | Purpose |
|---------|---------|
| `java.net.Socket` | Represents a client socket that connects to a server. |
| `java.net.ServerSocket` | Listens for incoming client connections on a specific port. |
| `java.io.PrintWriter` | Sends text data (messages) over the socket. |
| `java.io.BufferedReader` | Reads text data from the socket efficiently. |
| `java.io.InputStreamReader` | Bridges byte streams from socket to character streams. |

---

## ⚙️ How It Works

### 1️⃣ Server Side
1. Create a `ServerSocket` on a specific port.
   ```java
   ServerSocket ss = new ServerSocket(5005);
   ```
2. Wait for a client connection using `accept()`. This method blocks until a client connects.
```java
Socket s = ss.accept();
```
3. Set up input/output streams:
- `BufferedReader` to read client messages.
- `PrintWriter` to send messages back.
4. Receive message from client:
```java
String msg = in.readLine();
```
5. Send a response to client:
```java
out.println("Hello Client, message received: " + msg);
```

### 2️⃣ Client Side
1. Connect to the server using Socket with hostname and port.
```java
Socket s = new Socket("localhost", 5005);
```
2. Set up input/output streams:
- `PrintWriter` to send messages.
- `BufferedReader` to receive server response.

3. Send a message:
```java
out.println("Hello Server! This is client.");
```
4. Read the reply from server:
```java
String response = in.readLine();
```
### 🔄 Connection Flow
```
Client  ---- message ---->  Server
Client  <--- reply ------- Server
```
- Server must start first to listen for connections.
- Client connects and sends data over the socket.
- Server reads the data, processes it, and sends a reply.
- Streams (BufferedReader, PrintWriter) handle text communication over TCP/IP sockets.


### ⚡ Key Points

- `ServerSocket` listens on a port and blocks until a client connects.
- `Socket` represents the active connection on both client and server sides.
- `BufferedReader` + `InputStreamReader` reads incoming messages.
- `PrintWriter` writes outgoing messages.
- Communication happens over TCP/IP, reliable and ordered.
- This is the foundation for building chat systems, network applications, and distributed systems.