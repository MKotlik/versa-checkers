package CheckerClient;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

import checkers.Checkers;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

public class VersaClientGUI extends JFrame {
    private VersaClient client = null;
    public HashMap chats = null;
    private String name = "";
    DefaultListModel userListModel = null;

    private void chatButtonBotActionPerformed(ActionEvent e) {
        final String save;
        if (name.equals("")){
            save = "human";
        }else{
            save = name;
        }
        Thread t = new Thread(new Runnable(){
            @Override
            public void run(){
                new Checkers().init(save);
            }
        });
        t.start();

    }

    public VersaClientGUI() {
        userListModel = new DefaultListModel();
        chats = new HashMap();
        initComponents();

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (userList.getSelectedValue() != null) {
                        newChatWindow((String) userList.getSelectedValue());
                        userList.clearSelection();
                    }
                }
            }
        };
        userList.addMouseListener(mouseListener);


        addWindowListener(new clientWindowListener());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Sam Xu
    private void initComponents() {
        ipAddressField = new JTextField();
        ipAddressLabel = new JLabel();
        connectButton = new JButton();
        portLabel = new JLabel();
        portField = new JTextField();
        connectionLabel = new JLabel();
        disconnectButton = new JButton();
        nameLabel = new JLabel();
        nameField = new JTextField();
        usersLabel = new JLabel();
        chatButton = new JButton();
        usersScrollPane = new JScrollPane();
        userList = new JList();
        chatButtonBot = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("VersaCheckers");
        setResizable(false);
        Container contentPane = getContentPane();

        //---- ipAddressField ----
        ipAddressField.setText("127.0.0.1");
        ipAddressField.setNextFocusableComponent(portField);
        ipAddressField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ipAddressFieldActionPerformed(e);
            }
        });

        //---- ipAddressLabel ----
        ipAddressLabel.setText("IP Address:");
        ipAddressLabel.setFocusable(false);

        //---- connectButton ----
        connectButton.setText("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectButtonActionPerformed(e);
            }
        });

        //---- portLabel ----
        portLabel.setText("Port:");
        portLabel.setFocusable(false);

        //---- portField ----
        portField.setText("1216");
        portField.setNextFocusableComponent(nameField);
        portField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portFieldActionPerformed(e);
            }
        });

        //---- connectionLabel ----
        connectionLabel.setText("Not connected");
        connectionLabel.setFocusable(false);

        //---- disconnectButton ----
        disconnectButton.setText("Disconnect");
        disconnectButton.setEnabled(false);
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnectButtonActionPerformed(e);
            }
        });

        //---- nameLabel ----
        nameLabel.setText("Name:");
        nameLabel.setFocusable(false);

        //---- nameField ----
        nameField.setNextFocusableComponent(connectButton);
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameFieldActionPerformed(e);
            }
        });

        //---- usersLabel ----
        usersLabel.setText("Users:");
        usersLabel.setFocusable(false);

        //---- chatButton ----
        chatButton.setText("New Game");
        chatButton.setEnabled(false);
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatButtonActionPerformed(e);
            }
        });

        //======== usersScrollPane ========
        {

            //---- userList ----
            userList.setModel(userListModel);
            userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            userList.setFocusable(false);
            userList.setMaximumSize(new Dimension(0, 50));
            usersScrollPane.setViewportView(userList);
        }

        //---- chatButtonBot ----
        chatButtonBot.setText("New Bot Game");
        chatButtonBot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatButtonBotActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(connectionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(usersScrollPane, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
                                .addComponent(usersLabel)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(ipAddressLabel)
                                        .addComponent(portLabel, GroupLayout.Alignment.TRAILING)
                                        .addComponent(nameLabel, GroupLayout.Alignment.TRAILING))
                                    .addGap(18, 18, 18)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(ipAddressField)
                                        .addComponent(portField)
                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(36, 36, 36)
                                            .addComponent(connectButton))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addGap(26, 26, 26)
                                            .addComponent(disconnectButton)))))
                            .addContainerGap(43, Short.MAX_VALUE))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addComponent(chatButton)
                    .addGap(18, 18, 18)
                    .addComponent(chatButtonBot)
                    .addGap(0, 116, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ipAddressLabel)
                                .addComponent(ipAddressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(portLabel))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameLabel)))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(connectButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(disconnectButton)))
                    .addGap(18, 18, 18)
                    .addComponent(usersLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(usersScrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chatButton)
                        .addComponent(chatButtonBot))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                    .addComponent(connectionLabel))
        );
        pack();
        setLocationRelativeTo(getOwner());
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
            newChatWindow(sender, true); //I'm assuming that when no chat exists with opponent, window is opened to fix it
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

    //TODO - look at this, notifyWin starts a chat window, this is sus
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
    // Generated using JFormDesigner Evaluation license - Sam Xu
    private JTextField ipAddressField;
    private JLabel ipAddressLabel;
    private JButton connectButton;
    private JLabel portLabel;
    private JTextField portField;
    private JLabel connectionLabel;
    private JButton disconnectButton;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel usersLabel;
    private JButton chatButton;
    private JScrollPane usersScrollPane;
    private JList userList;
    private JButton chatButtonBot;
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
