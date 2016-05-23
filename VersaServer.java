/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

import java.net.*;
import java.io.*;
import java.util.*;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.*;

public class VersaServer extends JPanel{
    /*
     * Spawns new hostIO thread
     * Spawns new SocketHandler thread
     * In loop:
     * Checks for new hostIO messages
     * Responds to hostIO messages
     * Checks for new SocketHandler messages
     * Responds to SocketHandler messages
     */
    public VersaServer(){
        setPreferredSize(new Dimension(600,400));
        setLayout(new BorderLayout());
    }

    //Main
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e){
            e.printStackTrace();
        }

        VersaServer as = new VersaServer();
        JFrame frame = new JFrame("Versa-Checkers Server");
        frame.add(as);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Instance variables
    private ThreadLinker consoleLink;
    private ThreadLinker socketLink;
    private SynchronizedThreadLinkerMap clientLinkMap;

    //Constructors

    //Accessors

    //Mutators
}