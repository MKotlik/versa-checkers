package CheckerServer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServerGUI
 */

public class VersaServerGUI  extends JFrame{
    private VersaServer server;

    public VersaServerGUI(){
        init();
        server = new VersaServer(this);
        addWindowListener(new serverWindowListener());
    }

    /**
     * This method is called by the constructor to create the form
     * WARNING: Modifying this code will affect the form editor
     * So don't touch this without editing the form file before hand
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void init(){
        portField = new javax.swing.JTextField();
        listenButton = new javax.swing.JButton();
        stopButton;
        connectionLabel;
        mainScrollPane;
        mainTextArea;
        clientListLabel;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("VersaServer");

        portField.setText("1216");
        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });
    }


    //calls server to stop listening
    private class serverWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            server.stopListening();
        }
    }
}
