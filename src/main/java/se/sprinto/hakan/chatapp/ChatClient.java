package se.sprinto.hakan.chatapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Startar klienten och ansluter till chatservern.
 *
 * Klienten använder TVÅ trådar:
 * 1. En tråd för att LYSSNA på servern
 * 2. Huvudtråden för att läsa användarens input från konsolen
 *
 * Detta behövs eftersom servern kan skicka meddelanden
 * när som helst, oberoende av användarens input.
 *
*/

public class ChatClient {
    public void start() {
        String host = "localhost";
        int port = 5555;

        // Skapar en socket som ansluter till servern
        try (Socket socket = new Socket(host, port);

             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Tråd 1: Lyssnar konstant på medd från servern.
            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException ignored) {
                }
            }).start();

            // Huvudtråden: läser användarens input och skickar till servern
            String userInput;
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
                // Avslutar klienten med /quit
                if (userInput.equalsIgnoreCase("/quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Kunde inte ansluta till servern: " + e.getMessage());
        }
    }

}
