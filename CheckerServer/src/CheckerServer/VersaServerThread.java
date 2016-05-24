package CheckerServer;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

import java.net.Socket;


/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServerThread
 */

public class VersaServerThread extends Thread{
    private VersaServer server;
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean listening = true;
    private boolean ready = false;

    public String name = "";

    public VersaServerThread(VersaServer server, Socket socket){
        super("VersaServerThread");
        this.server = server;
        this.socket = socket;

        try{
            //To be completed
        }catch(IOException e){
            System.err.println("Unable to initialize I/O for the thread");
        }
    }

    public static class Packet{
        public String recip;
        public String content;

        public Packet(String message){
            recip = message.substring(message.indexOf("###sendto")+10,message.indexOf("###message="));
            content = message.substring(message.indexOf("###message")+11,message.length());
        }

        @Override
        public String toString(){
            return "Recipient: " + recip + " Message: " + content;
        }
    }
}
