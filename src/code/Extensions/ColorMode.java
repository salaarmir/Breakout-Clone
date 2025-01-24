package Extensions;

import Initialization.GameFrame;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import javax.swing.*;

/**
 * Class to Allow users to choose between light and dark mode when playing the game
 *
 * @author Salaar Mir
 */
public class ColorMode {

    /**
     * The only instance of the ColorMode object to display the light and dark mode options to the user
     */
    private static final ColorMode COLORMODE = new ColorMode();

    /**
     * Returns the only ColorMode object in the entire project
     *
     * @return the only ColorMode object in the entire project
     */
    public static ColorMode getColorMode() {
        return COLORMODE;
    }

    /**
     * Width of the popup to let user choose between light and dark more
     */
    private final int DEF_WIDTH = 400;

    /**
     * Height of the popup to let user choose between light and dark more
     */
    private final int DEF_HEIGHT = 200;

    /**
     * Frame to display the light or dark mode popup
     */
    private final JFrame FRAME = new JFrame("Choose Light or Dark Mode");

    /**
     * JavaFX panel to be able to add onto a Java Swing JFrame to be able to use JavaFX features
     */
    private final JFXPanel PANEL = new JFXPanel();

    /**
     * HBox pane to add popup features onto
     */
    private final HBox HB = new HBox();

    /**
     * Boolean value to store information about whether the user chose the dark mode or light mode option
     */
    private Boolean m_isDarkMode;

    /**
     * Returns true if the user chose the dark mode option or false otherwise
     *
     * @return true if the user chose the dark mode option or false otherwise
     */
    public Boolean getDarkMode() {
        return m_isDarkMode;
    }

    /**
     * Initialises and displays the frame with the option to choose between light or dark mode to the user
     */
    void initAndShowMenu() {

        FRAME.add(PANEL);
        FRAME.setSize(DEF_WIDTH, DEF_HEIGHT);
        FRAME.setVisible(true);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setColorMode();
    }

    /**
     * Organises the features of the ColorMode popup and lets user choose between light or dark mode for the game
     */
    public void setColorMode() {

        Button light = new Button("Light Mode");
        light.setOnAction(e -> {
            m_isDarkMode = false;
            FRAME.setVisible(false);
            GameFrame.getGameFrame().initialize();
        });
        Button dark = new Button("Dark Mode");
        dark.setOnAction(e -> {
            m_isDarkMode = true;
            FRAME.setVisible(false);
            GameFrame.getGameFrame().initialize();
        });

        int hbSpacing = 100;
        HB.setSpacing(hbSpacing);
        HB.getChildren().addAll(light, dark);
        HB.setAlignment(Pos.CENTER);
        PANEL.setScene(new Scene(HB, DEF_WIDTH, DEF_HEIGHT));

    }
}


