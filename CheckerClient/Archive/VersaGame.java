package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

public class VersaGame extends Thread {
    public static final int [] failure = {0, 0, 0, 0};

    VersaCheckers gamePanel = null;
    VersaFrame gameBoard = null;

    public VersaGame(VersaCheckers game, VersaFrame gameBoard){
        this.gamePanel = game;
        this.gameBoard = gameBoard;
    }

    public void run(){
        while(!gamePanel.gameover){
            if(gamePanel.getTurn() == false){
                int [] fromTo = choosemove(gamePanel.getBoard());
                if( fromTo == failure){
                    gameBoard.notifyWin();
                }else{
                    int from [] = {fromTo[0], fromTo[1]};
                    int to [] = {fromTo[2], fromTo[3]};
                    int res = gameBoard.checkMove(from, to);
                    if ((res == 1) || (res == 2)) {
                        int[] s = from;

                        int type = gamePanel.removePiece(s[0], s[1]);
                        if (to[1] == 0) {
                            gamePanel.addPiece(to[0], to[1], 2);
                        } else {
                            gamePanel.addPiece(to[0], to[1], type);
                        }

                        gameBoard.writeBoard("New move from bot");
                        gamePanel.rotateBoard();
                        if (res == 1) {
                            gamePanel.setSelected(-1, -1);
                        } else if (res == 2) {
                            int dir_x = (int) ((to[0]-s[0])/2);
                            int dir_y = (int) ((to[1]-s[1])/2);
                            gamePanel.removePiece(s[0]+dir_x, s[1]+dir_y);
                            gamePanel.setSelected(to[0], to[1]);
                        }
                    }
                }
            }else{

            }
        }
    }

    public int [] choosemove(int[][] board){
        int [] save = {5,2};
        return save;
    }
}
