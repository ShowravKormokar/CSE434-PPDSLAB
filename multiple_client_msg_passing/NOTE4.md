# 📝 NOTE4 — Java Client-to-Client Message Passing (Server as Middleman)

This note explains a simple **client-to-client communication system** in Java where two clients communicate through a server acting as a middleman using sockets and data streams.

---

## 📦 Used Packages & Their Purpose

| Package | Class | Purpose |
|--------|-------|--------|
| `java.net` | `ServerSocket` | Creates a server that listens for incoming client connections. |
| `java.net` | `Socket` | Establishes a connection between client and server. |
| `java.io` | `DataInputStream` | Reads UTF-encoded messages from the socket. |
| `java.io` | `DataOutputStream` | Sends UTF-encoded messages through the socket. |
| `java.io` | `BufferedReader` | Reads keyboard input from the user. |
| `java.io` | `InputStreamReader` | Converts byte input stream into character stream. |

---

## ⚙️ Key Built-in Methods

| Method | Used In | Purpose |
|--------|--------|--------|
| `new ServerSocket(port)` | Server | Starts the server on a specific port. |
| `accept()` | Server | Waits for a client to connect. |
| `new Socket(host, port)` | Client | Connects client to the server. |
| `getInputStream()` | Both | Receives data from the socket. |
| `getOutputStream()` | Both | Sends data through the socket. |
| `readUTF()` | Both | Reads a string message from the stream. |
| `writeUTF()` | Both | Sends a string message to the stream. |
| `flush()` | Both | Forces buffered data to be sent immediately. |
| `close()` | Both | Closes streams and socket connections. |

---

## 🔄 How the System Works

### 1️⃣ Server Process (Middleman)

1. Server starts and listens on port `5005`.
2. Accepts connection from **Client1**.
3. Accepts connection from **Client2**.
4. Receives message from Client1.
5. Forwards that message to Client2.
6. Receives reply from Client2.
7. Forwards that reply back to Client1.
8. Continues the loop until one client sends `"stop"`.

The server does NOT create messages.
It only forwards messages between clients.

---

### 2️⃣ Client1 Process

1. Connects to the server.
2. Takes user input from keyboard.
3. Sends message to server.
4. Waits for reply from Client2 (via server).
5. Stops when `"stop"` is typed.

---

### 3️⃣ Client2 Process

1. Connects to the server.
2. Waits for message from Client1.
3. Displays received message.
4. Sends reply back to server.
5. Stops when `"stop"` is received or typed.

---

## 🔗 Communication Flow
```
Client1 ---- message ----> Server ----> Client2
Client1 <--- reply ------- Server <---- Client2
```
- The server must start first.
- Then Client1 connects.
- Then Client2 connects.
- Server acts only as a message relay (middleman).

---

## 💡 Important Concepts

- This system uses **TCP/IP socket programming**.
- Two clients cannot directly communicate without a server in this model.
- The server manages both socket connections.
- Messages are transferred using UTF string format.
- Communication is turn-based (Client1 → Client2 → Client1).
- Typing `"stop"` ends the chat session.

---

## 🧠 System Architecture
```
       ┌───────────┐
       │   Server  │
       │ (Port5005)│
       └─────┬─────┘
             │
   ┌─────────┴──────────┐
   │                    │
  ┌─────────┐  ┌─────────┐
  │ Client1 │  │ Client2 │
  └─────────┘  └─────────┘
```

- Server handles two socket connections.
- Clients communicate indirectly through the server.

---

## 🚀 Learning Outcome

By implementing this experiment, we understand:

- Multi-client socket handling
- Client-to-client communication model
- Server as a message relay
- Turn-based communication logic
- Practical understanding of distributed systems
- Foundation of real-time chat applications

---

## 📌 Conclusion

This experiment demonstrates a basic **client-to-client messaging system** using Java sockets where:

- The server acts as a communication bridge.
- Two clients exchange messages in real-time.
- Communication continues until `"stop"` is sent.
- This is the basic structure behind chat servers and messaging systems.
