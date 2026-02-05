# 📝 NOTE3 — Java Client-Server Message Passing (Two-Way Communication)

This note explains a simple **two-way client-server communication system** in Java using sockets and data streams.

---

## 📦 Used Packages & Their Purpose

| Package | Class | Purpose |
|--------|-------|--------|
| `java.net` | `ServerSocket` | Creates a server that listens for client connections on a port. |
| `java.net` | `Socket` | Establishes a connection between client and server. |
| `java.io` | `DataInputStream` | Reads UTF-encoded messages from the socket. |
| `java.io` | `DataOutputStream` | Sends UTF-encoded messages through the socket. |
| `java.io` | `BufferedReader` | Reads input from the keyboard efficiently. |
| `java.io` | `InputStreamReader` | Converts byte streams to character streams. |

---

## ⚙️ Key Built-in Methods

| Method | Used In | Purpose |
|--------|--------|--------|
| `new ServerSocket(port)` | Server | Starts a server on a specific port. |
| `accept()` | Server | Waits for a client to connect. |
| `new Socket(host, port)` | Client | Connects client to the server. |
| `getInputStream()` | Both | Receives data from the socket. |
| `getOutputStream()` | Both | Sends data through the socket. |
| `readUTF()` | Both | Reads a string message from the stream. |
| `writeUTF()` | Both | Sends a string message to the stream. |
| `flush()` | Both | Forces data to be sent immediately. |
| `close()` | Both | Closes streams and socket connections. |

---

## 🔄 How the System Works

### 1️⃣ Server Process
1. Server starts and listens on port `5005`.
2. Waits for a client using `accept()`.
3. Receives messages from the client using `DataInputStream`.
4. Sends replies using `DataOutputStream`.
5. Continues communication until the client sends `"stop"`.

### 2️⃣ Client Process
1. Client connects to the server using `Socket`.
2. Takes user input from the keyboard.
3. Sends messages to the server.
4. Receives replies from the server.
5. Stops communication when `"stop"` is typed.

---

## 🔗 Communication Flow

```
Client ---- message ----> Server
Client <--- reply ------- Server
```

- The server must start first.
- Client and server communicate using TCP sockets.
- Data is exchanged in UTF string format.

---

## 💡 Key Points

- This system uses **TCP/IP socket programming**.
- `ServerSocket` handles incoming connections.
- `Socket` represents an active connection.
- `DataInputStream` and `DataOutputStream` enable structured message transfer.
- The loop allows continuous two-way communication.
- This is a basic model of chat systems and distributed applications.

---

## 🚀 Learning Outcome

By implementing this system, we understand:

- Basic client-server architecture
- Socket-based communication
- Java I/O streams
- Real-time message exchange
- Foundations of distributed systems
