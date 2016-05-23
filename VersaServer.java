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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class VersaServer extends JPanel{
    private PlayerList playerList;

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
        playerList = new PlayerList();

        setPreferredSize(new Dimension(600,400));
        setLayout(new BorderLayout());

        add(getPlayerList(),"North");
        add(getLog(), "Center");
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

    public JScrollPane getPlayerList(){
        JScrollPane scroll = new JScrollPane(playerList);
        scroll.setBorder(new TitledBorder(new EtchedBorder(),"Player List"));
        return scroll;
    }

    public JPanel getLog(){
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea text = new JTextArea();
        JScrollPane pane = new JScrollPane(text);
        text.setEditable(false);

        JTextField input = new JTextField();

        panel.add(pane, "Center");
        panel.add(input, "South");
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Log Output"));
        return panel;
    }

    //Instance variables
    private ThreadLinker consoleLink;
    private ThreadLinker socketLink;
    private SynchronizedThreadLinkerMap clientLinkMap;

    //Constructors

    //Accessors

    //Mutators
}