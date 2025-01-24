package Extensions;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to allow for the game to have background music while its running
 *
 * @author Salaar Mir
 */
public class Music {


    /**
     * Object used to play the background music
     */
    private static Music m_music;

    static {
        try {
            m_music = new Music();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the only instance of the music object used to play background music
     *
     * @return the only instance of the music object used to play background music
     */
    public static Music getMusic() {
        return m_music;
    }

    /**
     * File to store file path of song to be played in the background
     */
    private File m_musicFile = new File("src/code/Music/music.wav");

    /**
     * Audio input stream to play music on
     */
    private AudioInputStream m_audioStream = AudioSystem.getAudioInputStream((m_musicFile));

    /**
     * Clip of song to be played in the background
     */
    private Clip m_clip = AudioSystem.getClip();

    /**
     * Constructor of Music class to be able to play and stop the music
     *
     * @throws UnsupportedAudioFileException Exception error if an unsupported audio file is played
     * @throws IOException                   Exception error when there is a failure while searching for a file
     * @throws LineUnavailableException      Exception error indicating line cannot be opened because it is unavailable
     */
    public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    /**
     * Play song clip in the background
     *
     * @throws LineUnavailableException Exception error indicating line cannot be opened because it is unavailable
     * @throws IOException              Exception error when there is a failure while searching for a file
     */
    void playMusic() throws LineUnavailableException, IOException {


        m_clip.open(m_audioStream);
        m_clip.start();
        m_clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    /**
     * Stop song clip in the background
     */
    void stopMusic() {

        m_clip.stop();
        m_audioStream = null;
        m_clip = null;
        m_music = null;
        m_musicFile = null;

    }


}
