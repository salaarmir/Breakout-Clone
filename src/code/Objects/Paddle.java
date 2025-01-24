package Objects;

import java.awt.*;

/**
 * Class to create the paddle that the user can control
 *
 * @author Salaar Mir-modified
 */
public class Paddle {

    /**
     * Border color of the paddle
     */
    private static final Color BORDER_COLOR = Color.GREEN.darker().darker();

    /**
     * Returns the border color of the paddle
     *
     * @return the border color of the paddle
     */
    public Color getBorderColor() {
        return BORDER_COLOR;
    }

    /**
     * Inner color of the paddle
     */
    private static final Color INNER_COLOR = Color.GREEN;

    /**
     * Returns the inner color of the paddle
     *
     * @return the inner color of the paddle
     */
    public Color getInnerColor() {
        return INNER_COLOR;
    }

    /**
     * Constant amount the paddle can move in each direction
     */
    private static final int DEF_MOVE_AMOUNT = 5;

    /**
     * Width of the paddle
     */
    private static final int DEF_WIDTH = 600;

    /**
     * Height of the paddle
     */
    private static final int DEF_HEIGHT = 450;

    /**
     * Face of the paddle
     */
    private final Rectangle PADDLEFACE;

    /**
     * Returns the shape of the paddle face
     *
     * @return the shape of the paddle face
     */
    public Shape getPaddleFace() {
        return PADDLEFACE;
    }

    /**
     * Coordinates where the ball is located
     */
    private Point m_ballPoint;

    /**
     * Set new coordinates for the ball location
     *
     * @param newBallPoint New coordinates for the ball location
     */
    public void setBallPoint(Point newBallPoint) {
        m_ballPoint = newBallPoint;
    }

    /**
     * Changeable variable to adjust the paddle movement
     */
    private int m_moveAmount;

    /**
     * Minimum length the paddle can be moved to
     */
    private final int MIN;

    /**
     * Maximum amount the paddle can be moved to
     */
    private final int MAX;

    /**
     * Only instance of the paddle class to be used in the entire project
     */
    private static final Paddle PADDLE = new Paddle(new Point(300, 430), 150, 10, new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT));

    /**
     * Returns the only instance of the paddle object
     *
     * @return the only instance of the paddle object
     */
    public static Paddle getPaddle() {
        return PADDLE;
    }

    /**
     * Creates paddle object for player to be able to move
     *
     * @param ballPoint Coordinates of the ball location
     * @param width     Width of the paddle
     * @param height    Height of the paddle
     * @param container Rectangular space that will contain the paddle
     */
    private Paddle(Point ballPoint, int width, int height, Rectangle container) {
        this.setBallPoint(ballPoint);
        m_moveAmount = 0;
        PADDLEFACE = makeRectangle(width, height);
        MIN = container.x + (width / 2);
        MAX = MIN + container.width - width;
    }

    /**
     * Creates and returns the rectangle the paddle will be contained in
     *
     * @param width  Width of the rectangle
     * @param height Height of the rectangle
     * @return The Rectangle shape paddle will be contained in
     */
    public Rectangle makeRectangle(int width, int height) {
        Point p = new Point((int) (m_ballPoint.getX() - (width / 2)), (int) m_ballPoint.getY());
        return new Rectangle(p, new Dimension(width, height));
    }

    /**
     * Ball makes impact on the paddle
     *
     * @param b The ball that made the impact
     * @return True if the ball made contact with the paddle or false if it did not make contact
     */
    public boolean impact(Ball b) {
        return PADDLEFACE.contains(b.getPosition()) && PADDLEFACE.contains(b.getDOWN());
    }

    /**
     * Moves paddle horizontally left or right based on users input
     */
    public void move() {

        int paddleWithDivider = 2;
        double x = m_ballPoint.getX() + m_moveAmount;
        if (x < MIN || x > MAX)
            return;
        m_ballPoint.setLocation(x, m_ballPoint.getY());
        PADDLEFACE.setLocation(m_ballPoint.x - (int) PADDLEFACE.getWidth() / paddleWithDivider, m_ballPoint.y);
    }

    /**
     * Moves the paddle left
     */
    public void moveLeft() {
        m_moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Moves the paddle right
     */
    public void movRight() {
        m_moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Stops paddle movement
     */
    public void stop() {
        m_moveAmount = 0;
    }

    /**
     * Moves the paddle to specified new coordinates
     *
     * @param p The new coordinates the paddle should be moved to
     */
    public void moveTo(Point p) {
        m_ballPoint.setLocation(p);
        PADDLEFACE.setLocation(m_ballPoint.x - (int) PADDLEFACE.getWidth() / 2, m_ballPoint.y);
    }


}
