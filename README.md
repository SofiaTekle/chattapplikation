# ChatApp

A simple client-server chat application built with **Java**, **Spring Boot**, and **Socket programming**. The application allows users to register, log in, send messages in real time, and view their chat history.

---

## Features

### User Registration and Login
- New users can create an account.  
- Existing users can log in and view previous messages.  

### Real-Time Chat
- Messages are sent instantly to all connected users.  
- Commands:  
  - `/mymsgs` - View your previous messages  
  - `/quit` - Exit the chat  

### Database Storage
- Messages are stored in a database using **JPA/Hibernate**.  
- Users and messages are related via a **One-to-Many relationship**.  

### Multithreading
- The server can handle multiple clients simultaneously.  
- The client uses two threads:  
  1. Listens for messages from the server  
  2. Reads user input from the console  

### Testing
- Unit and mock tests are available for `UserService` and `MessageService`.  

---

## Technologies
- Java 17  
- Spring Boot 3  
- JPA/Hibernate  
- Socket programming (TCP)  
- JUnit 5 + Mockito  

---

## Project Structure

se.sprinto.hakan.chatapp
```
│
├── model
│ ├── User.java # Represents a user
│ └── Message.java # Represents a message
│
├── repository
│ ├── UserRepository.java
│ └── MessageRepository.java
│
├── service
│ ├── UserService.java
│ └── MessageService.java
│
├── ChatServer.java # Manages all clients and broadcasts
├── ClientHandler.java # Handles a single client
├── ChatClient.java # Console-based client
├── ChatServerMain.java # Server entry point
└── ChatClientMain.java # Client entry point
```
