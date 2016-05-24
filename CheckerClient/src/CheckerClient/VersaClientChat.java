package CheckerClient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;

import javax.imageio.ImageIO;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

public class VersaClientChat extends JFrame{
    private VersaClient client = null;
    private VersaClientGUI gui = null;
    private String partner = "";
    private HashMap prevBoard = null;
    private int num_undos = 0;
    private boolean myTurn = false;
    private URL soundFile = null;

    private SimpleAttributeSet yourNameStyle = null;
    private SimpleAttributeSet hisNameStyle = null;
    private SimpleAttributeSet messageNameStyle = null;

    private StyledDocument chatDoc = null;

    public VersaClientChat(String partner, VersaClient client, VersaClientGUI gui) {
        initComponents();
        setTitle("Game with " + partner);
        this.client = client;
        this.gui = gui;
        this.partner = partner;
        soundFile = this.getClass().getResource("assets/audio/floop.wav");

        yourNameStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(yourNameStyle, Color.RED);
        StyleConstants.setBold(yourNameStyle, true);

        hisNameStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(hisNameStyle, Color.BLUE);
        StyleConstants.setBold(hisNameStyle, true);

        messageNameStyle = new SimpleAttributeSet();

        chatDoc = chatTextPane.getStyledDocument();

        addWindowListener(new chatWindowListener());

        chatTextPane.setEditable(false);
        DefaultCaret caret = (DefaultCaret)chatTextPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        javax.swing.JPanel gamePanel = new CheckersPanel();
        this.gamePanel = (CheckersPanel) gamePanel;
        centerSeparator = new javax.swing.JSeparator();
        confirmMoveButton = new javax.swing.JButton();
        undoMoveButton = new javax.swing.JButton();
        turnLabel = new javax.swing.JLabel();
        connectionLabel = new javax.swing.JLabel();
        chatLabel = new javax.swing.JLabel();
        giveupButton = new javax.swing.JButton();
        startNewGameButton = new javax.swing.JButton();
        chatScrollPane = new javax.swing.JScrollPane();
        chatTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat with ...");
        setResizable(false);

        sendField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFieldActionPerformed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        gamePanel.setBackground(java.awt.Color.white);
        gamePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gamePanel.setFocusable(false);
        gamePanel.setMaximumSize(new java.awt.Dimension(400, 400));
        gamePanel.setMinimumSize(new java.awt.Dimension(400, 400));
        gamePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                gamePanelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        centerSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        confirmMoveButton.setText("Confirm Move");
        confirmMoveButton.setEnabled(false);
        confirmMoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmMoveButtonActionPerformed(evt);
            }
        });

        undoMoveButton.setText("Undo");
        undoMoveButton.setEnabled(false);
        undoMoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMoveButtonActionPerformed(evt);
            }
        });

        turnLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        turnLabel.setText("Your turn:");

        connectionLabel.setText("You've started a new game");

        chatLabel.setText("Chat:");

        giveupButton.setText("Give up");
        giveupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                giveupButtonActionPerformed(evt);
            }
        });

        startNewGameButton.setText("Start new game");
        startNewGameButton.setEnabled(false);
        startNewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startNewGameButtonActionPerformed(evt);
            }
        });

        chatScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chatScrollPane.setAutoscrolls(true);
        chatScrollPane.setFocusable(false);
        chatScrollPane.setHorizontalScrollBar(null);
        chatScrollPane.setMaximumSize(new java.awt.Dimension(329, 400));
        chatScrollPane.setMinimumSize(new java.awt.Dimension(329, 400));
        chatScrollPane.setPreferredSize(new java.awt.Dimension(329, 400));

        chatTextPane.setEditable(false);
        chatTextPane.setAutoscrolls(true);
        chatTextPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chatTextPane.setFocusable(false);
        chatTextPane.setMaximumSize(new java.awt.Dimension(329, 400));
        chatTextPane.setMinimumSize(new java.awt.Dimension(329, 400));
        chatTextPane.setPreferredSize(new java.awt.Dimension(329, 400));
        chatScrollPane.setViewportView(chatTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addComponent(turnLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(startNewGameButton)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(giveupButton))
                                                        .addComponent(gamePanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addGap(119, 119, 119)
                                                                .addComponent(confirmMoveButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(undoMoveButton)))
                                                .addGap(12, 12, 12)
                                                .addComponent(centerSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(sendField)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(sendButton))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(chatLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(chatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(connectionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(turnLabel))
                                                .addComponent(chatLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(startNewGameButton)
                                                .addComponent(giveupButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(centerSeparator)
                                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(chatScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sendButton)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(undoMoveButton)
                                                .addComponent(sendField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(confirmMoveButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(connectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // On action performed functions
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendText();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void sendFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFieldActionPerformed
        if (sendButton.isEnabled()) {
            sendText();
        }
    }//GEN-LAST:event_sendFieldActionPerformed

    // Functions to communicate with chat
    private void sendText() {
        String txt = sendField.getText();
        if (!txt.equals("")) {
            if (client.sendMessage(partner, txt) == 1) {
                try {
                    chatDoc.insertString(chatDoc.getLength(), "You: ", yourNameStyle);
                    chatDoc.insertString(chatDoc.getLength(), txt + "\n", messageNameStyle);
                } catch(Exception e) {
                    System.err.println(e);
                }
                sendField.setText("");
            } else {
                System.err.println("Error writing message");
            }
        }
    }

    public void addToChatField(String message) {
        try {
            chatDoc.insertString(chatDoc.getLength(), partner+": ", hisNameStyle);
            chatDoc.insertString(chatDoc.getLength(), message + "\n", messageNameStyle);
        } catch(Exception e) {
            System.err.println(e);
        }
    }

    public void partnerExists() {
        if (!sendButton.isEnabled()) {
            sendButton.setEnabled(true);
            connectionLabel.setText("You are reconnected!");
        }
    }

    public void disconnect(String reason) {
        sendButton.setEnabled(false);
        connectionLabel.setText(reason);
    }

    private void confirmMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmMoveButtonActionPerformed
        myTurn = false;
        client.sendMessage(partner, "###new_move###new_board="+gamePanel.getFormattedBoard()+"###");
        prevBoard = null;
        setTurn(false);
        confirmMoveButton.setEnabled(false);
        undoMoveButton.setEnabled(false);
        gamePanel.setSelected(-1, -1);
        connectionLabel.setText("Move submitted...");
    }//GEN-LAST:event_confirmMoveButtonActionPerformed

    private void undoMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMoveButtonActionPerformed
        int[][] pBoard = (int[][]) prevBoard.get(num_undos);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (pBoard[y][x] - gamePanel.getBoard()[y][x] == 1) {
                    gamePanel.setSelected(x, y);
                }
            }
        }

        gamePanel.setBoard(pBoard);
        num_undos -= 1;
        if (num_undos == 0) {
            gamePanel.setSelected(-1, -1);
            confirmMoveButton.setEnabled(false);
            undoMoveButton.setEnabled(false);
        }
    }//GEN-LAST:event_undoMoveButtonActionPerformed

    private void gamePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMousePressed
        int x_pos = (int)Math.floor(evt.getX()/50.0);
        int y_pos = (int)Math.floor(evt.getY()/50.0);

        if (myTurn) {
            if (gamePanel.selected == null &&
                    (gamePanel.getBoard()[y_pos][x_pos] == 1 || gamePanel.getBoard()[y_pos][x_pos] == 2) &&
                    num_undos == 0) {
                gamePanel.setSelected(x_pos, y_pos);
            } else if (gamePanel.selected != null) {
                int[] to = {x_pos, y_pos};
                int res = checkMove(gamePanel.getSelected(), to);
                if ((res == 1 && num_undos == 0) || (res == 2)) {
                    num_undos += 1;
                    prevBoard.put(num_undos, gamePanel.getBoard());
                    int[] s = gamePanel.getSelected();

                    int type = gamePanel.removePiece(s[0], s[1]);
                    if (to[1] == 0) {
                        gamePanel.addPiece(to[0], to[1], 2);
                    } else {
                        gamePanel.addPiece(to[0], to[1], type);
                    }

                    confirmMoveButton.setEnabled(true);
                    undoMoveButton.setEnabled(true);
                    if (res == 1) {
                        gamePanel.setSelected(-1, -1);
                    } else if (res == 2) {
                        int dir_x = (int) ((to[0]-s[0])/2);
                        int dir_y = (int) ((to[1]-s[1])/2);
                        gamePanel.removePiece(s[0]+dir_x, s[1]+dir_y);
                        gamePanel.setSelected(to[0], to[1]);
                    }
                } else if (num_undos == 0) {
                    gamePanel.setSelected(-1, -1);
                }
            }
        }
    }//GEN-LAST:event_gamePanelMousePressed

    private void giveupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giveupButtonActionPerformed
        client.sendMessage(partner, "###game_over###you_win###");
        turnLabel.setText("You lost!");

        myTurn = false;
        prevBoard = null;
        confirmMoveButton.setEnabled(false);
        undoMoveButton.setEnabled(false);
        gamePanel.setSelected(-1, -1);
        startNewGameButton.setEnabled(true);
        giveupButton.setEnabled(false);
    }//GEN-LAST:event_giveupButtonActionPerformed

    private void startNewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startNewGameButtonActionPerformed
        client.sendMessage(partner, "###new_game_restarted###");
        setTurn(true);
        startNewGameButton.setEnabled(false);
        giveupButton.setEnabled(true);
    }//GEN-LAST:event_startNewGameButtonActionPerformed

    // Functions to communicate with the Checkers game
    public void setTurn(boolean turn) {
        myTurn = turn;
        if (turn) {
            turnLabel.setText("Your turn:");
        } else {
            turnLabel.setText(partner+"'s turn:");
        }
    }

    public void soundNotification() {
        try {
            AudioInputStream soundIn = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = soundIn.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(soundIn);
            clip.start();
            while(clip.isRunning())
            {
                Thread.yield();
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void writeBoard(int[][] board, String message) {
        gamePanel.setBoard(board);
        prevBoard = new HashMap();
        num_undos = 0;
        connectionLabel.setText(message);

        if (message.contains("New move from ")) {
            soundNotification();
        }

        if (checkEnd()) {
            turnLabel.setText("You lost!");
            client.sendMessage(partner, "###game_over###you_win###");

            myTurn = false;
            prevBoard = null;
            confirmMoveButton.setEnabled(false);
            undoMoveButton.setEnabled(false);
            gamePanel.setSelected(-1, -1);
            startNewGameButton.setEnabled(true);

            JOptionPane.showMessageDialog(null, "You lost! Better luck next time.", "You Lost", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void restartGame() {
        startNewGameButton.setEnabled(false);
        giveupButton.setEnabled(true);
    }

    public void notifyWin() {
        turnLabel.setText("You won!");
        myTurn = false;
        prevBoard = null;
        confirmMoveButton.setEnabled(false);
        undoMoveButton.setEnabled(false);
        gamePanel.setSelected(-1, -1);
        startNewGameButton.setEnabled(true);
        giveupButton.setEnabled(false);

        JOptionPane.showMessageDialog(null, "You won! Congrats.", "You Won", JOptionPane.INFORMATION_MESSAGE);
    }

    private int checkMove(int[] from, int[] to) {
        if (gamePanel.getBoard()[to[1]][to[0]] == 0) {
            // If the new position is above the old position (only for non-kings)
            if (gamePanel.getBoard()[from[1]][from[0]] == 1 && to[1] > from[1]) {
                return 0;
            }

            // If the new position is directly diagonal to the piece
            if (Math.abs(to[0]-from[0]) == 1 && Math.abs(to[1]-from[1]) == 1) {
                return 1;
            }

            // If the new position is two spaces away diagonal, and there is an enemy in the way
            if (Math.abs(to[0]-from[0]) == 2 && Math.abs(to[1]-from[1]) == 2) {
                int dir_x = (int) ((to[0]-from[0])/2);
                int dir_y = (int) ((to[1]-from[1])/2);
                int enemy_num = gamePanel.getBoard()[from[1]+dir_y][from[0]+dir_x];
                if (enemy_num == 3 || enemy_num == 4) {
                    return 2;
                }
            }
        }
        return 0;
    }

    private boolean checkEnd() {
        int[][] b = gamePanel.getBoard();
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (b[y][x] == 1 || b[y][x] == 2) {
                    for (int j = 0; j < 8; j++) {
                        for (int k = 0; k < 8; k++) {
                            int[] from = {x, y};
                            int[] to = {k, j};
                            if (checkMove(from, to) != 0) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private class CheckersPanel extends JPanel {
        private int[][] board = null;
        private int[] selected = null;

        private Image red_piece = null;
        private Image blue_piece = null;
        private Image red_piece_king = null;
        private Image blue_piece_king = null;

        public CheckersPanel() {
            try {
                red_piece = ImageIO.read(this.getClass().getResource("assets/images/red_piece.png"));
                blue_piece = ImageIO.read(this.getClass().getResource("assets/images/blue_piece.png"));
                red_piece_king = ImageIO.read(this.getClass().getResource("assets/images/red_piece_king.png"));
                blue_piece_king = ImageIO.read(this.getClass().getResource("assets/images/blue_piece_king.png"));
            } catch (IOException e) {
                System.out.println("could not open file");
            }
        }

        public int[] getSelected() {
            return selected;
        }

        public void setSelected(int x, int y) {
            if (x == -1 && y == -1) {
                selected = null;
            } else {
                selected = new int[2];
                selected[0] = x;
                selected[1] = y;
            }
            repaint();
        }

        public void addPiece(int x, int y, int type) {
            if (board[y][x] == 0 && (type == 1 || type == 2 || type == 3 || type ==3)) {
                board[y][x] = type;
                repaint();
            } else {
                System.err.println("Error: A piece is already there");
            }
        }

        public int removePiece(int x, int y) {
            if (board[y][x] != 0) {
                int res = board[y][x];
                board[y][x] = 0;
                repaint();
                return res;
            } else {
                System.err.println("Error: no piece exists there");
                return 0;
            }
        }

        public int[][] getBoard() {
            int[][] b = new int[8][8];
            for (int y = 0; y < 8; y++) {
                System.arraycopy(board[y], 0, b[y], 0, 8);
            }
            return b;
        }

        public String getFormattedBoard() {
            String res = "[";
            for (int[] y : board) {
                res += "[";
                for (int x: y) {
                    res += x+",";
                }
                res = res.substring(0, res.length()-1);
                res += "],";
            }
            res = res.substring(0, res.length()-1);
            res += "]";
            return res;
        }

        public void setBoard(int[][] newBoard) {
            this.board = newBoard;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Draw the background
            g.setColor(Color.BLACK);
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    if ((x+y) % 2 == 0) {
                        g.fillRect(x*50, y*50, 50, 50);
                    }
                }
            }

            //Draw the selection
            g.setColor(Color.GREEN);
            if (selected != null) {
                g.fillRect(selected[0]*50, selected[1]*50, 50, 50);
            }

            //Draw the pieces
            if (board != null) {
                for (int y = 0; y < board.length; y++) {
                    for (int x = 0; x < board.length; x++) {
                        if (board[y][x] == 1) {
                            g.drawImage(red_piece, x*50+5, y*50+5, x*50+45, y*50+45,
                                    0, 0, red_piece.getWidth(null), red_piece.getHeight(null), null);
                        } else if (board[y][x] == 2) {
                            g.drawImage(red_piece_king, x*50+5, y*50+5, x*50+45, y*50+45,
                                    0, 0, red_piece_king.getWidth(null), red_piece_king.getHeight(null), null);
                        } else if (board[y][x] == 3) {
                            g.drawImage(blue_piece, x*50+5, y*50+5, x*50+45, y*50+45,
                                    0, 0, blue_piece.getWidth(null), blue_piece.getHeight(null), null);
                        } else if (board[y][x] == 4) {
                            g.drawImage(blue_piece_king, x*50+5, y*50+5, x*50+45, y*50+45,
                                    0, 0, blue_piece_king.getWidth(null), blue_piece_king.getHeight(null), null);
                        }
                    }
                }
            }
        }
    }

    private CheckersPanel gamePanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator centerSeparator;
    private javax.swing.JLabel chatLabel;
    private javax.swing.JScrollPane chatScrollPane;
    private javax.swing.JTextPane chatTextPane;
    private javax.swing.JButton confirmMoveButton;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JButton giveupButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField sendField;
    private javax.swing.JButton startNewGameButton;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JButton undoMoveButton;
    // End of variables declaration//GEN-END:variables

    // on window close listener
    private class chatWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            gui.cleanUpChatWindowClosed(partner);
        }
    }
}
