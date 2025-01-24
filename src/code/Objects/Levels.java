package Objects;

import java.awt.*;

/**
 * Class to create the different playable levels in the game
 *
 * @author Salaar Mir
 */
public class Levels {

    /**
     * Creates the game level where all bricks are the same type
     *
     * @param drawArea       The rectangular area where the bricks should be drawn
     * @param brickCnt       The number of bricks to draw
     * @param lineCnt        The number of lines to be filled with bricks
     * @param brickSizeRatio Length of bricks height compared to its width
     * @param type           The type of brick to draw
     * @return an array of bricks that will be drawn onto the game board
     */
    public static Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type) {
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;

    }

    /**
     * Creates the game level where 2 different types of bricks are alternating along the lines
     *
     * @param drawArea       The rectangular area where the bricks should be drawn
     * @param brickCnt       The number of bricks to draw
     * @param lineCnt        The number of lines to be filled with bricks
     * @param brickSizeRatio Length of bricks height compared to its width
     * @param typeA          First type of brick to draw
     * @param typeB          Second type of brick to draw
     * @return an array of bricks that will be drawn onto the game board
     */
    public static Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        // if brickCount is not divisible by line count, brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? Wall.makeBrick(p, brickSize, typeA) : Wall.makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    /**
     * Creates the game level where 3 sets of bricks are alternating every column
     *
     * @param drawArea       The rectangular area where the bricks should be drawn
     * @param brickCnt       The number of bricks to draw
     * @param lineCnt        The number of lines to be filled with bricks
     * @param brickSizeRatio Length of bricks height compared to its width
     * @param typeA          First type of brick to draw
     * @param typeB          Second type of brick to draw
     * @param typeC          Third type of brick to draw
     * @return an array of bricks that will be drawn onto the game board
     */
    public static Brick[] makeColumnLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            if (i == 2 || i == 5 || i == 8 || i == 12 || i == 15 || i == 18 || i == 22 || i == 25 || i == 28)
                tmp[i] = Wall.makeBrick(p, brickSize, typeC);
            else if (i == 1 || i == 4 || i == 7 || i == 11 || i == 14 || i == 17 || i == 21 || i == 24 || i == 27)
                tmp[i] = Wall.makeBrick(p, brickSize, typeB);
            else
                tmp[i] = Wall.makeBrick(p, brickSize, typeA);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;

    }

    /**
     * Creates final game level where each row is a different brick type and there is a final row of golden bricks at the end
     *
     * @param drawArea       The rectangular area where the bricks should be drawn
     * @param brickCnt       The number of bricks to draw
     * @param lineCnt        The number of lines to be filled with bricks
     * @param brickSizeRatio Length of bricks height compared to its width
     * @param typeA          First type of brick to draw
     * @param typeB          Second type of brick to draw
     * @param typeC          Third type of brick to draw
     * @return an array of bricks that will be drawn onto the game board
     */
    public static Brick[] makeFinalLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC) {
        // if brickCount is not divisible by line count,brickCount is adjusted to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i <= 9; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, typeB);
        }
        for (i = 10; i <= 19; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, typeA);
        }
        for (i = 20; i <= 29; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, typeC);
        }
        for (i = 30; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = Wall.makeBrick(p, brickSize, typeA);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;

    }


}
