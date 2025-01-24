package Objects;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Class to create the rubber ball type the user can hit using the paddle
 *
 * @author Salaar Mir-modified
 */
public class RubberBall extends Ball {

    /**
     * Name of the ball to distinguish its type
     */
    private final String NAME = "Rubber Ball";

    /**
     * The radius of the ball
     */
    private static final int DEF_RADIUS = 10;

    /**
     * The inner color of the ball
     */
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);

    /**
     * The border color of the ball
     */
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();

    /**
     * Creates the ball with specified point coordinates to be its center
     *
     * @param center the point coordinates to be the center of the ball
     */
    public RubberBall(Point2D center) {
        super(center, DEF_RADIUS, DEF_RADIUS, DEF_INNER_COLOR, DEF_BORDER_COLOR);
    }

    /**
     * Makes and returns the shape of the ball
     *
     * @param center  Coordinates for the center of the ball
     * @param radiusA Horizontal radius of the ball
     * @param radiusB Vertical radius of the ball
     * @return the shape of the created ball
     */
    @Override
    protected Shape makeBall(Point2D center, float radiusA, float radiusB) {

        int radiusADivider = 2;
        int radiusBDivider = 2;

        double x = center.getX() - (radiusA / radiusADivider);
        double y = center.getY() - (radiusB / radiusBDivider);

        return new Ellipse2D.Double(x, y, radiusA, radiusB);
    }
}
