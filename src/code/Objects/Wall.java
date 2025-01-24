package Objects;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Class to create the wall of bricks that will need to be destroyed in each level
 *
 * @author Salaar Mir-modified
 */
public class Wall {

    /**
     * Clay brick type
     */
    private static final int CLAY = 1;

    /**
     * Steel brick type
     */
    private static final int STEEL = 2;

    /**
     * Cement brick type
     */
    private static final int CEMENT = 3;

    /**
     * Gold brick type
     */
    private static final int GOLD = 4;

    /**
     * Width of the board the user will play on
     */
    private static final int DEF_WIDTH = 600;

    /**
     * Height of the board the user will play on
     */
    private static final int DEF_HEIGHT = 450;

    /**
     * Random speed of the ball to be set
     */
    private final Random RND;

    /**
     * Area of the board the user will play on
     */
    private Rectangle m_area;

    /**
     * Set new area of the board for the game to be played on
     *
     * @param newArea New rectangle area of the board
     */
    public void setArea(Rectangle newArea) {
        this.m_area = newArea;
    }

    /**
     * Array of bricks to be drawn
     */
    private Brick[] m_bricks;

    /**
     * Returns the array of bricks to be drawn
     *
     * @return the array of bricks to be drawn
     */
    public Brick[] getBricks() {
        return this.m_bricks;
    }

    /**
     * The ball that is being used
     */
    private Ball m_ball;

    /**
     * Returns the ball being used
     *
     * @return the ball being used
     */
    public Ball getBall() {
        return m_ball;
    }

    /**
     * The paddle being used by the player
     */
    private final Paddle PLAYER;

    /**
     * Array of bricks in each of the levels the user will play
     */
    private final Brick[][] LEVELS;

    /**
     * Current level of the game the user is on
     */
    private int m_level;

    /**
     * Start point of the paddle and game
     */
    private final Point STARTPOINT;

    /**
     * Number of bricks that still need to be broken
     */
    private int m_brickCount;

    /**
     * Returns the number of bricks that still need to be broken
     *
     * @return the number of bricks that still need to be broken
     */
    public int getBrickCount() {
        return m_brickCount;
    }

    /**
     * The current number of balls the user has left
     */
    private int m_ballCount;

    /**
     * Returns the current number of balls the user has left
     *
     * @return the current number of balls the user has left
     */
    public int getBallCount() {
        return m_ballCount;
    }

    /**
     * True or false depending on if the ball has been lost or not
     */
    private boolean m_ballLost;

    /**
     * Returns true if the ball has been lost or false otherwise
     *
     * @return true if the ball has been lost or false otherwise
     */
    public boolean isBallLost() {
        return m_ballLost;
    }

    /**
     * Only instance of the wall object to be created when the game starts
     */
    private static final Wall WALL = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 6.0 / 2, new Point(300, 430));

    /**
     * Returns single instance of Wall
     *
     * @return single instance of Wall object
     */
    public static Wall getWall() {
        return WALL;
    }

    /**
     * Creates the wall object to be displayed in the game
     *
     * @param drawArea            The area of the board for the game to be played on
     * @param brickCount          The number of bricks to draw
     * @param lineCount           The number of lines of bricks to draw
     * @param brickDimensionRatio The ratio of the height of a brick to its width
     * @param ballPos             Coordinates of the position for the center of the ball
     */
    private Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        this.STARTPOINT = new Point(ballPos);

        LEVELS = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        m_level = 0;

        m_ballCount = 3;
        m_ballLost = false;

        RND = new Random();

        makeBall(ballPos);
        int speedX, speedY;
        do {
            speedX = RND.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -RND.nextInt(3);
        } while (speedY == 0);

        m_ball.setSpeed(speedX, speedY);

        PLAYER = Paddle.getPaddle();

        setArea(drawArea);


    }

    /**
     * Creates a ball object with a specified center position
     *
     * @param ballPos The specified center position where the ball should be created
     */
    public void makeBall(Point2D ballPos) {
        m_ball = new RubberBall(ballPos);
    }

    /**
     * Creates and returns a 2-dimensional array with each level of the game and the bricks to draw in that level
     *
     * @param drawArea            The area of the board for the game to be played on
     * @param brickCount          The number of bricks to draw
     * @param lineCount           The number of lines of bricks to draw
     * @param brickDimensionRatio The ratio of the height of a brick to its width
     * @return a 2-dimensional array with each level of the game and the bricks to draw in that level
     */
    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {

        int levelsCount = 6;

        int finalLevelBricks = 40;
        int finalLevelLines = 4;

        Brick[][] tmp = new Brick[levelsCount][];
        tmp[0] = Levels.makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = Levels.makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        tmp[4] = Levels.makeColumnLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT, STEEL);
        tmp[5] = Levels.makeFinalLevel(drawArea, finalLevelBricks, finalLevelLines, brickDimensionRatio, CLAY, GOLD, STEEL);
        return tmp;
    }

    /**
     * Move the game paddle and ball
     */
    public void move() {
        PLAYER.move();
        m_ball.move();
    }

    /**
     * Finds where on the game board the ball made contact
     */
    public void findImpacts() {
        if (PLAYER.impact(m_ball)) {
            m_ball.reverseY();
        } else if (impactWall()) {
            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
            m_brickCount--;
        } else if (impactBorder()) {
            m_ball.reverseX();
        } else if (m_ball.getPosition().getY() < m_area.getY()) {
            m_ball.reverseY();
        } else if (m_ball.getPosition().getY() > m_area.getY() + m_area.getHeight()) {
            m_ballCount--;
            m_ballLost = true;
        }
    }

    /**
     * Changes direction of the ball after impact with the wall and returns true if the brick that was impacted wasn't broken and false otherwise
     *
     * @return true if the brick that was impacted wasn't broken and false otherwise
     */
    public boolean impactWall() {
        for (Brick b : m_bricks) {

            //Vertical Impact
            if (b.getUpImpact() == b.findImpact(m_ball)) {
                m_ball.reverseY();
                return b.setImpact(m_ball.getDOWN(), Brick.Crack.getUp());
            } else if (b.getDownImpact() == b.findImpact(m_ball)) {
                m_ball.reverseY();
                return b.setImpact(m_ball.getUP(), Brick.Crack.getDown());
            }

            //Horizontal Impact
            else if (b.getLeftImpact() == b.findImpact(m_ball)) {
                m_ball.reverseX();
                return b.setImpact(m_ball.getRIGHT(), Brick.Crack.getRight());
            } else if (b.getRightImpact() == b.findImpact(m_ball)) {
                m_ball.reverseX();
                return b.setImpact(m_ball.getLEFT(), Brick.Crack.getLeft());
            }
        }

        return false;
    }

    /**
     * Returns true if the ball impacted the side borders or false otherwise
     *
     * @return true if the ball impacted the side borders or false otherwise
     */
    public boolean impactBorder() {
        Point2D p = m_ball.getPosition();
        return ((p.getX() < m_area.getX()) || (p.getX() > (m_area.getX() + m_area.getWidth())));
    }

    /**
     * Resets the ball and paddle back to the startpoint and resets the balls xspeed and yspeed to a random integer between 1 and 3
     */
    public void ballReset() {
        PLAYER.moveTo(STARTPOINT);
        m_ball.moveTo(STARTPOINT);
        int speedX, speedY;
        do {
            speedX = RND.nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -RND.nextInt(3);
        } while (speedY == 0);

        m_ball.setSpeed(speedX, speedY);
        m_ballLost = false;
    }

    /**
     * Resets all the bricks in the wall and sets the ball count to be 3 again
     */
    public void wallReset() {
        for (Brick b : m_bricks)
            b.repair();
        m_brickCount = m_bricks.length;
        m_ballCount = 3;
    }

    /**
     * Returns true if the ball count is 0 or false otherwise
     *
     * @return true if the ball count is 0 or false otherwise
     */
    public boolean ballEnd() {
        return m_ballCount == 0;
    }

    /**
     * Returns true if the brick count is 0 or false otherwise
     *
     * @return true if the brick count is 0 or false otherwise
     */
    public boolean isDone() {
        return m_brickCount == 0;
    }

    /**
     * Passes the brick array the series of bricks to be drawn for the next level once the current level has been completed
     */
    public void nextLevel() {
        m_bricks = LEVELS[m_level++];
        this.m_brickCount = m_bricks.length;
    }

    /**
     * Returns true if there are still levels to be played by the user and false otherwise
     *
     * @return true if there are still levels to be played by the user and false otherwise
     */
    public boolean hasLevel() {
        return m_level < LEVELS.length;
    }

    /**
     * Sets the balls horizontal speed to a newly specified integer
     *
     * @param s The new horizontal ball speed
     */
    public void setBallXSpeed(int s) {
        m_ball.setXSpeed(s);
    }

    /**
     * Sets the balls vertical speed to a newly specified integer
     *
     * @param s The new vertical ball speed
     */
    public void setBallYSpeed(int s) {
        m_ball.setYSpeed(s);
    }

    /**
     * Resets the ball count to the original number of balls
     */
    public void resetBallCount() {
        m_ballCount = 3;
    }

    /**
     * Creates and returns a new brick of a specified size and type at the point inputted
     *
     * @param point The coordinates of the point where the brick should be created
     * @param size  The size of the brick
     * @param type  The type of the brick
     * @return a new brick of a specified size and type at the point inputted
     */
    public static Brick makeBrick(Point point, Dimension size, int type) {
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case GOLD -> new GoldBrick(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d", type));
        };
    }

}
