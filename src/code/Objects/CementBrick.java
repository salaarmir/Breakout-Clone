package Objects;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Class to create the cement brick type
 *
 * @author Salaar Mir-modified
 */
public class CementBrick extends Brick {


    /**
     * The name to distinguish the type of brick
     */
    private static final String NAME = "Cement Brick";

    /**
     * The inner color of the brick
     */
    private static final Color DEF_INNER = new Color(147, 147, 147);

    /**
     * The color of the brick's border
     */
    private static final Color DEF_BORDER = new Color(217, 199, 175);

    /**
     * The strength of the break (the number of hits it takes to break)
     */
    private static final int CEMENT_STRENGTH = 2;

    /**
     * Object used to display the cracks on the brick when its hit
     */
    private final Crack m_crack;

    /**
     * Face of the brick
     */
    private Shape m_brickFace;

    /**
     * Returns the face of the brick
     *
     * @return the face of the brick
     */
    @Override
    public Shape getBrick() {
        return m_brickFace;
    }

    /**
     * Initialises the brick to be created in the game
     *
     * @param point coordinates at which the brick should be drawn
     * @param size  size the brick should be drawn
     */
    public CementBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CEMENT_STRENGTH);

        final int DEF_CRACK_DEPTH = 1;
        final int DEF_STEPS = 35;
        m_crack = new Crack(DEF_CRACK_DEPTH, DEF_STEPS);
        m_brickFace = super.getBRICKFACE();
    }

    /**
     * Makes and returns the brick face at the position and size specified
     *
     * @param pos  Coordinates where the brick face should be drawn
     * @param size Size the brick should be drawn
     * @return the brick face shape
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * Set the impact of the ball on the brick at the coordinates specified to make a crack in the direction stated
     *
     * @param point Coordinates at which to create the crack after the ball impacts the brick
     * @param dir   Direction in which the crack should be drawn
     * @return true if the brick has been broken or if a crack has been drawn or false otherwise
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return true;
        super.impact();
        if (!super.isBroken()) {
            m_crack.makeCrack(point, dir);
            updateBrick();
            return true;
        }
        return false;
    }

    /**
     * Draw crack onto brick after it has been hit
     */
    private void updateBrick() {
        if (!super.isBroken()) {
            GeneralPath gp = m_crack.draw();
            gp.append(super.getBRICKFACE(), false);
            m_brickFace = gp;
        }
    }

    /**
     * Repair brick by removing all cracks and resetting it to full strength
     */
    public void repair() {
        super.repair();
        m_crack.reset();
        m_brickFace = super.getBRICKFACE();
    }
}
