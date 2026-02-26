# 📝 NOTE5 — Java Multi-Client Handling Using Threads (Thread per Client Model)

This note explains a **multi-client server system** in Java where multiple clients connect to a single server, and each client is handled using a separate thread.

This model demonstrates how a server can handle **concurrent client connections** using multithreading.

---

## 📦 Used Packages & Their Purpose

| Package | Class | Purpose |
|----------|----------|----------|
| `java.net` | `ServerSocket` | Creates a server that listens for client connections. |
| `java.net` | `Socket` | Establishes communication between client and server. |
| `java.io` | `DataInputStream` | Reads UTF messages from the socket. |
| `java.io` | `DataOutputStream` | Sends UTF messages through the socket. |
| `java.lang` | `Thread` | Executes multiple clients concurrently. |
| `java.lang` | `Runnable` | Defines task logic for each client thread. |
| `java.io` | `BufferedReader` | Reads keyboard input from client side. |
| `java.io` | `InputStreamReader` | Converts byte stream into character stream. |

---

## ⚙️ Key Built-in Methods

| Method | Used In | Purpose |
|---------|---------|----------|
| `new ServerSocket(port)` | Server | Starts the server on a specific port. |
| `accept()` | Server | Waits for and accepts client connection. |
| `new Socket(host, port)` | Client | Connects client to server. |
| `getInputStream()` | Both | Receives data from socket. |
| `getOutputStream()` | Both | Sends data through socket. |
| `readUTF()` | Both | Reads string message. |
| `writeUTF()` | Both | Sends string message. |
| `flush()` | Both | Sends buffered data immediately. |
| `start()` | Server | Starts a new thread. |
| `run()` | Server | Contains thread execution logic. |
| `close()` | Both | Closes connection and streams. |

---

## 🔄 How the System Works

### 1️⃣ Server Process

1. Server starts on port `5000`.
2. Waits for client connections using `accept()`.
3. When a client connects:
   - A new `ClientHandler` object is created.
   - A new `Thread` is created for that client.
4. Each thread handles communication independently.
5. Multiple clients can communicate with the server simultaneously.
6. Communication continues until the client sends `"exit"`.

---

### 2️⃣ Client Process

1. Client connects to server.
2. Sends message to server.
3. Server replies individually.
4. Communication continues until `"exit"` is typed.

---

## 🔗 Communication Flow
```
Client1 ──┐
Client2 ──┼──> Server (Thread per Client)
Client3 ──┘
```
- Each client has a separate thread.
- All clients communicate independently.
- Server does not block while handling other clients.

---

## 🧠 Thread-Based Architecture
```
            ┌──────────────────┐
            │      Server      │
            │   (Port 5000)    │
            └────────┬─────────┘
                     │
     ┌───────────────┼───────────────┐
     │               │               │
    ┌─────────┐ ┌─────────┐ ┌─────────┐
    │ Thread1 │ │ Thread2 │ │ Thread3 │
    │Client1  │ │Client2  │ │Client3  │
    └─────────┘ └─────────┘ └─────────┘
```

Each thread runs independently using the `Runnable` interface.

---

## 💡 Important Concepts

- This system uses **concurrent programming**.
- Each client connection runs in a separate thread.
- The server can handle unlimited clients (theoretically).
- This prevents blocking problems.
- Communication is independent between clients.
- `"exit"` keyword stops communication for a specific client only.
- Server continues running even if one client disconnects.

---

## 🔥 Why Thread Per Client is Important

Without threads:
- Server handles only one client at a time.
- Other clients must wait.

With threads:
- Server handles multiple clients simultaneously.
- Improves performance and scalability.
- Forms the base of real-world servers.

---

## 🚀 Learning Outcome

By implementing this experiment, we understand:

- Multithreading in Java
- Runnable interface usage
- Concurrent client handling
- Thread lifecycle
- Real-time multi-user communication
- Basic server scalability
- Foundation of distributed and parallel systems

---

## 📌 Real-World Application

This model is used in:

- Chat servers
- Online multiplayer games
- Web servers
- Banking servers
- Messaging systems
- Cloud services

---

## 📌 Conclusion

This experiment demonstrates a **multi-client threaded server system** where:

- A single server handles multiple clients.
- Each client runs in a separate thread.
- Communication is independent and concurrent.
- This is a foundational architecture in distributed systems.

It is a major step toward advanced distributed system models like:

- Load balancers
- Distributed task managers
- Message brokers
- Microservice architectures

---