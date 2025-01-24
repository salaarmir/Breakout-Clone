package Objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Ball class to initiate the ball used in the game
 *
 * @author Salaar Mir-modified
 */
abstract public class Ball {

    /**
     * Actual face of the ball
     */
    private Shape m_ballFace;

    /**
     * Returns the shape of the ball face
     *
     * @return the shape of the ball face
     */
    public Shape getBallFace() {
        return m_ballFace;
    }

    /**
     * Coordinates for the center of the ball
     */
    private Point2D m_center;

    /**
     * Returns coordinates for the center of the ball
     *
     * @return coordinates for the center of the ball
     */
    public Point2D getPosition() {
        return m_center;
    }

    /**
     * Sets new center for the ball
     *
     * @param newCenter Coordinates for the new ball center
     */
    public void setCenter(Point2D newCenter) {
        m_center = newCenter;
    }

    /**
     * Coordinates for the upper part of the ball
     */
    private final Point2D UP;

    /**
     * Returns the coordinates' for the upper part of the ball
     *
     * @return the coordinates' for the upper part of the ball
     */
    public Point2D getUP() {
        return UP;
    }

    /**
     * Coordinates for the lower part of the ball
     */
    private final Point2D DOWN;

    /**
     * Returns the coordinates' for the lower part of the ball
     *
     * @return the coordinates' for the left part of the ball
     */
    public Point2D getDOWN() {
        return DOWN;
    }

    /**
     * Coordinates for the left part of the ball
     */
    private final Point2D LEFT;

    /**
     * Returns the coordinates' for the left part of the ball
     *
     * @return the coordinates' for the left part of the ball
     */
    public Point2D getLEFT() {
        return LEFT;
    }

    /**
     * Coordinates for the right part of the ball
     */
    private final Point2D RIGHT;

    /**
     * Returns the coordinates' for the right part of the ball
     *
     * @return the coordinates' for the right part of the ball
     */
    public Point2D getRIGHT() {
        return RIGHT;
    }

    /**
     * Outside border color of the ball
     */
    private final Color BORDER;

    /**
     * Returns the border color of the ball
     *
     * @return the border color of the ball
     */
    public Color getBorderColor() {
        return BORDER;
    }

    /**
     * Inner color of the ball
     */
    private final Color INNER;

    /**
     * Returns the inner color of the ball
     *
     * @return the inner color of the ball
     */
    public Color getInnerColor() {
        return INNER;
    }

    /**
     * Horizontal speed at which the ball moves
     */
    private int m_speedX;

    /**
     * Returns the horizontal speed of the ball
     *
     * @return the horizontal speed of the ball
     */
    public int getSpeedX() {
        return m_speedX;
    }

    /**
     * Sets the horizontal speed of the ball
     *
     * @param s New horizontal speed of the ball
     */
    public void setXSpeed(int s) {
        m_speedX = s;
    }

    /**
     * Vertical speed at which the ball moves
     */
    private int m_speedY;

    /**
     * Returns the vertical speed of the ball
     *
     * @return the vertical speed of the ball
     */
    public int getSpeedY() {
        return m_speedY;
    }

    /**
     * Sets the vertical speed of the ball
     *
     * @param s New vertical speed of the ball
     */
    public void setYSpeed(int s) {
        m_speedY = s;
    }

    /**
     * Reverses the direction of the ball
     */
    private final int REVERSEDIRECTION = -1;

    /**
     * Creates new ball object with specified center coordinates, radius, inner and border color
     *
     * @param center  Coordinates for the center of the ball
     * @param radiusA Horizontal radius of the ball
     * @param radiusB Vertical radius of the ball
     * @param inner   Inner color of the ball
     * @param border  Border color of the ball
     */
    public Ball(Point2D center, float radiusA, float radiusB, Color inner, Color border) {
        this.setCenter(center);

        int radiusBDivide = 2;
        int radiusADivide = 2;

        final int INITIALSPEED = 0;

        UP = new Point2D.Double();
        DOWN = new Point2D.Double();
        LEFT = new Point2D.Double();
        RIGHT = new Point2D.Double();

        UP.setLocation(center.getX(), center.getY() - (radiusB / radiusBDivide));
        DOWN.setLocation(center.getX(), center.getY() + (radiusB / radiusBDivide));

        LEFT.setLocation(center.getX() - (radiusA / radiusADivide), center.getY());
        RIGHT.setLocation(center.getX() + (radiusA / radiusADivide), center.getY());


        m_ballFace = makeBall(center, radiusA, radiusB);
        this.BORDER = border;
        this.INNER = inner;

        m_speedX = INITIALSPEED;
        m_speedY = INITIALSPEED;
    }

    /**
     * Creates and returns the shape of the ball
     *
     * @param center  Coordinates for the center of the ball
     * @param radiusA Horizontal radius of the ball
     * @param radiusB Vertical radius of the ball
     * @return the shape of the ball
     */
    protected abstract Shape makeBall(Point2D center, float radiusA, float radiusB);

    /**
     * Moves the ball in game
     */
    public void move() {

        int heightDivider = 2;
        int widthDivider = 2;
        RectangularShape tmp = (RectangularShape) m_ballFace;
        m_center.setLocation((m_center.getX() + m_speedX), (m_center.getY() + m_speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((m_center.getX() - (w / widthDivider)), (m_center.getY() - (h / heightDivider)), w, h);
        setPoints(w, h);


        m_ballFace = tmp;
    }

    /**
     * Set the horizontal and vertical speed of the ball
     *
     * @param x The new horizontal speed of the ball
     * @param y The new vertical speed of the ball
     */
    public void setSpeed(int x, int y) {
        m_speedX = x;
        m_speedY = y;
    }

    /**
     * Reverses the horizontal speed of the ball
     */
    public void reverseX() {
        m_speedX *= REVERSEDIRECTION;
    }

    /**
     * Reverses the vertical speed of the ball
     */
    public void reverseY() {
        m_speedY *= REVERSEDIRECTION;
    }

    /**
     * Moves the ball to a specified new point
     *
     * @param p New point coordinates the ball should be moved to
     */
    public void moveTo(Point p) {
        m_center.setLocation(p);

        RectangularShape tmp = (RectangularShape) m_ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((m_center.getX() - (w / 2)), (m_center.getY() - (h / 2)), w, h);
        m_ballFace = tmp;
    }

    /**
     * Change the lengths of the vertical and horizontal radii of the ball
     *
     * @param width  New length of the horizontal radius
     * @param height New length of the vertical radius
     */
    public void setPoints(double width, double height) {

        int heightDivider = 2;
        int widthDivider = 2;

        UP.setLocation(m_center.getX(), m_center.getY() - (height / heightDivider));
        DOWN.setLocation(m_center.getX(), m_center.getY() + (height / heightDivider));

        LEFT.setLocation(m_center.getX() - (width / widthDivider), m_center.getY());
        RIGHT.setLocation(m_center.getX() + (width / widthDivider), m_center.getY());
    }

}

