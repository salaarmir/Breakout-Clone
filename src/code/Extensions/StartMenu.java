package Extensions;

import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to display the start menu to the user and allow them to choose whether they want to start or end the game or view the HighScores file
 *
 * @author Salaar Mir
 */
public class StartMenu {

    /**
     * Width of the start menu
     */
    private final int DEF_WIDTH = 600;

    /**
     * Height of the start menu
     */
    private final int DEF_HEIGHT = 450;

    /**
     * Object to play music in the background of the game
     */
    private final Music BACKMUSIC = Music.getMusic();

    /**
     * Pane used to arrange information displayed on the start menu
     */
    private final BorderPane ROOT = new BorderPane();

    /**
     * Frame to display the start menu on
     */
    private final JFrame FRAME = new JFrame("space = start/pause   ←/→ = move left/right   esc = menu   1 Brick = 10 Points");

    /**
     * JavaFX panel to add onto a Java Swing frame so JavaFX features can be used
     */
    private final JFXPanel FXPANEL = new JFXPanel();

    /**
     * Image to be displayed in the background of the start menu
     */
    private final Image IMAGE = new Image("file:src/code/Images/brickbackground.png", true);

    /**
     * Background image of the start menu
     */
    private final BackgroundImage BIMAGE = new BackgroundImage(
            IMAGE,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(DEF_WIDTH, DEF_HEIGHT, true, true, true, true));

    /**
     * Background of the start menu
     */
    private final Background BACKGROUND = new Background(BIMAGE);

    /**
     * Sets the background color of the start meny
     */
    private void setBackground(Background back) {

        ROOT.setBackground(back);
    }

    /**
     * Only instance of the start menu object to be displayed to the user when the game first starts
     */
    private static StartMenu m_startMenu;

    static {
        try {
            m_startMenu = new StartMenu();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the start menu object
     *
     * @return the start menu object
     */
    public static StartMenu getMenu() {
        return m_startMenu;
    }

    /**
     * Creates new start menu object
     *
     * @throws UnsupportedAudioFileException Exception error if an unsupported audio file is played
     * @throws LineUnavailableException      Exception error indicating line cannot be opened because it is unavailable
     * @throws IOException                   Exception error when there is a failure while searching for a file
     */
    private StartMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

    }

    /**
     * Initialises the start menu and displays it to the user
     *
     * @throws LineUnavailableException Exception error indicating line cannot be opened because it is unavailable
     * @throws IOException              Exception error when there is a failure while searching for a file
     */
    public void initAndShowMenu() throws LineUnavailableException, IOException {

        FRAME.add(FXPANEL);
        FRAME.setSize(DEF_WIDTH, DEF_HEIGHT);
        FRAME.setVisible(true);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BACKMUSIC.playMusic();
        sceneSet();

    }

    /**
     * Organises the start menu by adding all the components to the main frame and sets the background color
     */
    private void sceneSet() {

        ROOT.setPrefSize(DEF_WIDTH, DEF_HEIGHT);
        setBottomScene();
        setMiddleScene();
        setUpperScene();

        setBackground(BACKGROUND);
        FXPANEL.setScene(new Scene(ROOT, DEF_WIDTH, DEF_HEIGHT));
    }

    /**
     * Sets the upper part of the start menu
     */
    private void setUpperScene() {

        final int TOPHB_SPACING = 100;

        HBox topHB = new HBox();
        Image name = new Image("file:src/code/Images/name.png");
        ImageView title = new ImageView(name);

        int titleHeight = 320;
        title.setFitHeight(titleHeight);

        int titleWidth = 320;
        title.setFitWidth(titleWidth);
        title.setPreserveRatio(true);
        Button blue = new Button("Blue");
        blue.setOnAction(e -> ROOT.styleProperty().set("-fx-background-color: #0099FF"));
        Button red = new Button("Red");
        red.setOnAction(e -> ROOT.styleProperty().set("-fx-background-color: #CF1E0F"));


        topHB.setSpacing(TOPHB_SPACING);
        topHB.getChildren().addAll(blue, title, red);
        topHB.setAlignment(Pos.TOP_CENTER);
        ROOT.setTop(topHB);

    }

    /**
     * Sets the middle part of the start menu
     */
    private void setMiddleScene() {

        int vbSpacing = 20;

        Button start = new Button("Start Game");
        Button end = new Button("End Game");
        Button highScore = new Button("HighScores");
        VBox vb = new VBox();
        vb.setSpacing(vbSpacing);
        start.setOnAction(e -> {
            ColorMode.getColorMode().initAndShowMenu();
            FRAME.setVisible(false);
        });
        end.setOnAction(e -> {
            BACKMUSIC.stopMusic();
            System.exit(0);
        });
        highScore.setOnAction(e -> openFile());
        vb.getChildren().addAll(start, end, highScore);

        vb.setAlignment(Pos.CENTER);
        ROOT.setCenter(vb);

    }

    /**
     * Sets the bottom part of the start menu
     */
    private void setBottomScene() {

        HBox bottomHB = new HBox();

        int bottomHBSpacing = 226;

        Button green = new Button("Green");
        green.setOnAction(e -> ROOT.styleProperty().set("-fx-background-color: #2CAE0F"));
        Button yellow = new Button("Yellow");
        yellow.setOnAction(e -> ROOT.styleProperty().set("-fx-background-color: #EDDF0C"));
        Button brick = new Button("Brick");
        brick.setOnAction(e -> setBackground(BACKGROUND));

        bottomHB.setSpacing(bottomHBSpacing);
        bottomHB.getChildren().addAll(green, brick, yellow);
        bottomHB.setAlignment(Pos.BOTTOM_CENTER);
        ROOT.setBottom(bottomHB);

    }

    /**
     * Opens the high score file to show users their past scores
     */
    private void openFile() {
        try {

            File highScore = new File("HighScores.txt");
            highScore.createNewFile();
            if (!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (highScore.exists())
                desktop.open(highScore);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


