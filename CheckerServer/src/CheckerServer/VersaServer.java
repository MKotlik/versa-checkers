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
    private ServerSocket serverSocket = null;
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


    public int addClient(VersaServerThread client){
        if(clients.containsKey(client.name)){
            System.err.println("Please choose another name");
            return 2;
        }else if(num_connected >= max_connections){
            System.err.println("Connections full");
            return 1;
        }else{
            clients.put(client.name, client);
            num_connected += 1;
            sendClientList();
            return 0;
        }
    }

    public void removeClient(VersaServerThread client){
        if(clients.containsKey(client.name)){
            clients.remove(client.name);
            num_connected -= 1;
            sendClientList();
        }else{
            System.err.println("Unable to remove client, are you sure it exist?");
        }
    }

    private void sendClientList(){
        String names = "###name_list=";
        String [] clientIPList = new String[clients.size()];
        VersaServerThread[] clientsList = (VersaServerThread[]) clients.values().toArray(new VersaServerThread[0]);
        for(int n = 0; n < clientsList.length; n++) {
            clientIPList[n] = String.valueOf(clientsList[n].getSocket().getRemoteSocketAddress()) + " " + clientsList[n].name;
            names += clientsList[n].name + ",";
        }
        names = names.substring(0, names.length()-1) + "###";
        gui.writeClientList(clientIPList);

        for(int n = 0; n < clientsList.length; n++) {
            if (clientsList[n] != null) {
                clientsList[n].sendMessage("server", names);
            }
        }
    }

    //TODO - generatize HashMap games as <String, VersaCheckers>
    public int startListener(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new HashMap();
            games = new HashMap();
            clientListener = new ClientFinder(this, serverSocket, games);
            clientListener.start();
            listening = true;
            return 1;
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            return 0;
        } catch (IllegalArgumentException e) {
            System.err.println("Bad port");
            return 0;
        }
    }

    public int stopListening() {
        try {
            if (listening) {
                for (VersaServerThread client : (VersaServerThread[]) clients.values().toArray(new VersaServerThread[0])) {
                    client.sendMessage("server", "###disconnected###");
                    client.kill();
                }
                clientListener.kill();
                clientListener = null;
                serverSocket.close();
                gui.writeClientList(new String[0]);
                clients.clear();
                games.clear();
                num_connected = 0;
                listening = false;
            }
            return 1;
        } catch (IOException e) {
            System.err.println("Could not close server.");
            return 0;
        }
    }

    public void sendMessage(String receiver, String sender, String message) {
        if (clients.containsKey(receiver)) {
            VersaServerThread threadReceiver = (VersaServerThread) clients.get(receiver);
            threadReceiver.sendMessage(sender, message);
        } else if (receiver.equals("all")) {
            VersaServerThread[] clientsList = (VersaServerThread[]) clients.values().toArray(new VersaServerThread[0]);
            for (VersaServerThread c : clientsList) {
                c.sendMessage(sender, message);
            }
        } else {
            System.err.println("Error sending message to " + receiver);
        }
    }
}
