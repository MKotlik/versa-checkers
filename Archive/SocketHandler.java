/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * SocketHandler
 */

import java.net.*;
import java.io.*;

public class SocketHandler implements Runnable{
    //Instance variables
    private boolean listening;
    private int threadIdCnt; //currently, threadIDs are consecutively increasing whole numbers
    //TODO - come up with better way to assign threadIDs
    private int portNumber;
    private ThreadLinker serverLink;
    private SynchronizedThreadLinkerMap clientLinkMap;

    //Constructors
    public SocketHandler(int portNumber, ThreadLinker serverLink, SynchronizedThreadLinkerMap clientLinkMap) {
        this.listening = true; //for now, defaults to true
        threadIdCnt = 0;
        this.portNumber = portNumber;
        this.serverLink = serverLink; //uses side B
        this.clientLinkMap = clientLinkMap;
        new Thread(this, 'SOCKET_HANDLER').start();
    }

    //Run
    public void run() {
        while (! listening) { //if started w/ socket closed by default, listen for open signal
            if (mainLink.retrieveB().equals("START_SOCKET")) { //could be diff. code
                listening = true;
            }
        }
        //assume listening = true at this point
        try (ServerSocket mainSocket = new ServerSocket(portNumber)) {
            System.out.println("SOCKET_HANDLER: Socket started on port " + portNumber);
            System.out.println("SOCKET_HANDLER: Listening for connections");
            while (listening) {
                //Check if server sent exit message
                if (serverLink.hasMsgForB()) {
                    if (serverLink.retrieveB().equals("EXIT")) { //could change to diff. code
                        System.out.println("SOCKET_HANDLER: Stopped by main.");
                        break;
                    } //Ignore any msgs that aren't EXIT, they shouldn't be here
                }
                //TODO - support more commands as they're added
                //Listen for connections and create ClientHandlers
                String nextID = "CLIENT_" + threadIdCnt; //ID to be assigned to next handler
                ThreadLinker nextLink = new ThreadLinker("SERVER_MAIN", nextID); //Linker to be given to next handler
                new ClientHandler(mainSocket.accept(), nextLink, nextID); //Spawn clientHandler
                clientLinkMap.put(nextID, nextLink); //Give server_main linker to new clientHandler
                threadIdCnt++; //Increase id
            }
            System.out.println("SOCKET_HANDLER: Stopped listening. Socket closing.");
        } catch (IOException e) {
            System.err.println("SOCKET_HANDLER: IOException - Could not listen on port " + portNumber);
        }
    }
}