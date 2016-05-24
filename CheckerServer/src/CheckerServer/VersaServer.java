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
    private ServerSocket serversocket = null;
    private ClientFinder clientListener = null;
    private VersaServerGUI gui = null;
    private HashMap clients = null;
    private HashMap games = null;
    private int num_connected = 0;
    private int max_connections;
    private boolean listening = false;
    /*
     * Spawns new hostIO thread
     * Spawns new SocketHandler thread
     * In loop:
     * Checks for new hostIO messages
     * Responds to hostIO messages
     * Checks for new SocketHandler messages
     * Responds to SocketHandler messages
     */

    public VersaServer(VersaServerGUI GUI){
        //We can add parameters to alter max_connection in the future
        this.gui = GUI;
        max_connections = 10;
    }

    public int addClient(){

    }

    public void removeClient(VersaServerThread){

    }
}
