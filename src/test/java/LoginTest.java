/**
 * Created by majid on 23/10/15.
 */

import Helpers.ScreenTypes;
import ScreenObjects.LoginScreen;
import ScreenObjects.SpotifyScreen;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends SpotifyBaseTest {

    private static final int LOGIN_WAIT_TIME = 3000; // time in ms

    @Parameters ({"validUsername", "invalidPassword"})
    @Test
    public void testInvalidLogin (String username, String password) throws InterruptedException, FindFailed {
        LoginScreen login = SpotifyScreen.getLoginWindow();
        login.login(username, password);
        assertTrue(login.isLoginErrorVisible());
    }

    @Parameters ({"validUsername", "validPassword"})
    @Test
    public void testValidLogin (String username, String password) throws FindFailed, InterruptedException {
        LoginScreen loginWindow = SpotifyScreen.getLoginWindow();
        loginWindow.login(username, password);
        wait(LOGIN_WAIT_TIME);
        assertNotEquals(SpotifyScreen.getActiveScreen(), ScreenTypes.LOGIN);
    }

    private void wait (int waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }

}
