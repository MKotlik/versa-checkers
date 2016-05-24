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
            //To be completed this.in = in =
            this.out = new PrintWriter(socket.getOutputStream(),true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            System.err.println("Unable to initialize I/O for the thread");
        }
    }

    public void sendMessage(String sender, String incMessage){
        out.println("###sentfrom=" + sender + "###message" + incMessage);
    }

    //Kills the thread
    public void kill(){
        listening = false;
    }

    //accessor method
    public Socket getSocket(){
        return this.socket;
    }

    public static class Message{
        public String recip;
        public String content;

        public Message(String message){
            recip = message.substring(message.indexOf("###sendto")+10,message.indexOf("###message="));
            content = message.substring(message.indexOf("###message")+11,message.length());
        }

        @Override
        public String toString(){
            return "Recipient: " + recip + " Message: " + content;
        }
    }

    @Override
    public void run() {
        try {
            String inputLine;

            while ((inputLine = in.readLine()) != null && listening) {
                Message message = new Message(inputLine);
                if (message.recip.equals("server")) {
                    if (message.content.equals("###disconnecting###")) {
                        server.sendMessage("all", name, "###potential_chat_disconnected###");
                        break;
                    }
                    else if (message.content.contains("###name=")) {
                        name = message.content.substring(message.content.indexOf("=")+1, message.content.length()-3);

                        int res = server.addClient(this);
                        if (res == 0) {
                            ready = true;
                        } else if (res == 1) {
                            sendMessage("server", "###too_many_connections###");
                            break;
                        } else if (res == 2) {
                            sendMessage("server", "###name_already_taken###");
                            break;
                        }
                    }
                } else {
                    if (ready) {
                        if (message.content.equals("###new_game_window###")) {
                            server.newGame(name, message.recip);
                        }
                        else if (message.content.contains("###new_move")) {
                            server.gameMove(name, message.recip, message.content);
                        }
                        else if (message.content.equals("###game_over###you_win###")) {
                            server.endGame(name, message.recip);
                        }
                        else if (message.content.equals("###new_game_restarted###")) {
                            server.restartGame(name, message.recip);
                        }

                        else {
                            server.sendMessage(message.recip, name, message.content);
                        }
                    }
                }
            }

            out.close();
            in.close();
            socket.close();
            if (ready) {
                server.removeClient(this);
            }

        } catch (IOException e) {

        }
    }
}
