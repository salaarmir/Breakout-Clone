package Initialization;

import Controller.Controls;
import Extensions.ColorMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * Class to initiate the game frame to add the game board onto
 *
 * @author Salaar Mir-modified
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    /**
     * Boolean to indicate if user is playing the game or not
     */
    private boolean m_gaming;

    /**
     * Frame to display the game board on
     */
    private static final GameFrame GAME_FRAME = new GameFrame();

    /**
     * Returns the only instance of the frame to display the game board on
     *
     * @return the only instance of the frame to display the game board on
     */
    public static GameFrame getGameFrame() {
        return GAME_FRAME;
    }

    /**
     * ColorMode object to let users choose between light and dark mode
     */
    private final ColorMode COLOR_MODE = ColorMode.getColorMode();

    /**
     * game frame constructor creates a game board object to display the game components
     */
    private GameFrame() {
        super();
        m_gaming = false;
        this.setLayout(new BorderLayout());

        GameBoard gameBoard = GameBoard.getGameBoard();
        this.add(gameBoard, BorderLayout.CENTER);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Initialise the game frame
     */
    public void initialize() {

        String frameTitle = "space = start/pause   ←/→ = move left/right   esc = menu   1 Brick = 10 Points";
        this.setTitle(frameTitle);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        if (COLOR_MODE.getDarkMode()) {
            this.getContentPane().setBackground(Color.BLACK);
        } else
            this.getContentPane().setBackground(Color.WHITE);
    }

    /**
     * Set location of the game frame object
     */
    public void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        int XCOORDDIVIDER = 2;
        int x = (size.width - this.getWidth()) / XCOORDDIVIDER;

        int YCOORDDIVIDER = 2;
        int y = (size.height - this.getHeight()) / YCOORDDIVIDER;
        this.setLocation(x, y);
    }

    /**
     * Causes game board window to gain focus to allow user to play
     *
     * @param windowEvent Event to indicate the change in state of the window
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        // the first time the frame loses focus is because it has been disposed to install the GameBoard, so went it regains the focus it's ready to play. of course calling a method such as 'onLostFocus' is useful only if the GameBoard as been displayed at least once
        m_gaming = true;
    }

    /**
     * Causes game board window to lose focus
     *
     * @param windowEvent Event to indicate the change in state of the window
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (m_gaming)
            Controls.getControls().onLostFocus();

    }


}
