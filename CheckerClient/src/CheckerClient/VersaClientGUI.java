package CheckerClient;

import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

public class VersaClientGUI extends JFrame {
    private VersaClient client = null;
    public HashMap chats = null;
    private String name;
    DefaultListModel userListModel = null;

    public VersaClientGUI(){
        userListModel = new DefaultListModel();
        chats = new HashMap();
        initComponents();

        //Need mouseListener
    }

    /**
     * Don't change anything below
     * They are directly affected by the form editior
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ipAddressField = new javax.swing.JTextField();
        ipAddressLabel = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        portLabel = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        connectionLabel = new javax.swing.JLabel();
        disconnectButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        usersLabel = new javax.swing.JLabel();
        chatButton = new javax.swing.JButton();
        usersScrollPane = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VersaCheckers");
        setResizable(false);

        ipAddressField.setText("127.0.0.1");
        ipAddressField.setNextFocusableComponent(portField);
        ipAddressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ipAddressFieldActionPerformed(evt);
            }
        });

        ipAddressLabel.setText("IP Address:");
        ipAddressLabel.setFocusable(false);

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        portLabel.setText("Port:");
        portLabel.setFocusable(false);

        portField.setText("1216");
        portField.setNextFocusableComponent(nameField);
        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });

        connectionLabel.setText("Not connected");
        connectionLabel.setFocusable(false);

        disconnectButton.setText("Disconnect");
        disconnectButton.setEnabled(false);
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        nameLabel.setText("Name:");
        nameLabel.setFocusable(false);

        nameField.setNextFocusableComponent(connectButton);
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        usersLabel.setText("Users:");
        usersLabel.setFocusable(false);

        chatButton.setText("New Game");
        chatButton.setEnabled(false);
        chatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatButtonActionPerformed(evt);
            }
        });

        userList.setModel(userListModel);
        userList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        userList.setFocusable(false);
        userList.setMaximumSize(new java.awt.Dimension(0, 50));
        usersScrollPane.setViewportView(userList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(connectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(usersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(usersLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(ipAddressLabel)
                                                                        .addComponent(portLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(nameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(ipAddressField)
                                                                        .addComponent(portField)
                                                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(36, 36, 36)
                                                                                .addComponent(connectButton))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(26, 26, 26)
                                                                                .addComponent(disconnectButton)))))
                                                .addContainerGap())))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(chatButton)
                                .addGap(143, 143, 143))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(ipAddressLabel)
                                                        .addComponent(ipAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(portLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(nameLabel)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(connectButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(disconnectButton)))
                                .addGap(18, 18, 18)
                                .addComponent(usersLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(usersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chatButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(connectionLabel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ipAddressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipAddressFieldActionPerformed
        if (connectButton.isEnabled()) {
            connectClient();
        }
    }//GEN-LAST:event_ipAddressFieldActionPerformed

    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        if (connectButton.isEnabled()) {
            connectClient();
        }
    }//GEN-LAST:event_portFieldActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        connectClient();
    }//GEN-LAST:event_connectButtonActionPerformed

    private void connectClient() {
        String IPAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());
        name = nameField.getText();

        if (name.equals("")) {
            connectionLabel.setText("You need a name");
            return;
        }

        VersaClient c = new VersaClient(this);
        if (c.connect(IPAddress, port, name) == 1) {
            this.client = c;
            connectionLabel.setText("Connected! You can now start a new game.");
            disconnectButton.setEnabled(true);
            connectButton.setEnabled(false);
            chatButton.setEnabled(true);

            ipAddressField.setEnabled(false);
            portField.setEnabled(false);
            nameField.setEnabled(false);

            this.client.start();
        } else {
            connectionLabel.setText("Error connecting...");
        }
    }

    public void setUserList(String[] names) {
        userListModel.clear();
        for (String userName : names) {
            if (!userName.equals(name)) {
                userListModel.addElement(userName);
            }
            if (chats.containsKey(userName)) {
                VersaClientChat chat = (VersaClientChat) chats.get(userName);
                chat.partnerExists();
            }
        }
    }

    public void recievedMessage(String sender, String message) {
        if (!chats.containsKey(sender)) {
            newChatWindow(sender);
        }
        VersaClientChat chat = (VersaClientChat) chats.get(sender);
        chat.addToChatField(message);
    }

    public void updateGame(String sender, String board, String message) {
        updateGame(sender, board, name, message);
    }

    public void updateGame(String sender, String board, String turn, String message) {
        if (!chats.containsKey(sender)) {
            newChatWindow(sender, true);
        } else {
            int[][] realBoard = new int[8][8];
            String res = board.substring(1, board.length()-1);
            String[] rows = res.split("\\],\\[");
            rows[0] = rows[0].substring(1, rows[0].length());
            rows[7] = rows[7].substring(0, rows[7].length()-1);

            for (int y = 0; y < 8; y++) {
                String chars[] = rows[y].split(",");
                for (int x = 0; x < 8; x++) {
                    realBoard[y][x] = Integer.parseInt(chars[x]);
                }
            }

            VersaClientChat game = (VersaClientChat) chats.get(sender);
            String final_message = message;
            game.setTurn(turn.equals(name));

            if (message.equals("A new game has been started.")) {
                if (turn.equals(name)) {
                    final_message = "You have started a new game!";
                } else {
                    final_message = sender+" has started a new game with you!";
                    game.restartGame();
                }
            }
            game.writeBoard(realBoard, final_message);
        }
    }

    public void notifyWin(String partner) {
        if (!chats.containsKey(partner)) {
            newChatWindow(partner, true);
        }
        VersaClientChat game = (VersaClientChat) chats.get(partner);
        game.notifyWin();
    }

    public void newChatWindow(String partner) {
        newChatWindow(partner, false);
    }

    public void newChatWindow(String partner, boolean auto) {
        if (!chats.containsKey(partner)) {
            final VersaClientChat newChat = new VersaClientChat(partner, client, this);
            chats.put(partner, newChat);
            if (auto) {
                newChat.soundNotification();
            }
            client.sendMessage(partner, "###new_game_window###");
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    newChat.setVisible(true);
                }
            });
        } else {
            System.err.println("A game with this person is already open");
            connectionLabel.setText("game with this person already open");
        }
    }

    public void connectionDied(String partner) {
        if (chats.containsKey(partner)) {
            VersaClientChat chatWindow = (VersaClientChat) chats.get(partner);
            chatWindow.disconnect("Your partner has disconnected");
        }
    }

    public void cleanUpChatWindowClosed(String partner) {
        chats.remove(partner);
    }

    public void disconnect(String reason) {
        if (client.disconnect() == 1) {
            connectionLabel.setText(reason);
            disconnectButton.setEnabled(false);
            connectButton.setEnabled(true);
            chatButton.setEnabled(false);

            ipAddressField.setEnabled(true);
            portField.setEnabled(true);
            nameField.setEnabled(true);

            if (!chats.isEmpty()) {
                for (VersaClientChat chat : (VersaClientChat[]) chats.values().toArray(new VersaClientChat[0])) {
                    chat.disconnect(reason);
                }
            }

            userListModel.clear();

            client = null;
            name = "";
        }
    }

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed
        disconnect("Disconnected");
    }//GEN-LAST:event_disconnectButtonActionPerformed

    private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatButtonActionPerformed
        if (userList.getSelectedValue() != null) {
            newChatWindow((String) userList.getSelectedValue());
            userList.clearSelection();
        }
    }//GEN-LAST:event_chatButtonActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        if (connectButton.isEnabled()) {
            connectClient();
        }
    }//GEN-LAST:event_nameFieldActionPerformed

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VersaClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VersaClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VersaClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VersaClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VersaClientGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chatButton;
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JTextField ipAddressField;
    private javax.swing.JLabel ipAddressLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField portField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JList userList;
    private javax.swing.JLabel usersLabel;
    private javax.swing.JScrollPane usersScrollPane;
    // End of variables declaration//GEN-END:variables

    private class clientWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            if (client != null) {
                disconnect("");
            }
        }
    }

}
