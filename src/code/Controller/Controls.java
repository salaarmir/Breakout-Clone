package Controller;

import Initialization.GameBoard;
import Objects.Paddle;
import Objects.Wall;

import java.awt.*;
import java.awt.event.*;

/**
 * Class to initiate the controls for the game
 *
 * @author Salaar Mir
 */
public class Controls implements KeyListener, MouseListener, MouseMotionListener {

    /**
     * Object used to setup the controls for the game
     */
    private static final Controls CONTROLS = new Controls();

    /**
     * Returns only instance of the game controls object
     *
     * @return only instance of the game controls object
     */
    public static Controls getControls() {
        return CONTROLS;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Response in game when a key is pressed
     *
     * @param e The key that was pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            Paddle.getPaddle().moveLeft();
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            Paddle.getPaddle().movRight();
        }
        if (code == KeyEvent.VK_SPACE) {
            if (!GameBoard.getGameBoard().isShowPauseMenu())
                if (GameBoard.getGameBoard().getGameTimer().isRunning())
                    GameBoard.getGameBoard().getGameTimer().stop();
                else
                    GameBoard.getGameBoard().getGameTimer().start();
        }
        if (code == KeyEvent.VK_ESCAPE) {
            GameBoard.getGameBoard().setShowPauseMenu(!GameBoard.getGameBoard().isShowPauseMenu());
            GameBoard.getGameBoard().repaint();
            GameBoard.getGameBoard().getGameTimer().stop();
        }
        if (code == KeyEvent.VK_F1) {
            if (e.isAltDown() && e.isShiftDown())
                GameBoard.getGameBoard().getDebugConsole().setVisible(true);
        }
    }

    /**
     * Response in game when a key is released
     *
     * @param keyEvent The key that was released
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Paddle.getPaddle().stop();
    }

    /**
     * Resonse in game when the mouse is clicked
     *
     * @param mouseEvent The event of mouse being clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!GameBoard.getGameBoard().isShowPauseMenu())
            return;
        if (GameBoard.getGameBoard().getContinueButtonRect().contains(p)) {
            GameBoard.getGameBoard().setShowPauseMenu(false);
            GameBoard.getGameBoard().repaint();
        } else if (GameBoard.getGameBoard().getRestartButtonRect().contains(p)) {
            GameBoard.getGameBoard().setMessage("Restarting Game...");
            Wall.getWall().ballReset();
            Wall.getWall().wallReset();
            GameBoard.getGameBoard().setShowPauseMenu(false);
            GameBoard.getGameBoard().repaint();
        } else if (GameBoard.getGameBoard().getExitButtonRect().contains(p)) {
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Response in game when the mouse is moved
     *
     * @param mouseEvent The event of the mouse being moved
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (GameBoard.getGameBoard().getExitButtonRect() != null && GameBoard.getGameBoard().isShowPauseMenu()) {
            if (GameBoard.getGameBoard().getExitButtonRect().contains(p) || GameBoard.getGameBoard().getContinueButtonRect().contains(p) || GameBoard.getGameBoard().getRestartButtonRect().contains(p))
                GameBoard.getGameBoard().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                GameBoard.getGameBoard().setCursor(Cursor.getDefaultCursor());
        } else {
            GameBoard.getGameBoard().setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * Pauses the game and prints a message saying focus on the game was lost if the user switches tabs and stops playing
     */
    public void onLostFocus() {
        GameBoard.getGameBoard().getGameTimer().stop();
        GameBoard.getGameBoard().setMessage("Focus Lost");
        GameBoard.getGameBoard().repaint();
    }

}
