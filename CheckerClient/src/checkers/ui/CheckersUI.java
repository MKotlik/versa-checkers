package checkers.ui;

import checkers.CheckersController;
import checkers.CheckersModel;
import checkers.Utility;

import static checkers.CheckersConstants.*;

import java.lang.reflect.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CheckersUI extends JFrame implements ChangeListener, ActionListener
{
    CheckersModel model;
    CheckersUIController ctl;
    checkers.ui.CheckersBoardModel cbm;

    JLabel statusLabel;
    JLabel[] sideLabel;
    JLabel[] nameLabel;
    JLabel[] clockLabel;

    javax.swing.Timer updateLabelTimer;

    java.util.List<JLabel> labelList;

    private static boolean se5hack; 
    static
    {
        String version = System.getProperty("java.specification.version");
        se5hack = version.equals("1.5");
    }

    public CheckersUI(CheckersModel model, CheckersUIController ctl)
    {
        super("Checkers");

        this.model = model;
        this.ctl = ctl;

        /* No component desire variable dimensions, so do not allow resizing
         * this frame.  */
        setResizable(false);

        /* Create this frame's content pane */
        Container pane = getContentPane();

        /* Set pane background to black */
        pane.setBackground(Color.lightGray);

        /* Create labels */
        statusLabel = new JLabel();
        sideLabel = new JLabel[] { new JLabel(), new JLabel() };
        nameLabel = new JLabel[] { new JLabel(), new JLabel() };
        clockLabel = new JLabel[] { new JLabel(), new JLabel() };

        /* Add labels to labelList */
        labelList = new ArrayList<JLabel>();
        labelList.add(statusLabel);
        for (int i : new int [] {RED, BLUE} )
        {
            labelList.add(nameLabel[i]);
            labelList.add(sideLabel[i]);
            labelList.add(clockLabel[i]);
        }

        /* Set alignment of text in labels */
        statusLabel.setHorizontalAlignment(JLabel.TRAILING);
        statusLabel.setVerticalAlignment(JLabel.TOP);

        sideLabel[RED].setHorizontalAlignment(JLabel.LEADING);
        sideLabel[RED].setVerticalAlignment(JLabel.TOP);
        sideLabel[BLUE].setHorizontalAlignment(JLabel.LEADING);
        sideLabel[BLUE].setVerticalAlignment(JLabel.BOTTOM);

        nameLabel[RED].setHorizontalAlignment(JLabel.CENTER);
        nameLabel[RED].setVerticalAlignment(JLabel.TOP);
        nameLabel[BLUE].setHorizontalAlignment(JLabel.CENTER);
        nameLabel[BLUE].setVerticalAlignment(JLabel.BOTTOM);

        clockLabel[RED].setHorizontalAlignment(JLabel.TRAILING);
        clockLabel[RED].setVerticalAlignment(JLabel.TOP);
        clockLabel[BLUE].setHorizontalAlignment(JLabel.TRAILING);
        clockLabel[BLUE].setVerticalAlignment(JLabel.BOTTOM);

        /* Set text color of labels to white */
        for (JLabel label : labelList)
            label.setForeground(Color.WHITE);

        /* Set all labels to the following font */
        Font font = new Font("monospaced", Font.PLAIN, 22);
        for (JLabel label : labelList)
            label.setFont(font);

        /* The main course of the day: create the CheckersBoard component */
        cbm = new checkers.ui.CheckersBoardModel(model.getBoardState());
        CheckersBoard cb = new CheckersBoard(cbm);

        /* Add all components to the content pane */
        pane.add(cb);
        for (JLabel label : labelList)
            pane.add(label);

        /* Uses spring layout for the content pane */
        SpringLayout layout = new SpringLayout();
        pane.setLayout(layout);

        /* Edge of frame is 50 px outside edge of checkers board */
        layout.putConstraint(SpringLayout.EAST, pane, 50, SpringLayout.EAST, cb);
        layout.putConstraint(SpringLayout.WEST, cb, 50, SpringLayout.WEST, pane);
        layout.putConstraint(SpringLayout.SOUTH, pane, 50, SpringLayout.SOUTH, cb);
        layout.putConstraint(SpringLayout.NORTH, cb, 50, SpringLayout.NORTH, pane);


        /* Left align sideLabels, place above/below cb */
        layout.putConstraint(SpringLayout.SOUTH, sideLabel[BLUE], 0,
                             SpringLayout.NORTH, cb);
        layout.putConstraint(SpringLayout.WEST, sideLabel[BLUE], 0,
                             SpringLayout.WEST, cb);

        layout.putConstraint(SpringLayout.NORTH, sideLabel[RED], 0, 
                             SpringLayout.SOUTH, cb);
        layout.putConstraint(SpringLayout.WEST, sideLabel[RED], 0, 
                             SpringLayout.WEST, cb);

        // Redefine so SE 5 compile does not fail. 
        String fieldHorizontalCenter = "HorizontalCenter";

        if (se5hack)
            System.out.println("Java SE 5 detected. Layout hacks enabled.");

        /* Center nameLabels horizontally, place above/below cb */
        /* Use layout hack of se5hack == true */
        layout.putConstraint(SpringLayout.SOUTH, nameLabel[BLUE], 0,
                             SpringLayout.NORTH, cb);
        //layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabel[BLUE], 0,
                             //SpringLayout.HORIZONTAL_CENTER, cb);
        
        if (se5hack)
            layout.putConstraint(SpringLayout.EAST, nameLabel[BLUE], -100,
                                 SpringLayout.EAST, cb);
        else
            layout.putConstraint(fieldHorizontalCenter, nameLabel[BLUE], 0,
                                 fieldHorizontalCenter, cb);

        layout.putConstraint(SpringLayout.NORTH, nameLabel[RED], 0, 
                             SpringLayout.SOUTH, cb);
        //layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, nameLabel[RED], 0, 
                             //SpringLayout.HORIZONTAL_CENTER, cb);

        if (se5hack)
            layout.putConstraint(SpringLayout.EAST, nameLabel[RED], -100, 
                                 SpringLayout.EAST, cb);
        else
            layout.putConstraint(fieldHorizontalCenter, nameLabel[RED], 0, 
                                 fieldHorizontalCenter, cb);

        /* Right align clockLabels, place above/below cb */
        layout.putConstraint(SpringLayout.SOUTH, clockLabel[BLUE], 0,
                             SpringLayout.NORTH, cb);
        layout.putConstraint(SpringLayout.EAST, clockLabel[BLUE], 0,
                             SpringLayout.EAST, cb);

        layout.putConstraint(SpringLayout.NORTH, clockLabel[RED], 0, 
                             SpringLayout.SOUTH, cb);
        layout.putConstraint(SpringLayout.EAST, clockLabel[RED], 0, 
                             SpringLayout.EAST, cb);

        /* Place statusLabel at top right corner of frame */
        layout.putConstraint(SpringLayout.NORTH, statusLabel, 0,
                             SpringLayout.NORTH, pane);
        layout.putConstraint(SpringLayout.EAST, statusLabel, 0,
                             SpringLayout.EAST, pane);


        /* Listen for changes in the checkers game */
        model.addChangeListener(this);

        /* Redirect mouse clicks from the CheckersBoard directly to the controller */
        cbm.addActionListener(ctl);

        /* Periodically update labels */
        updateLabelTimer = new javax.swing.Timer(100, this);
        updateLabelTimer.start();

        /* Dispose this frame if user closes this frame
         * (by default, this frame is only hidden) */
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Automatically end the checkers game if user closes this frame */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                updateLabelTimer.stop();
                CheckersUI.this.ctl.terminateGame("UI closed");
            }
        });

        /* Automatically terminate the game on SIGTERM */
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() { CheckersUI.this.ctl.terminateGame("shutdown"); }
        });

        /* Make visible */
        update();
        pack();
    }

    protected void update()
    {
        /* Update board state control contents with game model */
        cbm.setBoardState(model.getBoardState());

        /* Update name, side labels */
        for (int i = 0; i < 2; i++)
            sideLabel[i].setText(Utility.reportSide(i));

        for (int i = 0; i < 2; i++)
            nameLabel[i].setText(model.getPlayer(i).getName());

        /* Update statusLabel */
        String statusText;
        switch (model.getState())
        {
            case READY:
                statusText = "Ready for " + Utility.reportSide(model.getSide());
                break;
            case WAITING:
                statusText = "Waiting for " + Utility.reportSide(model.getSide());
                break;
            case FINISHED:
                int winner = model.getWinner();
                if (winner == NEITHER)
                    statusText = "DRAW";
                else
                    statusText = Utility.reportSide(model.getWinner()) + " wins!";
                break;
            case INVALID:
                statusText = "Invalid game";
                break;
            default:
                statusText = "Unknown status";
                break;
        }
        statusLabel.setText(statusText);

        /* update clockLabel */
        for (int i = 0; i < 2; i++)
        {
            long turn_time_remain = model.getClock().getTurnTime(i);
            long quot = turn_time_remain / 1000;
            long rem = turn_time_remain % 1000;

            String timeText = String.format("%02d:%02d", quot/60, quot%60);
            clockLabel[i].setText(timeText);
        }
    }
    
    public void stateChanged(ChangeEvent e)
    {
        update();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateLabelTimer)
            /* Timer ticked; update clockLabel */
            update();
    }

    public checkers.ui.CheckersBoardModel getCheckersBoardWidget() { return cbm; }

    public CheckersModel getModel() { return model; }

    public CheckersController getController() { return ctl; }

    public static CheckersUI launch(CheckersModel cm, CheckersController ctl)
    {
        final CheckersUI gui = new CheckersUI(cm, (CheckersUIController)ctl);
        gui.setLocation(160, 120);

        Runnable run_show = new Runnable()
        {
            public void run()
            {
                gui.setVisible(true);
            }
        };

        try {
            SwingUtilities.invokeAndWait(run_show);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return gui;
    }
}
