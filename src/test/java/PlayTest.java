import ScreenObjects.PlayerScreen;
import ScreenObjects.SpotifyScreen;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

/**
 * Created by majid on 24/10/15.
 */
public class PlayTest extends SpotifyBaseTest {

    /**
     * Tests playing a track
     * @param username
     * @param password
     * @param trackToPlay
     * @throws FindFailed
     * @throws InterruptedException
     */
    @Parameters ({"validUsername", "validPassword", "trackToPlay"})
    @Test
    public void testPlayingTracks (String username, String password, String trackToPlay) throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertTrue(playerScreen.playAndVerify(trackToPlay));

    }
}
