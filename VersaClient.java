/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClient
 */

import java.net.*;
import java.io.*;

public class VersaClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNum = 16;

        try (
                Socket mainSocket = new Socket(hostName, portNum);
                BufferedWriter serverOut = new BufferedWriter(new OutputStreamWriter(mainSocket.getOutputStream()));
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
        ) {
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            String userInput;

            while ((inputLine = serverIn.readLine()) != null) {
                System.out.println(inputLine);
                if (inputLine.equalsIgnoreCase("Versa server disconnecting.")) {
                    break;
                }
                userInput = userIn.readLine();
                if (userInput != null) {
                    serverOut.write(userInput, 0, userInput.length()); //Writing to server
                    //serverWriter.newLine();
                    serverOut.write("\r\n"); //Always writes a <CRLF>
                    serverOut.flush();
                } else {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("No such host: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }

}