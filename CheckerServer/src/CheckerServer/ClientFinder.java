package CheckerServer;

import java.io.IOException;

import java.net.ServerSocket;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * ClientFinder
 */

public class ClientFinder extends Thread{
    private VersaServer server;
    private ServerSocket serverSocket;
    private boolean keepLooking = true;

    public ClientFinder(VersaServer server, ServerSocket serverSocket){
        this.server = server;
        this.serverSocket = serverSocket;
    }

    public void kill(){
        keepLooking = false;
    }

    @Override
    public void run(){
        while(keepLooking){
            try{
                VersaServerThread thread = new VersaServerThread(server, serverSocket.accept());
                thread.start();
            }catch(IOException e){
                //Something here
            }
        }
    }
}
