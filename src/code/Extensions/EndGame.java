package Extensions;

import Objects.Brick;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to allow users to input their usernames and save their scores after they have successfully finished the game
 *
 * @author Salaar Mir
 */
public class EndGame {

    /**
     * Score panel to display user's score on
     */
    private static final EndGame SCORE = new EndGame();

    /**
     * Returns the only instance of the score object to display the users score on
     *
     * @return the only instance of the score object to display the users score on
     */
    public static EndGame getScore() {
        return SCORE;
    }

    /**
     * Grid pane object to organise high score information on
     */
    private final GridPane ROOT = new GridPane();

    /**
     * Frame panel to display high score information on
     */
    private final JFrame FRAME = new JFrame("Type Your Username to Save Your Score!");

    /**
     * Java FX Panel to be able to add to a Java Swing JFrame to be able to use JavaFX features
     */
    private final JFXPanel FXPANEL = new JFXPanel();

    /**
     * String to store inputted name by user
     */
    private String m_username = "";

    /**
     * Returns the name the user inputted into the text box
     *
     * @return the name the user inputted into the text box
     */
    public String getUsername() {
        return m_username;
    }

    /**
     * Width of the high score panel
     */
    private final int DEF_WIDTH = 450;

    /**
     * Height of the high score panel
     */
    private final int DEF_HEIGHT = 250;

    /**
     * Size of text on high score panel
     */
    private final int TEXT_SIZE = 14;

    /**
     * Font of text displayed on high score panel
     */
    private final Font MENUFONT = new Font("Monospaced", TEXT_SIZE);

    private final Music BACKMUSIC = Music.getMusic();

    /**
     * Constructor to create the EndGame object to display the popup when the user successfully completes the game
     */
    public EndGame() {
    }

    /**
     * Initialise the high score panel and display it to user
     */
    public void displayMenu() {

        FRAME.add(FXPANEL);
        FRAME.setSize(DEF_WIDTH, DEF_HEIGHT);
        FRAME.setVisible(true);
        FRAME.setLocationRelativeTo(null);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showScore();
    }

    /**
     * Show user their high score and ask for username input to store in a high score file
     */
    public void showScore() {

        final int HB_SPACING = 10;
        final int VB_SPACING = 10;

        final int VB_XCOORD = 7;
        final int VB_YCOORD = 9;

        Text title = new Text("Congratulations, you won!! Your Score Was : " + Brick.getScore());
        Label label1 = new Label("Username :");
        TextField textField = new TextField();
        Button submit = new Button("Submit");
        HBox hb = new HBox();
        VBox vb = new VBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(HB_SPACING);
        m_username = textField.getText();
        vb.getChildren().addAll(title, hb, submit);
        vb.setSpacing(VB_SPACING);
        submit.setOnAction(e -> {
            m_username = textField.getText();
            scoreSubmitted(vb);
            createFile();
            writeFile();

        });

        title.setFont(MENUFONT);

        int hGap = 10;
        ROOT.setHgap(hGap);

        int vGap = 10;
        ROOT.setVgap(vGap);
        ROOT.setMinSize(DEF_WIDTH, DEF_HEIGHT);
        ROOT.add(vb, VB_XCOORD, VB_YCOORD);

        FXPANEL.setScene(new Scene(ROOT));
    }

    /**
     * Submit high score panel to add to high score file
     *
     * @param vb VBox pane to hide so another popup can be displayed to show users their score has been submitted
     */
    public void scoreSubmitted(VBox vb) {

        final int VB2_SPACING = 20;

        final int VB2_XCOORD = 3;
        final int VB2_YCOORD = 7;


        int popupWidth = 700;
        int popupHeight = 200;

        FRAME.setSize(popupWidth, popupHeight);
        VBox vb2 = new VBox();
        vb.setVisible(false);
        Text text = new Text("Hey " + getUsername() + "! Your Username and Score were saved to the HighScore file!");
        FRAME.setLocationRelativeTo(null);
        Button end = new Button("End Game");
        end.setOnAction(e -> {
            BACKMUSIC.stopMusic();
            System.exit(0);

        });
        vb2.getChildren().addAll(text, end);
        vb2.setSpacing(VB2_SPACING);
        ROOT.add(vb2, VB2_XCOORD, VB2_YCOORD);

    }

    /**
     * Create high score file to save names and scores of users
     */
    public void createFile() {
        try {
            File highScore = new File("HighScores.txt");
            highScore.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Write the names of users and their high scores onto the file
     */
    private void writeFile() {
        try {
            FileWriter writer = new FileWriter("HighScores.txt", true);
            writer.write("\n");
            writer.write(" " + getUsername());
            writer.write("\t\t");
            writer.write(" " + Brick.getScore());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
