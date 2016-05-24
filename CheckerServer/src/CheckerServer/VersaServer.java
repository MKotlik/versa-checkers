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

    public void newGame(String client1, String client2){
        String save = "";
        if(games.containsKey(client1+";" + client2)){
            save = client1 + ";" + client2;
        }else if(games.containsKey(client2 + ":" + client1)){
            save = client2 + ":" + client1;
        }
        if(!save.equals("")){
            VersaCheckers game = (VersaCheckers) games.get(save);
            String board;
            if(save.substring(0, save.indexOf(":")).equals(client1)){
                board = game.getBoard();
            }else{
                board = game.getRotated(game.getBoard());
            }
            sendMessage(client1, client2, "###game_already_exists###board="+board+"###turn="+game.getTurn()+"###");
        }else {
            VersaCheckers game = new VersaCheckers(client1, client2);
            games.put(client1+":"+client2, game);
            sendMessage(client1, client2, "###new_game_started###board="+game.getBoard()+"###");
        }
    }

    public void endGame(String loser, String winner){
        String save = "";
        if(games.containsKey(loser+":"+winner)){
            save = loser+":"+winner;
        }else if(games.containsKey(winner+":"+loser)){
            save = winner+":"+loser;
        }else{
            System.err.println("unable to disconnect, does not exist");
        }
        sendMessage(winner, loser, "###you_won###");
        games.remove(save);
    }

    public void restartGame(String client1, String client2){
        VersaCheckers game = new VersaCheckers(client1, client2);
        games.put(client1+":"+client2, game);
        sendMessage(client1, client2,
                "###new_game_restarted###board="+game.getBoard()+"###turn="+game.getTurn()+"###");
        sendMessage(client2, client1,
                "###new_game_restarted###board="+game.getRotated(game.getBoard())+"###turn="+game.getTurn()+"###");
    }

    public void gameMove(String from, String to, String message) {
        String gameString = "";
        if (games.containsKey(from+":"+to)) {
            gameString = from+":"+to;
        } else if (games.containsKey(to+":"+from)) {
            gameString = to+":"+from;
        } else {
            System.err.println("Error: gameMove registered for non-existant game");
        }

        VersaCheckers game = (VersaCheckers) games.get(gameString);

        int[][] realBoard = new int[8][8];
        String res = message.substring(message.indexOf("###new_board=")+14, message.length()-4);
        String[] rows = res.split("\\],\\[");
        rows[0] = rows[0].substring(1, rows[0].length());
        rows[7] = rows[7].substring(0, rows[7].length()-1);

        for (int y = 0; y < 8; y++) {
            String chars[] = rows[y].split(",");
            for (int x = 0; x < 8; x++) {
                realBoard[y][x] = Integer.parseInt(chars[x]);
            }
        }

        game.setBoard(realBoard);
        game.changeTurns();
        String newBoard;
        if (gameString.substring(0, gameString.indexOf(":")).equals(to)) {
            newBoard = game.getBoard();
        } else {
            newBoard = game.getRotated(game.getBoard());
        }
        sendMessage(to, from, "###checkers_move###new_board="+newBoard+"###");
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

    public int startListener(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clients = new HashMap();
            games = new HashMap();
            clientListener = new ClientFinder(this, serverSocket);
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
