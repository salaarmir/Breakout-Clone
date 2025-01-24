package Debug;

import Initialization.GameBoard;
import Initialization.GameFrame;
import Objects.Ball;
import Objects.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Class to allow for easy code debugging through the ability to skip levels and reset ball count
 *
 * @author Salaar Mir-modified
 */
public class DebugConsole extends JDialog implements WindowListener {

    /**
     * Title of the DebugConsole
     */
    private static final String TITLE = "Debug Console";

    /**
     * DebugPanel to allow users to skip levels and reset the ball count
     */
    private final DebugPanel debugPanel;

    /**
     * Gameboard to play the game on
     */
    private final GameBoard gameBoard;

    /**
     * Wall of bricks to be drawn onto the game board
     */
    private final Wall wall;

    /**
     * Creates the DebugConsole
     *
     * @param wall      Wall to assign the console to
     * @param gameBoard Game board to assign the console to
     */
    public DebugConsole(Wall wall, GameBoard gameBoard) {

        this.wall = wall;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel, BorderLayout.CENTER);


        this.pack();
    }

    /**
     * Initialises and displays the DebugConsole for the user to use
     */
    public void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * Sets location of the DebugConsole
     */
    public void setLocation() {
        int x = ((GameFrame.getGameFrame().getWidth() - this.getWidth()) / 2) + GameFrame.getGameFrame().getX();
        int y = ((GameFrame.getGameFrame().getHeight() - this.getHeight()) / 2) + GameFrame.getGameFrame().getY();
        this.setLocation(x, y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Repaints the game board when the frame window is closing
     *
     * @param windowEvent The event of the window closing
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * Sets location of the DebugConsole and sets the values of the DebugPanel to the horizontal and vertical of the ball
     *
     * @param windowEvent The event of the window being activated
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.getBall();
        debugPanel.setValues(b.getSpeedX(), b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
