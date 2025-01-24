package Objects;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Brick class to create the bricks used for the brick wall in the game
 *
 * @author Salaar Mir-modified
 */
abstract public class Brick {

    /**
     * Integer to signify when ball impacts upper part of a brick
     */
    private final int UP_IMPACT = 100;

    /**
     * Returns the integer to signify the ball impacted the upper part of a brick
     *
     * @return the integer to signify the ball impacted the upper part of a brick
     */
    public int getUpImpact() {
        return UP_IMPACT;
    }

    /**
     * Integer to signify when ball impacts lower part of a brick
     */
    private final int DOWN_IMPACT = 200;

    /**
     * Returns the integer to signify the ball impacted the lower part of a brick
     *
     * @return the integer to signify the ball impacted the lower part of a brick
     */
    public int getDownImpact() {
        return DOWN_IMPACT;
    }

    /**
     * Integer to signify the ball impacted the left part of a brick
     */
    private final int LEFT_IMPACT = 300;

    /**
     * Returns the integer to signify the ball impacted the left part of a brick
     *
     * @return the integer to signify the ball impacted the left part of a brick
     */
    public int getLeftImpact() {
        return LEFT_IMPACT;
    }

    /**
     * Integer to signify the ball impacted the right part of a brick
     */
    private final int RIGHT_IMPACT = 400;

    /**
     * Returns the integer to signify the ball impacted the right part of a brick
     *
     * @return the integer to signify the ball impacted the right part of a brick
     */
    public int getRightImpact() {
        return RIGHT_IMPACT;
    }

    /**
     * The score earned by the user
     */
    private static int m_score = 0;

    /**
     * Returns the score earned by the user
     *
     * @return the score earned by the user
     */
    public static int getScore() {
        return m_score;
    }

    /**
     * Updates the score earned by the user
     *
     * @param m_score Updated score
     */
    public static void setScore(int m_score) {
        Brick.m_score = m_score;
    }

    /**
     * Used to generate random number
     */
    private final Random RND;

    /**
     * Name of brick to specify its type
     */
    private final String NAME;


    /**
     * The brick face
     */
    private final Shape BRICKFACE;

    /**
     * Returns the brick face
     *
     * @return the brick face
     */
    public Shape getBRICKFACE() {
        return BRICKFACE;
    }

    /**
     * The border color of the brick
     */
    private final Color BORDER;

    /**
     * Returns the border color of the brick
     *
     * @return the border color of the brick
     */
    public Color getBorderColor() {
        return BORDER;
    }

    /**
     * The inner color of the brick
     */
    private final Color INNER;

    /**
     * Returns the inner color of the brick
     *
     * @return the inner color of the brick
     */
    public Color getInnerColor() {
        return INNER;
    }

    /**
     * The max strength of the brick (number of hits it initially takes to break)
     */
    private final int FULL_STRENGTH;

    /**
     * The current strength of the brick
     */
    private int m_strength;

    /**
     * Boolean to represent whether the brick is broken or not
     */
    private boolean m_broken;

    /**
     * Returns true if the brick is broken or false otherwise
     *
     * @return true if the brick is broken or false otherwise
     */
    public final boolean isBroken() {
        return m_broken;
    }

    /**
     * Crack class to create cracks on the cement brick type when it is impacted by the ball
     *
     * @author Salaar Mir-modified
     */
    public class Crack {

        /**
         * Number of sections of a crack
         */
        private static final int CRACK_SECTIONS = 3;

        /**
         * Probability the crack jumps
         */
        private static final double JUMP_PROBABILITY = 0.7;

        /**
         * Integer to signify crack being made towards left direction
         */
        private static final int LEFT = 10;

        /**
         * Returns integer to signify crack being made towards left direction
         *
         * @return integer to signify crack being made towards left direction
         */
        public static int getLeft() {
            return LEFT;
        }

        /**
         * Integer to signify crack being made towards right direction
         */
        private static final int RIGHT = 20;

        /**
         * Returns integer to signify crack being made towards right direction
         *
         * @return integer to signify crack being made towards right direction
         */
        public static int getRight() {
            return RIGHT;
        }

        /**
         * Integer to signify crack being made upwards
         */
        private static final int UP = 30;

        /**
         * Returns integer to signify crack being made upwards
         *
         * @return integer to signify crack being made upwards
         */
        public static int getUp() {
            return UP;
        }

        /**
         * Integer to signify crack being made downwards
         */
        private static final int DOWN = 40;

        /**
         * Returns integer to signify crack being made downwards
         *
         * @return integer to signify crack being made downwards
         */
        public static int getDown() {
            return DOWN;
        }

        /**
         * Integer to signify crack being made vertically at a given point
         */
        private static final int VERTICAL = 100;

        /**
         * Integer to signify crack being made horizontally at a given point
         */
        private static final int HORIZONTAL = 200;

        /**
         * The path of the crack
         */
        private final GeneralPath CRACK;

        /**
         * The depth of the crack
         */
        private final int CRACK_DEPTH;

        /**
         * Number of steps of the crack
         */
        private final int STEPS;

        /**
         * Creates the crack with a specified depth and a given number of steps
         *
         * @param crackDepth Depth of the crack
         * @param steps      Number of steps of the crack
         */
        public Crack(int crackDepth, int steps) {

            CRACK = new GeneralPath();
            this.CRACK_DEPTH = crackDepth;
            this.STEPS = steps;

        }

        /**
         * Returns the path of the crack
         *
         * @return the path of the crack
         */
        public GeneralPath draw() {

            return CRACK;
        }

        /**
         * Resets the path of the crack
         */
        public void reset() {
            CRACK.reset();
        }

        /**
         * Makes the crack starting at a specified location and towards an inputted direction
         *
         * @param point     Start point of the crack
         * @param direction Direction of the crack
         */
        protected void makeCrack(Point2D point, int direction) {
            Rectangle bounds = Brick.this.BRICKFACE.getBounds();

            Point impact = new Point((int) point.getX(), (int) point.getY());
            Point start = new Point();
            Point end = new Point();


            switch (direction) {
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrack(impact, tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrack(impact, tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrack(impact, tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrack(impact, tmp);
                    break;

            }
        }

        /**
         * Makes the crack from a specified start point to a specified end point
         *
         * @param start Specified start point
         * @param end   Specified end point
         */
        protected void makeCrack(Point start, Point end) {

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x, start.y);

            double w = (end.x - start.x) / (double) STEPS;
            double h = (end.y - start.y) / (double) STEPS;

            int bound = CRACK_DEPTH;
            int jump = bound * 5;

            double x, y;

            for (int i = 1; i < STEPS; i++) {

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if (inMiddle(i, CRACK_SECTIONS, STEPS))
                    y += jumps(jump, JUMP_PROBABILITY);

                path.lineTo(x, y);

            }

            path.lineTo(end.x, end.y);
            CRACK.append(path, true);
        }

        /**
         * Returns random int to signify where crack start point will be
         *
         * @param bound Depth of the crack
         * @return random int to signify where crack start point will be
         */
        public int randomInBounds(int bound) {
            int n = (bound * 2) + 1;
            return RND.nextInt(n) - bound;
        }

        /**
         * Returns true if crack is made in the middle of the brick and false otherwise
         *
         * @param i         Index number
         * @param steps     Number of sections of the crack
         * @param divisions Number of steps of the crack
         * @return true if crack is made in the middle of the brick and false otherwise
         */
        public boolean inMiddle(int i, int steps, int divisions) {
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return (i > low) && (i < up);
        }

        /**
         * Returns random int to signify where crack will be if random number is greater than the jump probability or 0 otherwise
         *
         * @param bound       the bounds of the brick
         * @param probability The jump probability
         * @return random int to signify where crack will be if random number is greater than the jump probability or 0 otherwise
         */
        public int jumps(int bound, double probability) {

            if (RND.nextDouble() > probability)
                return randomInBounds(bound);
            return 0;

        }

        /**
         * Creates and returns a random point between two points in a specified direction
         *
         * @param from      Starting point
         * @param to        Ending point
         * @param direction Direction for point to be made
         * @return a random point between two points in a specified directio
         */
        public Point makeRandomPoint(Point from, Point to, int direction) {

            Point out = new Point();
            int pos;

            switch (direction) {
                case HORIZONTAL -> {
                    pos = RND.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos, to.y);
                }
                case VERTICAL -> {
                    pos = RND.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x, pos);
                }

            }
            return out;
        }


    }

    /**
     * Creates a brick object with specified information
     *
     * @param name     The name of the brick to specify its type
     * @param pos      The point at which the brick is made
     * @param size     The size of the brick
     * @param border   The border color of the brick
     * @param inner    The inner color of the brick
     * @param strength The strength of the brick
     */
    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {
        RND = new Random();
        m_broken = false;
        this.NAME = name;
        BRICKFACE = makeBrickFace(pos, size);
        this.BORDER = border;
        this.INNER = inner;
        this.FULL_STRENGTH = this.m_strength = strength;

    }

    /**
     * Creates and returns a brick face at a specified point and size
     *
     * @param pos  Point at which to make the brick face
     * @param size Size of the brick face
     * @return a brick face at a specified point and size
     */
    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    /**
     * Returns true if an unbroken brick has been impacted and broken or false otherwise
     *
     * @param point Point at which the brick was impacted
     * @param dir   Direction in which to make the crack if brick has not yet been broken
     * @return true if an unbroken brick has been impacted and broken or false otherwise
     */
    public boolean setImpact(Point2D point, int dir) {
        if (m_broken)
            return false;
        impact();
        return m_broken;
    }

    /**
     * Returns a brick object
     *
     * @return a brick object
     */
    public abstract Shape getBrick();

    /**
     * Returns where the brick was impacted by the ball
     *
     * @param b Ball which impacted the brick
     * @return where the brick was impacted by the ball
     */
    public final int findImpact(Ball b) {
        if (m_broken)
            return 0;
        int out = 0;
        if (BRICKFACE.contains(b.getRIGHT()))
            out = LEFT_IMPACT;
        else if (BRICKFACE.contains(b.getLEFT()))
            out = RIGHT_IMPACT;
        else if (BRICKFACE.contains(b.getUP()))
            out = DOWN_IMPACT;
        else if (BRICKFACE.contains(b.getDOWN()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Repairs brick by resetting it to full strength and setting it to unbroken
     */
    public void repair() {
        m_broken = false;
        m_strength = FULL_STRENGTH;
    }

    /**
     * Simulates the brick getting hit
     */
    public void impact() {

        int broken = 0;
        m_strength--;
        m_broken = (m_strength == broken);

        int scoreIncrease = 10;
        if (m_strength == 0)
            setScore(Brick.getScore() + scoreIncrease);
    }


}





