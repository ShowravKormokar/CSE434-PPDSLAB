# 🔗 Simple Client-Server Connection (Java Socket)

This example demonstrates a basic **client-server connection** using Java Socket programming.  
Here, the client connects to the server through a specific port, but no data is exchanged yet.

---

## ⚙️ How It Works

### 🖥️ Server Side
1. The server creates a `ServerSocket` on a specific port (e.g., `5005`).
2. It waits for a client connection using the `accept()` method.
3. When a client connects, the server confirms the connection.

```java
ServerSocket ss = new ServerSocket(5005);
Socket s = ss.accept();
```

### 💻 Client Side
1. The client creates a Socket by providing the server address and port.
2. If the connection is successful, it confirms that the socket is connected.

```java
Socket s = new Socket("localhost", 5005);
```
### 🔄 Connection Flow
```
Client  ---- request ---->  Server
Client  <--- connection ---  Server
```
- The server must start first and listen on the port.
- The client then sends a connection request.
- Once connected, both sides can communicate using input/output streams.

### 🎯 Purpose of This Example
- Understand the basic concept of socket programming
- Learn how a client connects to a server
- Prepare for message-based client-server communication

### 📌 Key Points
- ServerSocket is used to listen for connections.
- Socket is used to establish communication.
- Both client and server must use the same port number.
- This is the foundation of distributed systems and network programming.