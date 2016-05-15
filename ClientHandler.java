/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * ClientHandler
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private int clientNum;

    //Constructors
    public ClientHandler(Socket newSocket, int clientNum) {
        clientSocket = newSocket;
        this.clientNum = clientNum;
    }

    //Run
    public void run() {
        try (
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            String outputLine = "Welcome to the Versa echo server! You are client " + clientNum;
            out.write(outputLine, 0, outputLine.length()); //Writing to server
            //serverWriter.newLine();
            out.write("\r\n"); //Always writes a <CRLF>
            out.flush();
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.equalsIgnoreCase("quit")) {
                    outputLine = "Versa server disconnecting.";
                } else {
                    outputLine = "Server ECHOES: " + inputLine;
                }
                out.write(outputLine, 0, outputLine.length()); //Writing to server
                //serverWriter.newLine();
                out.write("\r\n"); //Always writes a <CRLF>
                out.flush();
                if (outputLine.equals("Versa server disconnecting.")) {
                    break;
                }
            }
            clientSocket.close();
            System.out.println("Ended convo with client #" + clientNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}