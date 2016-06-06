package CheckerClient;

import java.util.*;
/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */
public class Move {
    private List <Integer> moves;

    public Move(){
        this.moves = new ArrayList<Integer>();
    }

    public Move(Collection<Integer> moves){
        this.moves = new ArrayList<Integer>(moves);
    }

    public Integer get(int i){
        return moves.get(i);
    }

    public int size(){
        return moves.size();
    }

    public String toString(){
        return Utility.reportMove(moves);
    }

}
