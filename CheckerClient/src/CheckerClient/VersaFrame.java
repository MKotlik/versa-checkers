package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

import java.util.HashMap;

import java.net.URL;

import javax.swing.*;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

public class VersaFrame extends JFrame {
    private VersaClient client = null;
    private VersaClientGUI gui = null;
    private HashMap prevBoard = null;
    private int num_undos = 0;
    private boolean myTurn = false;
    private URL soundFile = null;
    private VersaCheckers gamePanel;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator centerSeparator;
    private javax.swing.JButton confirmMoveButton;
    private javax.swing.JButton giveupButton;
    private javax.swing.JButton startNewGameButton;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JButton undoMoveButton;
    // End of variables declaration//GEN-END:variables


    public VersaFrame(VersaClient client, VersaClientGUI gui){
        initComponents();
        setTitle("Game with  bot");

        this.client = client;
        this.gui = gui;
        soundFile = this.getClass().getResource("audio/floop.wav");
    }

    private void initComponents(){
        javax.swing.JPanel gamePanel = new CheckersPanel();
        this.gamePanel = (VersaCheckers) gamePanel;
        confirmMoveButton = new javax.swing.JButton();
        undoMoveButton = new javax.swing.JButton();
        turnLabel = new javax.swing.JLabel();
        giveupButton = new javax.swing.JButton();
        startNewGameButton = new javax.swing.JButton();
        centerSeparator = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chat with ...");
        setResizable(false);

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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                        )
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(turnLabel)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(startNewGameButton)
                                                .addComponent(giveupButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(centerSeparator)
                                        .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        )
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(undoMoveButton)
                                                .addComponent(confirmMoveButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

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

    private void confirmMoveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmMoveButtonActionPerformed
        myTurn = false;
        prevBoard = null;
        setTurn(false);
        confirmMoveButton.setEnabled(false);
        undoMoveButton.setEnabled(false);
        gamePanel.setSelected(-1, -1);
    }//GEN-LAST:event_confirmMoveButtonActionPerformed

    private void gamePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gamePanelMousePressed
        int x_pos = (int)Math.floor(evt.getX()/50.0);
        int y_pos = (int)Math.floor(evt.getY()/50.0);

        if (myTurn) {
            if (gamePanel.getSelected() == null &&
                    (gamePanel.getBoard()[y_pos][x_pos] == 1 || gamePanel.getBoard()[y_pos][x_pos] == 2) &&
                    num_undos == 0) {
                gamePanel.setSelected(x_pos, y_pos);
            } else if (gamePanel.getSelected() != null) {
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

    private void giveupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_giveupButtonActionPerformed
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
        setTurn(true);
        startNewGameButton.setEnabled(false);
        giveupButton.setEnabled(true);
    }//GEN-LAST:event_startNewGameButtonActionPerformed

    public void setTurn(boolean turn) {
        myTurn = turn;
        if (turn) {
            turnLabel.setText("Your turn:");
        } else {
            turnLabel.setText("Bot's turn:");
        }
    }
}
