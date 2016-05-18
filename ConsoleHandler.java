/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * ConsoleHandler
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class ConsoleHandler implements Runnable{
    /*
     * Like ClientHandlers, opens I/O connections to console
     * Checks for client input, blocks, processes it when received
     * Transmits processed input to VersaServer
     * Checks for exit msg from server
     */

    //Instance Variables
    private ThreadLinker serverLink;
    private static String[] validCmds = {"START, QUIT"};
    //TODO - update validcmds as more added

    //Constructor
    public ConsoleHandler(ThreadLinker serverLink) {
        this.serverLink = serverLink; //Uses threadB
        new Thread(this, "CONSOLE_HANDLER").start();
    }

    //Run
    public void run() {
        try (BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in))) {
            String consoleInput = null;
            System.out.println("|> "); //print a prompt
            while ((consoleInput = sysIn.readLine()) != null) { //while System.in is open
                if (validateCmd(consoleInput)) {
                    serverLink.updateA(consoleInput); //submit cmd to main thread
                } else {
                    System.out.println("SERVER: Command not recognized");
                }
                if (serverLink.hasMsgForB()) {
                    if (serverLink.retrieveB().equals("EXIT")) { //could change to diff. code
                        System.out.println("CONSOLE_HANDLER: Stopped by main.");
                        break;
                    } //Ignore any msgs that aren't EXIT, they shouldn't be here
                }
                //TODO - check for prompt & server response inconsistencies. Might need to out through handler.
                System.out.println("|> "); //print a prompt
            }
            System.out.println("CONSOLE_HANDLER: System.in closed. Thread stopped.");
        } catch (IOException e) {
            System.out.println("CONSOLE_HANDLER: IOException. Thread stopped, notifying main.");
            serverLink.updateA("IOEXCEPTION"); //could change to diff. code
        }
    }

    //Additional methods

    //Checks if given String is a valid command
    private boolean validateCmd(String cmd) {
        for (String validCmd : validCmds) {
            if (validCmd.equalsIgnoreCase(cmd)) {
                return true;
            }
        }
        return false;
    }
}