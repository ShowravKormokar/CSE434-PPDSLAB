```md
# 📝 NOTE06 — Java HTTP Server Using HttpHandler (Request–Response Model)

This note explains a simple **HTTP Server implementation in Java** using the built-in `HttpServer` and `HttpHandler` classes.

It demonstrates how a server handles **HTTP requests from a browser** and sends back appropriate responses (text or HTML).

---

## 📦 Used Packages & Their Purpose

| Package | Class | Purpose |
|--------|-------|--------|
| `com.sun.net.httpserver` | `HttpServer` | Creates and manages the HTTP server. |
| `com.sun.net.httpserver` | `HttpHandler` | Defines how each request is handled. |
| `com.sun.net.httpserver` | `HttpExchange` | Represents the HTTP request and response. |
| `java.net` | `InetSocketAddress` | Binds the server to a specific port. |
| `java.io` | `OutputStream` | Sends response data to the client. |
| `java.io` | `IOException` | Handles input/output errors. |

---

## ⚙️ Key Built-in Methods

| Method | Used In | Purpose |
|--------|--------|--------|
| `HttpServer.create()` | Server | Creates a new HTTP server instance. |
| `createContext(path, handler)` | Server | Maps URL path to a handler. |
| `start()` | Server | Starts the HTTP server. |
| `handle(HttpExchange)` | Handler | Processes incoming requests. |
| `getResponseHeaders()` | Handler | Sets response headers (e.g., Content-Type). |
| `sendResponseHeaders(status, length)` | Handler | Sends HTTP status code and headers. |
| `getResponseBody()` | Handler | Gets output stream to write response. |
| `write()` | Handler | Sends response data. |
| `close()` | Handler | Closes connection and stream. |

---

## 🌐 What is HTTP?

**HTTP (HyperText Transfer Protocol)** is a communication protocol used between a **client (browser)** and a **server**.

- It follows a **Request–Response model**
- It is **stateless**
- It is the foundation of web communication

---

## 🔄 Request–Response Model

```
Client (Browser) → Request → Server
Client (Browser) ← Response ← Server
```

### 📥 Example Request

```
GET /showrav HTTP/1.1
Host: localhost:8080
```

### 📤 Example Response

```
HTTP/1.1 200 OK
Content-Type: text/plain

Hello, showrav.
```

---

## 🔄 How the System Works

### 🌍 Browser Request Flow

```

Browser → [http://localhost:8080/showrav](http://localhost:8080/showrav)

```

1. Request goes to the server  
2. Server checks path = `/showrav`  
3. `HelloHandler` is called  
4. `handle()` method executes  
5. Response is generated  
6. Browser displays output  

---

## 🧠 Server Architecture

```
    Browser
       │
       ▼
 HTTP Server (8080)
    │       │
    ▼       ▼
/showrav    /html
Handler   Handler

```

---

## 🔧 Code Explanation

### 1️⃣ Handler Implementation

```java
class HelloHandler implements HttpHandler
````

* `HttpHandler` is an **interface**
* `implements` means this class must define `handle()` method

---

### 2️⃣ @Override Annotation

```java
@Override
public void handle(HttpExchange exchange)
```

* Ensures method correctly overrides interface method
* Helps catch errors during compilation

---

### 3️⃣ Setting Response Type

```java
exchange.getResponseHeaders().set("Content-Type", "text/plain");
```

* Tells browser the response type is plain text

---

### 4️⃣ Sending Status Code

```java
exchange.sendResponseHeaders(200, 0);
```

* `200` = Success
* `0` = Unknown content length

---

### 5️⃣ Writing Response

```java
OutputStream od = exchange.getResponseBody();
od.write(response.getBytes());
```

* Converts string to bytes
* Sends data to client

---

### 6️⃣ Closing Connection

```java
od.close();
exchange.close();
```

* Ends communication safely

---

### 7️⃣ HTML Handler

```java
exchange.getResponseHeaders().set("Content-Type", "text/html");
```

* Sends HTML content instead of plain text

---

### 8️⃣ Server Setup

```java
HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
```

* Creates server on port `8080`

---

### 9️⃣ Route Mapping

```java
server.createContext("/showrav", new HelloHandler());
server.createContext("/html", new Html());
```

* Maps URL paths to specific handlers

---

### 🔟 Start Server

```java
server.start();
```

* Server begins listening for requests

---

## 💡 Important Concepts

* HTTP follows **stateless communication**
* Each request is handled independently
* URL paths are mapped to handlers
* Response = Headers + Body
* Content-Type is crucial for browser rendering

---

## 🚀 Learning Outcome

By implementing this system, we understand:

* HTTP protocol basics
* Request–Response cycle
* Java HTTP server creation
* Routing (URL → handler)
* Response generation using streams
* Interface (`HttpHandler`) usage
* Method overriding

---

## 📌 Summary Flow

```
HTTP is a protocol used for communication between client and server.

In this program:
- A Java HTTP server is created on port 8080
- Different URL paths are mapped to handlers
- Each handler processes request and sends response
- Response is sent using OutputStream

Flow:
Client → Request → Server → Handler → Response → Client
```

---

## 📌 Conclusion

This experiment demonstrates a **basic HTTP server** using Java where:

* A browser sends requests
* Server routes requests to handlers
* Handlers generate responses
* Responses are sent back to the browser

This is the foundation of:

* Web servers
* REST APIs
* Backend systems
* Microservices

---
