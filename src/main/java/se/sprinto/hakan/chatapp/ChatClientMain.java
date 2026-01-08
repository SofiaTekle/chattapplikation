package se.sprinto.hakan.chatapp;


/**
 * Startpunkt för klient-applikationen.

 * Varje gång detta program körs:
 * - startas EN chattklient
 * - klienten körs i sin egen process

 * För att simulera flera användare:
 * - starta detta program i flera terminalfönster
 */
public class ChatClientMain {

    public static void main(String[] args) {

        new ChatClient().start();
    }
}

