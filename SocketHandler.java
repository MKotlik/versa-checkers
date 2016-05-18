/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * SocketHandler
 */

import java.net.*;
import java.io.*;

public class SocketHandler implements Runnable{
    //Instance variables
    boolean listening;
    int portNumber;
    ThreadLinker mainLink;

    //Constructors
    public SocketHandler(int portNumber, ThreadLinker mainLink) {
        this.listening = true;
        this.portNumber = portNumber;
        this.mainLink = mainLink;
        new Thread(this, 'SOCKET_HANDLER').start();
    }

    //Run
    public void run() {
        try (ServerSocket mainSocket = new ServerSocket(portNumber)) {
            System.out.println("SOCKET_HANDLER: Socket started on port " + portNumber);
            System.out.println("SOCKET_HANDLER: Listening for connections");
            while (listening) {
                /*
                 * Check for new messages from serverMain
                 * Respond to messages from serverMain
                 */
                /*
                 * Assign temp threadID to new ClientHandler
                 * Create new ThreadLinker with main id and clienthandler id
                 * Create new ClientHandler with this threadLinker
                 * Add threadLinker to main's hashMap of threadLinkers
                 */
            }
            System.out.println("SOCKET_HANDLER: Stopped listening. Socket closing.");
        } catch (IOException e) {
            System.err.println("SOCKET_HANDLER: IOException - Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}