package Initialization;

import Controller.Controls;
import Debug.DebugConsole;
import Extensions.EndGame;
import Extensions.LoseGame;
import Objects.Brick;
import Objects.Paddle;
import Objects.Wall;
import View.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Class to initiate the game board to display all the game objects to the user
 *
 * @author Salaar Mir-modifed
 */
public class GameBoard extends JComponent {

    /**
     * Color of text on pause menu
     */
    private final Color MENU_COLOR = new Color(0, 255, 0);

    /**
     * Returns color of text used for pause menu
     *
     * @return color of text used for pause menu
     */
    public Color getMenuColor() {
        return MENU_COLOR;
    }

    /**
     * Width of game board
     */
    private final int DEF_WIDTH = 600;

    /**
     * Returns width of the game board
     *
     * @return width of the game board
     */
    public int getDefWidth() {
        return DEF_WIDTH;
    }

    /**
     * Height of game board
     */
    private final int DEF_HEIGHT = 450;

    /**
     * Returns height of the game board
     *
     * @return height of the game board
     */
    public int getDefHeight() {
        return DEF_HEIGHT;
    }

    /**
     * Timer for initial and between event delay of the game
     */
    private Timer m_gameTimer;

    /**
     * Returns the timer of the game
     *
     * @return the timer of the game
     */
    public Timer getGameTimer() {
        return m_gameTimer;
    }

    /**
     * Wall of bricks that need to be broken
     */
    private static Wall m_wall;


    /**
     * Messages displayed throughout the game
     */
    private String m_message;

    /**
     * Sets the various messages that should be displayed throughout the game
     *
     * @param message the message to display
     */
    public void setMessage(String message) {
        this.m_message = message;
    }

    /**
     * Boolean to show or hide the pause menu
     */
    private boolean m_showPauseMenu;

    /**
     * Returns boolean to notify action listener whether the pause menu should be displayed or hidden
     *
     * @return True or false depending on whether pause menu should be shown or not
     */
    public boolean isShowPauseMenu() {
        return m_showPauseMenu;
    }

    /**
     * Set boolean to true or false depending on if pause menu needs to be shown or hiddean
     *
     * @param showPauseMenu True if pause menu should be shown or false if it should be hidden
     */
    public void setShowPauseMenu(boolean showPauseMenu) {
        this.m_showPauseMenu = showPauseMenu;
    }

    /**
     * Font for text on tbe pause menu
     */
    private final Font MENU_FONT;

    /**
     * Returns font of text used for pause menu
     *
     * @return font of text used for pause menu
     */
    public Font getMenuFont() {
        return MENU_FONT;
    }

    /**
     * Area of space to click 'Continue' button
     */
    private Rectangle m_continueButtonRect;

    /**
     * Returns coordinate bounds for area of the 'Continue' button
     *
     * @return coordinate bounds for area of 'Continue' button
     */
    public Rectangle getContinueButtonRect() {
        return m_continueButtonRect;
    }

    /**
     * Set coordinate bounds for area of the 'Continue' button
     *
     * @param continueButtonRect coordinate bounds for area of the 'Continue' button
     */
    public void setContinueButtonRect(Rectangle continueButtonRect) {
        this.m_continueButtonRect = continueButtonRect;
    }

    /**
     * Area of space to click 'Exit' button
     */
    private Rectangle m_exitButtonRect;

    /**
     * Returns coordinate bounds for area of the 'Exit' button
     *
     * @return coordinate bounds for area of 'Exit' button
     */
    public Rectangle getExitButtonRect() {
        return m_exitButtonRect;
    }

    /**
     * Set coordinate bounds for area of the 'Exit' button
     *
     * @param exitButtonRect coordinate bounds for area of the 'Exit' button
     */
    public void setExitButtonRect(Rectangle exitButtonRect) {
        this.m_exitButtonRect = exitButtonRect;
    }

    /**
     * Area of space to click 'Restart' button
     */
    private Rectangle m_restartButtonRect;

    /**
     * Returns coordinate bounds for area of the 'Restart' button
     *
     * @return coordinate bounds for area of 'Restart' button
     */
    public Rectangle getRestartButtonRect() {
        return m_restartButtonRect;
    }

    /**
     * Set coordinate bounds for area of the 'Restart' button
     *
     * @param restartButtonRect coordinate bounds for area of the 'Restart' button
     */
    public void setRestartButtonRect(Rectangle restartButtonRect) {
        this.m_restartButtonRect = restartButtonRect;
    }

    /**
     * Assign string lengths for pause menu
     */
    private int m_strLen;

    /**
     * Returns length of string for message to be displayed
     *
     * @return length of string for message to be displayed
     */
    public int getStrLen() {
        return m_strLen;
    }

    /**
     * Set length of string for message to be displayed
     *
     * @param strLen Length of the string
     */
    public void setStrLen(int strLen) {
        this.m_strLen = strLen;
    }

    /**
     * the DebugConsole object
     */
    private final DebugConsole m_debugConsole;

    /**
     * Returns the debug console
     *
     * @return the debug console
     */
    public DebugConsole getDebugConsole() {
        return m_debugConsole;
    }

    /**
     * Object to setup the controls for the game
     */
    private final Controls CONTROLS = Controls.getControls();

    /**
     * Object to display the visual graphics to the user
     */
    private final GUI GRAPHICS = GUI.getGUI();

    /**
     * Object to show the user their score
     */
    private final LoseGame SCORE = LoseGame.getScore();

    /**
     * Board used to display the game objects on
     */
    private static final GameBoard GAME_BOARD = new GameBoard();

    /**
     * Returns only instance of game board object
     *
     * @return only instance of game board object
     */
    public static GameBoard getGameBoard() {
        return GAME_BOARD;
    }

    /**
     * Creates the game board and initialize all objects that will be displayed
     */
    private GameBoard() {
        super();

        m_strLen = 0;
        setShowPauseMenu(false);
        int extraPoints = 100;
        int textSize = 30;
        MENU_FONT = new Font("Monospaced", Font.PLAIN, textSize);

        SCORE.screenHide();
        this.initialize();
        this.setMessage("Press SPACE to start");
        m_wall = Wall.getWall();
        m_debugConsole = new DebugConsole(m_wall, this);
        m_wall.nextLevel();

        int TIMER_DELAY = 10;
        m_gameTimer = new Timer(TIMER_DELAY, e -> {
            m_wall.move();
            m_wall.findImpacts();
            this.setMessage(String.format("Bricks: %d Balls %d", m_wall.getBrickCount(), m_wall.getBallCount()));
            if (m_wall.isBallLost()) {
                if (m_wall.ballEnd()) {
                    m_wall.wallReset();
                    this.setMessage("Game over");
                    SCORE.initAndShowMenu();
                }
                m_wall.ballReset();
                m_gameTimer.stop();
            } else if (m_wall.isDone()) {
                if (m_wall.hasLevel()) {
                    this.setMessage("Go to Next Level -> +100 Points!!");
                    Brick.setScore(Brick.getScore() + extraPoints);

                    m_gameTimer.stop();
                    m_wall.ballReset();
                    m_wall.wallReset();
                    m_wall.nextLevel();
                } else {
                    Brick.setScore(Brick.getScore() + extraPoints);
                    Brick.setScore(Brick.getScore() + (m_wall.getBallCount() * extraPoints));
                    EndGame.getScore().displayMenu();
                    m_gameTimer.stop();
                }
            }

            repaint();
        });

    }

    /**
     * Set the size of the game board and add listeners for the different controls of the game
     */
    public void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(CONTROLS);
        this.addMouseListener(CONTROLS);
        this.addMouseMotionListener(CONTROLS);
    }

    /**
     * Paint the different objects that will be displayed
     *
     * @param g Encapsulates information that is required to render the different game objects
     */
    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        GRAPHICS.clear(g2d);

        g2d.setColor(Color.BLUE);

        int STRINGXCOORD;
        int STRINGYCOORD = 225;

        if (Objects.equals(m_message, "Go to Next Level -> +100 Points!!"))
            STRINGXCOORD = 200;
        else
            STRINGXCOORD = 250;


        g2d.drawString(m_message, STRINGXCOORD, STRINGYCOORD);

        GRAPHICS.drawBall(m_wall.getBall(), g2d);

        for (Brick b : m_wall.getBricks())
            if (!b.isBroken())
                GRAPHICS.drawBrick(b, g2d);

        GRAPHICS.drawPlayer(Paddle.getPaddle(), g2d);

        if (m_showPauseMenu)
            GRAPHICS.drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }


}
