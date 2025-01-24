package View;

import Initialization.GameBoard;
import Objects.Ball;
import Objects.Brick;
import Objects.Paddle;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;


/**
 * Class used to display the actual graphics of the game to the user
 *
 * @author Salaar Mir
 */
public class GUI extends JComponent {

    /**
     * Only instance of the GUI class to display the game visuals to the user
     */
    private static final GUI GRAPHICS = new GUI();

    /**
     * Returns the only instance of the GUI class
     *
     * @return the only instance of the GUI class
     */
    public static GUI getGUI() {
        return GRAPHICS;
    }

    /**
     * 'Continue' string to display to the user, so they can continue the game
     */
    private static final String CONTINUE = "Continue";

    /**
     * 'Restart' string to display to the user, so they can restart the game
     */
    private static final String RESTART = "Restart";

    /**
     * 'Exit' string to display to the user, so they can exit the game
     */
    private static final String EXIT = "Exit";

    /**
     * 'Pause Menu' string to display to the user, so they can open the pause menu
     */
    private static final String PAUSE = "Pause Menu";

    /**
     * Background color of the game
     */
    private static final Color BG_COLOR = Color.WHITE;

    /**
     * The only instance of the paddle object controlled by the user
     */
    private final Paddle PADDLE = Paddle.getPaddle();

    /**
     * Clears the screen of the game by setting the color to the background color
     *
     * @param g2d Encapsulates information that is required to render the game visuals
     */
    public void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Draws a brick to the screen
     *
     * @param brick Brick to draw
     * @param g2d   Encapsulates information that is required to render the brick
     */
    public void drawBrick(Brick brick, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Draws a ball to the screen
     *
     * @param ball Ball to draw
     * @param g2d  Encapsulates information that is required to render the ball
     */
    public void drawBall(Ball ball, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draws a PADDLE to the screen for the user to control
     *
     * @param p   Paddle to draw
     * @param g2d Encapsulates information that is required to render the PADDLE
     */
    public void drawPlayer(Paddle p, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = p.getPaddleFace();
        g2d.setColor(PADDLE.getInnerColor());
        g2d.fill(s);

        g2d.setColor(PADDLE.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Displays the pause menu onto the screen
     *
     * @param g2d Encapsulates information that is required to render the pause menu
     */
    public void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Conceals the game board from the user
     *
     * @param g2d Encapsulates information that is required to conceal the game board
     */
    public void obscureGameBoard(Graphics2D g2d) {

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, GameBoard.getGameBoard().getDefWidth(), GameBoard.getGameBoard().getDefHeight());

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draws the different components of the pause menu for user to be able to interact with
     *
     * @param g2d Encapsulates information that is required to draw the various features of the pause menu
     */
    public void drawPauseMenu(Graphics2D g2d) {
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(GameBoard.getGameBoard().getMenuFont());
        g2d.setColor(GameBoard.getGameBoard().getMenuColor());

        if (GameBoard.getGameBoard().getStrLen() == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            GameBoard.getGameBoard().setStrLen(GameBoard.getGameBoard().getMenuFont().getStringBounds(PAUSE, frc).getBounds().width);
        }

        int x = (GameBoard.getGameBoard().getWidth() - GameBoard.getGameBoard().getStrLen()) / 2;
        int y = GameBoard.getGameBoard().getHeight() / 10;

        g2d.drawString(PAUSE, x, y);

        x = GameBoard.getGameBoard().getWidth() / 8;
        y = GameBoard.getGameBoard().getHeight() / 4;


        if (GameBoard.getGameBoard().getContinueButtonRect() == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            GameBoard.getGameBoard().setContinueButtonRect(GameBoard.getGameBoard().getMenuFont().getStringBounds(CONTINUE, frc).getBounds());
            GameBoard.getGameBoard().getContinueButtonRect().setLocation(x, y - GameBoard.getGameBoard().getContinueButtonRect().height);
        }

        g2d.drawString(CONTINUE, x, y);

        y *= 2;

        if (GameBoard.getGameBoard().getRestartButtonRect() == null) {
            GameBoard.getGameBoard().setRestartButtonRect((Rectangle) GameBoard.getGameBoard().getContinueButtonRect().clone());
            GameBoard.getGameBoard().getRestartButtonRect().setLocation(x, y - GameBoard.getGameBoard().getRestartButtonRect().height);
        }

        g2d.drawString(RESTART, x, y);

        y *= 3.0 / 2;

        if (GameBoard.getGameBoard().getExitButtonRect() == null) {
            GameBoard.getGameBoard().setExitButtonRect((Rectangle) GameBoard.getGameBoard().getContinueButtonRect().clone());
            GameBoard.getGameBoard().getExitButtonRect().setLocation(x, y - GameBoard.getGameBoard().getExitButtonRect().height);
        }

        g2d.drawString(EXIT, x, y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }
}
