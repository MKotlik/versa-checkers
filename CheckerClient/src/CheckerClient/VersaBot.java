package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class VersaBot extends VersaClient{

    private VersaClientGUI gui = null;
    private Socket clientSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean listening = true;

    public VersaBot(VersaClientGUI gui){
        super(gui);
    }

    public int evaluateBoard(String board, boolean player){
        int value = 0;

        if (player){
            value = evaluateForPlayer(board);
        }else{
            value = evaluateForBot(board);
        }

        return value;
    }

    public int evaluateForPlayer(String board){
        int value = 0;
        return value;
    }

    public int evaluateForBot(String board){
        int value = 0;
        return value;
    }



}
