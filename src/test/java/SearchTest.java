import ScreenObjects.PlayerScreen;
import ScreenObjects.SpotifyScreen;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by majid on 24/10/15.
 */
public class SearchTest extends SpotifyBaseTest {

    /**
     * Tests searching for anything that does not exist in the backend database i.e track, album, artist, etc...
     * @param searchParameter
     * @param username Needs to be logged in so user name is needed
     * @param password Needs to be logged in so password is needed.
     * @throws FindFailed
     * @throws InterruptedException
     */
    @Parameters ({"invalidSearch", "validUsername", "validPassword"})
    @Test
    public void testNotFoundSearch(String searchParameter, String username, String password) throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertFalse(playerScreen.search(searchParameter));
    }

    /**
     * Tests searching for something that exists in Spotify's backend.
     * @param searchParameter
     * @param username
     * @param password
     * @throws FindFailed
     * @throws InterruptedException
     */
    @Parameters ({"validSearch", "validUsername", "validPassword"})
    @Test
    public void testFoundSearch(String searchParameter, String username, String password) throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertTrue(playerScreen.search(searchParameter));
    }



}
