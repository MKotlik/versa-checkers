package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

import java.util.HashMap;

public class VersaBot extends VersaClientGUI{
    private VersaClient client = null;
    public HashMap chats = null;
    private String name;
    private int portNum = 0;
    private String ip = "";

    public VersaBot(String name, int port, String IP){
        chats = new HashMap();

        this.name = name;
        portNum = port;
        ip = IP;
    }

    private void connectClient() {
        String IPAddress = ip;
        int port = portNum;


        VersaClient c = new VersaClient(this);
        if (c.connect(IPAddress, port, name) == 1) {
            this.client = c;

            this.client.start();
        } else {}
    }

    public void setUserList(String[] names) {}

    public void notifyWin(String partner){}

    public void disconnect(String reason) {
        if (client.disconnect() == 1) {
            if (!chats.isEmpty()) {
                for (VersaClientChat chat : (VersaClientChat[]) chats.values().toArray(new VersaClientChat[0])) {
                    chat.disconnect(reason);
                }
            }
            client = null;
            name = "";
        }
    }

    public void newChatWindow(String partner, boolean auto) {
        if (!chats.containsKey(partner)) {
            final VersaClientChat newChat = new VersaClientChat(partner, client, this);
            chats.put(partner, newChat);
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {}
            });
        }else {
            System.err.println("A game with this person is already open");
        }
    }

    public void recievedMessage(String sender, String message) {
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VersaBot("Bot1234",1216,"127.0.0.1").connectClient();
            }
        });
    }


}
