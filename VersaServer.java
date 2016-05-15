/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

import java.net.*;
import java.io.*;


public class VersaServer {
    public static void main(String[]args) {
        boolean listening = true;
        //default port number is 16 (TCP-unassigned)
        int portNumber = 16;
        int clientCount = 1;
        try (ServerSocket mainSocket = new ServerSocket(portNumber)) {
            System.out.println("Socket started on port " + portNumber);
            System.out.println("Listening for connections");
            while (listening) {
                new Thread(new ClientHandler(mainSocket.accept(), clientCount)).start();
                System.out.println("Connected client #" + clientCount);
                clientCount++;
            }
            System.out.println("Listen loop ended");
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}