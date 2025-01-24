package Objects;

import java.awt.*;

/**
 * Class to create the gold brick type
 *
 * @author Salaar Mir
 */
public class GoldBrick extends Brick {

    /**
     * The name to distinguish the type of brick
     */
    private static final String NAME = "Gold Brick";

    /**
     * The inner color of the brick
     */
    private static final Color DEF_INNER = new Color(218, 165, 32);

    /**
     * The color of the brick's border
     */
    private static final Color DEF_BORDER = Color.BLACK;

    /**
     * The strength of the break (the number of hits it takes to break)
     */
    private static final int GOLD_STRENGTH = 5;

    /**
     * Face of the brick
     */
    private final Shape m_brickFace;

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
    public GoldBrick(Point point, Dimension size) {
        super(NAME, point, size, DEF_BORDER, DEF_INNER, GOLD_STRENGTH);
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


}
