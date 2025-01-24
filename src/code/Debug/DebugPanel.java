package Debug;

import Objects.Wall;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class to display the debugging console and set up its features
 *
 * @author Salaar Mir-modifed
 */
public class DebugPanel extends JPanel {

    /**
     * Background color of the DebugPanel
     */
    private static final Color DEF_BKG = Color.BLACK;

    /**
     * Slider to adjust the horizontal speed of the ball
     */
    private JSlider ballXSpeed;

    /**
     * Slider to adjust the vertical speed of the ball
     */
    private JSlider ballYSpeed;

    /**
     * Creates the panel and adds all its features onto it
     *
     * @param wall The wall object to be able to skip levels of the game and reset the ball count of
     */
    public DebugPanel(Wall wall) {

        initialize();

        JButton skipLevel = makeButton("Skip Level", e -> wall.nextLevel());
        JButton resetBalls = makeButton("Reset Balls", e -> wall.resetBallCount());

        ballXSpeed = makeSlider(-4, 4, e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4, 4, e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * Initialises the DebugPanel by setting the background color and grid layout
     */
    public void initialize() {
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2, 2));
    }

    /**
     * Creates and returns a button with the specific title and action listener
     *
     * @param title Title of the button
     * @param e     Action listener to be able to respond when pressed
     * @return a button with the specific title and action listener
     */
    public JButton makeButton(String title, ActionListener e) {
        JButton out = new JButton(title);
        out.addActionListener(e);
        return out;
    }

    /**
     * Creates and returns a slider with a specified minimum and maximum value and a change listener
     *
     * @param min Minimum value of the slider
     * @param max Maximum value of the slider
     * @param e   Change listener to be able to respond when the slider is used
     * @return a slider with a specified minimum and maximum value and a change listener
     */
    public JSlider makeSlider(int min, int max, ChangeListener e) {
        JSlider out = new JSlider(min, max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * Set new values for the horizontal and vertical ball speed
     *
     * @param x New value for the horizontal ball speed
     * @param y New value for the vertical ball speed
     */
    public void setValues(int x, int y) {
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}
