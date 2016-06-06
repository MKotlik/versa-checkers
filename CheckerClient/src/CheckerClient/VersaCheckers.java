package CheckerClient;

/* Copyright (c) 2016, Mikhail Kotlik and Sam Xu
 * Versa Checkers
 * APCS Spring Final Project
 * VersaServer
 */

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class VersaCheckers {

    public VersaCheckersPlayer[] cp;
    public OutputStream logFile;
    public int[] depthLimit;

    public int[] bs;
    public int side;

    public VersaCheckers(){
        cp = new VersaCheckersPlayer[] { null, null };
        logFile = System.out;
        depthLimit = new int[] {-1, -1};

        bs = Utility.INITIAL_BOARDSTATE;
        side = Utility.INITIAL_SIDE;
    }

    public static VersaCheckersPlayer createVersaPlayers(String className, String playerName, int side){
        VersaCheckersPlayer player;
        Class<?> playerClass;
        Constructor<?> cpConst;

        try{
            playerClass = Class.forName(className);
        }catch(ClassNotFoundException e){
            System.out.println(e);
            throw new IllegalArgumentException("Bad className given while constructing createVersaPlayers: " + className);
        }

        try {
            cpConst = playerClass.getConstructor(String.class, int.class);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
            throw new IllegalArgumentException("Cannot load " + className);
        }

        try {
            player = (VersaCheckersPlayer)cpConst.newInstance(playerName, side);
        } catch (InstantiationException e) {
            System.out.println(e);
            throw new IllegalArgumentException("Cannot load " + className);
        } catch (IllegalAccessException e) {
            System.out.println(e);
            throw new IllegalArgumentException("Cannot load " + className);
        } catch (InvocationTargetException e) {
            System.out.println(e);
            throw new IllegalArgumentException("Cannot load " + className);
        }

        return player;
    }

    public void init(){
        for (int i : new int[] {Utility.RED, Utility.BLU} )
            if (depthLimit[i] != -1)
                cp[i].setDepthLimit(depthLimit[i]);


               /* Create game model */
        CheckersModel cm = new CheckersModel(cp, bs, side);

        CheckersController ctl;

        /* Create the Swing GUI */
        CheckersUIController uictl = new CheckersUIController(cm);
        ctl = uictl;
        CheckersUI ui = CheckersUI.launch(cm, ctl);

        /* If any players are HumanPlayer, pass a reference to gui's
            * CheckerBoard widget. This is necessary for HumanPlayer's
            * calculateMove().                                            */
        for (int i : new int[] {RED, BLK})
            if (cp[i] instanceof HumanPlayer)
                ((HumanPlayer)cp[i]).setCheckersBoardWidget(ui.getCheckersBoardWidget());

        /* Pass moveOnClick to the controller */
        for (int i : new int[] {RED, BLK} )
            uictl.setMoveOnClick(i, moveOnClick[i]);

        /* Pass turnDelay to the controller */
        uictl.setTurnDelay(turnDelay);


        /* Pass turnLimit to the controller */
        for (int i : new int[] {Utility.RED, Utility.BLU} )
            ctl.setTurnLimit(i, turnLimit[i]);

        /* Load logger */
        CheckersLogger logger = null;
        if (logFile != null)
            logger = new CheckersLogger(cm, logFile);

        /* Automatically start controller loop (after short delay) */
        ctl.loopLater(500);

    }
}
