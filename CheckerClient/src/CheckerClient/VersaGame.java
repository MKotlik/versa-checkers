package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

public class VersaGame extends Thread {
    VersaCheckers game = null;
    VersaFrame gameBoard = null;
    boolean botmove = false;

    public VersaGame(VersaCheckers game, VersaFrame gameBoard){
        this.game = game;
        this.gameBoard = gameBoard;
        botmove = game.getTurn() == "bot";
    }

    public void run(){
        while(!game.gameover){
            if(botmove){
                int [] to = choosemove(game.getBoard());
            }else{

            }
        }
    }

    public int [] choosemove(int[][] board){
        int [] save = {5,2};
        return save;
    }
}
