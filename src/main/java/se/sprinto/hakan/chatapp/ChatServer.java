package se.sprinto.hakan.chatapp;

import org.springframework.stereotype.Component;
import se.sprinto.hakan.chatapp.service.UserService;
import se.sprinto.hakan.chatapp.service.MessageService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatServer {

    private final UserService userService;
    private final MessageService messageService;

     //Lista över alla anslutna klienter.
    private final List<ClientHandler> clients = new ArrayList<>();

    public ChatServer(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;

        // Startar servern direkt när bean skapas
        startServer();
    }


    /**
     * Startar servern i en separat tråd.
     * Servern lyssnar konstant efter nya klienter.
     */
    private void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5555)) {
                System.out.println("Server startad på port 5555...");

                // Serverns huvudloop - körs alltid
                while (true) {
                    // Blockerar tills en klient ansluter
                    Socket socket = serverSocket.accept();

                     // Skapar en ClientHandler för klienten
                    ClientHandler handler =
                            new ClientHandler(socket, this, userService, messageService);
                    clients.add(handler);

                    // Startar klientens tråd
                    new Thread(handler).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


  // Skickar ett medd till alla klienter, utom avsändaren
    public void broadcast(String message, ClientHandler from) {
        for (ClientHandler client : clients) {
            if (client != from) {
                client.sendMessage(from.getUser().getUsername() + ": " + message);
            }
        }
    }

    // Tar bort en klient när den disconnectar
    public void removeClient(ClientHandler c) {
        clients.remove(c);
    }
}

