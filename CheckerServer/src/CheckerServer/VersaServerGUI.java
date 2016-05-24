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

        listenButton.setText("Listen");
        listenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        connectionLabel.setText("No connection");
        connectionLabel.setFocusable(false);

        mainTextArea.setColumns(20);
        mainTextArea.setEditable(false);
        mainTextArea.setRows(5);
        mainTextArea.setFocusable(false);
        mainScrollPane.setViewportView(mainTextArea);

        clientListLabel.setText("Client List:");
        clientListLabel.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(95, 95, 95)
                                                                .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(listenButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(stopButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(clientListLabel)))
                                                .addGap(0, 103, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(mainScrollPane)
                                                        .addComponent(connectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(listenButton)
                                        .addComponent(stopButton))
                                .addGap(10, 10, 10)
                                .addComponent(clientListLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(connectionLabel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientListLabel;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton listenButton;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JTextField portField;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables

    //calls server to stop listening
    private class serverWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            server.stopListening();
        }
    }
}
