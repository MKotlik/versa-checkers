package CheckerServer;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class VersaServer extends Thread{
    private ServerSocket serversocket;
    private ClientFinder clientListener;
    private VersaServerGUI gui;
    private HashMap clients;
    private HashMap games;
    private int num_connected;
    private int max_connections;
    private boolean listening;
    /*
     * Spawns new hostIO thread
     * Spawns new SocketHandler thread
     * In loop:
     * Checks for new hostIO messages
     * Responds to hostIO messages
     * Checks for new SocketHandler messages
     * Responds to SocketHandler messages
     */

    public VersaServer(){

    }
}
