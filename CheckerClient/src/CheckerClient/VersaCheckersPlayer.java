package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */
public abstract class VersaCheckersPlayer {
    protected String name;

    protected int side;

    protected volatile Move pickedMove;

    protected int depthLimit;

    public VersaCheckersPlayer(String name, int side){
        this.name = name;
        this.side = side;
        depthLimit = 1000;
    }

    public abstract void calculateMove();

    public final Move getMove(){
        return pickedMove;
    }

    protected synchronized void setMove(Move move){
        this.pickedMove = move;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return name;
    }

    public void setDepthLimit(int limit){
        this.depthLimit = limit;
    }

    public int getDepthLimit(){
        return depthLimit;
    }

    public boolean isHuman(){
        return false;
    }
}
