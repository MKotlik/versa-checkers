package CheckerClient;

import java.util.List;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */
public class Utility {
    /*
     *Stores all the constants and other static methods.
     *
     *  Board Setup
     * ------------------
     *   01  03  05  07
     * 08  10  12  14
     *   17  19  21  23
     * 24  26  28  30
     *   33  35  37  39
     * 40  42  44  46
     *   49  51  53  55
     * 56  58  60  62
     * ------------------
     *
     */

    /** The number of squares wide a checkers board is */
    public final static int W = 8;
    /** The number of squares tall a checkers board is */
    public final static int H = 8;

    /** The integer specifying a red pawn, as part of the checkers piece enumeration scheme */
    public final static int RED_PAWN = 1;
    /** The integer specifying a black pawn, as part of the checkers piece enumeration scheme */
    public final static int BLU_PAWN = 3;
    /** The integer specifying a blank checkers board square, as part of the checkers piece enumeration scheme */
    public final static int BLANK    = 0;
    /** The integer specifying a red king, as part of the checkers piece enumeration scheme */
    public final static int RED_KING = 2;
    /** The integer specifying a black king, as part of the checkers piece enumeration scheme */
    public final static int BLU_KING = 4;

    /** The lowest int value not in the checkers enumeration. */
    public final static int PIECES_MAX = 6;

    /** The integer specifying the red player, as part of the side enumeration scheme */
    public final static int RED = 0;
    /** The integer specifying the black player, as part of the side enumeration scheme */
    public final static int BLU = 1;
    /** The integer specifying neither player, as part of the side enumeration scheme */
    public final static int NEITHER = 2;

    /**
     * The side which moves first in a checkers game.
     */
    public static final int INITIAL_SIDE = RED;

    /**
     * The board state of the starting position in a checkers game.
     */
    public static final int[] INITIAL_BOARDSTATE;
    static{
        INITIAL_BOARDSTATE = new int[H * W];
        for (int i = 0; i < H * W; i++)
            INITIAL_BOARDSTATE[i] = BLANK;

        int[] L = new int[] {  1,  3,  5,  7,  8, 10, 12, 14, 17, 19, 21, 23 };
        for ( int i : L )
            INITIAL_BOARDSTATE[i] = BLU_PAWN;

        for ( int i : L )
            INITIAL_BOARDSTATE[W * H - 1 - i] = RED_PAWN;
    }

    public static String reportMove(List <Integer> move){
        if (move == null)
            return "null";

        StringBuffer s = new StringBuffer();
        for (int i = 0; i < move.size(); i++)
        {
            s.append(Utility.reportLocation(move.get(i)));
            if (i != move.size() - 1)
                s.append("-");
        }
        return s.toString();
    }

    public static String reportLocation(int loc)
    {
        if (loc < 0 || loc >= W * H)
            return "??";

        String row = "" + (8 - loc / W);
        String col = "" + (char)('a' + (loc % W));
        return col + row;
    }

}
