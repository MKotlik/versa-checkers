package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaClientChat
 */

public class VersaGame extends Thread {
    VersaCheckers game = null;
    boolean gameover = false;
    boolean botmove = false;

    public VersaGame(VersaCheckers game){
        this.game = game;
        botmove = game.getTurn() == "bot";
        gameover =
    }

    public void run(){
        while(!gameover){

            if(botmove){

            }
        }
    }
}
