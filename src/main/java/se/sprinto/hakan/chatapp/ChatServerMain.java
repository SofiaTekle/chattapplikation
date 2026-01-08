package se.sprinto.hakan.chatapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Startpunkt för server-applikationen.
     * När ChatServer skapas:
     * - startar servern automatiskt
     * - öppnar ServerSocket
     * - börjar lyssna efter klienter
 */



@SpringBootApplication
public class ChatServerMain implements CommandLineRunner {
    private final ChatServer chatServer;

    public ChatServerMain(ChatServer chatServer) {

        this.chatServer = chatServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChatServerMain.class, args);

    }
    /**
     * Behövs inte i detta fall eftersom servern
     * startas direkt i ChatServer.
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
