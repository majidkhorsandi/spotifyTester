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

    @Parameters ({"invalidSearch", "validUsername", "validPassword"})
    @Test
    public void testNotFoundSearch(String searchParameter, String username, String password) throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertFalse(playerScreen.search(searchParameter));
    }

    @Parameters ({"validSearch", "validUsername", "validPassword"})
    @Test
    public void testFoundSearch(String searchParameter, String username, String password) throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertTrue(playerScreen.search(searchParameter));
    }



}
