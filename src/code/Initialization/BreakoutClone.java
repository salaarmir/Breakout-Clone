package Initialization;

import Extensions.StartMenu;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Class to first start the game to let the user play
 *
 * @author Salaar Mir-modified
 */
public class BreakoutClone {

    /**
     * Displays the initial start menu to the user to be able to start playing the game
     *
     * @param args The command line arguments
     * @throws LineUnavailableException Exception error indicating line cannot be opened because it is unavailable
     * @throws IOException              Exception error when there is a failure while searching for a file
     */
    public static void main(String[] args) throws LineUnavailableException, IOException {

        StartMenu startScreen = StartMenu.getMenu();
        startScreen.initAndShowMenu();

    }
    // use [space] to start/pause the game
    // use [←] to move the player left
    // use [→] to move the player right
    // use [esc] to enter/exit pause menu
    // use [alt+shift+f1] at any time to display debug panel
}
