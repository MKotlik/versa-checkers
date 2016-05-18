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
     * Checks for server output and prints it to console
     * Checks for client input, blocks, processes it when received
     * Transmits processed input to VersaServer
     */

    //Instance Variables
    ThreadLinker serverLink;

    //Constructor
    public ConsoleHandler(ThreadLinker serverLink) {
        this.serverLink = serverLink;
        new Thread(this, 'CONSOLE_HANDLER').start();
    }

    //Run
    public void run() {
        //Try-with-resources on client i/o
        //While loop with input checks and submissions
    }
}