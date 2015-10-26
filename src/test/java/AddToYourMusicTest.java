import ScreenObjects.LoginScreen;
import ScreenObjects.PlayerScreen;
import ScreenObjects.SpotifyScreen;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by majid on 25/10/15.
 */
public class AddToYourMusicTest extends SpotifyBaseTest {

    private static final int LOGIN_WAIT_TIME = 3000; //time in ms
    private static final int LOGOUT_WAIT_TIME = 5000; //time in ms

    /**
     * The main purpose of this test is to make sure a state change related to a user account (in this case a new
     * track added to "Your music") preserves after user logout and login. It also tests that data is correctly stored
     * in database and it is not lost.
     * @param trackToAdd name of the track to be added to "your music" list
     * @param username a valid username
     * @param password a valid pasword
     * @throws FindFailed
     * @throws InterruptedException
     */
    @Parameters ({"trackToAdd", "validUsername", "validPassword"})
    @Test
    public void testAddToYourMusic (String trackToAdd, String username, String password)
            throws FindFailed, InterruptedException {
        PlayerScreen playerScreen = SpotifyScreen.getPlayerWindow(username, password);
        assertFalse(playerScreen.checkIfTrackIsInYourSong()); // fail if track we want to add is already added
        playerScreen.addToYourMusic(trackToAdd);
        playerScreen.logout();
        wait(LOGOUT_WAIT_TIME);
        LoginScreen loginWindow = new LoginScreen();
        loginWindow.login(username , password);
        wait(LOGIN_WAIT_TIME);
        assertTrue(playerScreen.checkIfTrackIsInYourSong());
    }

    // a small wait function to make the code above more readable.
    private void wait (int waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }
}
