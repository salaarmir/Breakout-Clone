package Objects;

import java.awt.*;

/**
 * Class to create the clay brick type
 *
 * @author Salaar Mir-modified
 */
public class ClayBrick extends Brick {

    /**
     * The name to distinguish the type of brick
     */
    private static final String NAME = "Clay Brick";

    /**
     * The inner color of the brick
     */
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();

    /**
     * The color of the brick's border
     */
    private static final Color DEF_BORDER = Color.GRAY;

    /**
     * The strength of the break (the number of hits it takes to break)
     */
    private static final int CLAY_STRENGTH = 1;

    /**
     * Initialises the brick to be created in the game
     *
     * @param point coordinates at which the brick should be drawn
     * @param size  size the brick should be drawn
     */
    public ClayBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
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
     * Returns the face of the brick
     *
     * @return the face of the brick
     */
    @Override
    public Shape getBrick() {
        return super.getBRICKFACE();
    }


}
