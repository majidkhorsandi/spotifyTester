package ScreenObjects;

import Helpers.ScreenTypes;
import Helpers.SimilarityLevels;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

/**
 * Created by majid on 25/10/15.
 * Acts as a parent screen to PlayerScreen and LoginScreen. It is used to encapsulate the logic of deciding which page
 * we are on. This way LoginScreen can always assume Spotify app is in the login screen and PlayerScreen can assume Spotify app
 *  is on the player screen. So other screens don't need to care about current state of the application.
 */
public class SpotifyScreen {

    private final static int LOGIN_WAIT_TIME = 3000; //time in ms
    private final static int LOGOUT_WAIT_TIME = 3000; //time in ms

    static Pattern loginPage = new Pattern ("./src/main/resources/loginPage.png");
    static Pattern playerPage = new Pattern("./src/main/resources/mainPage.png");
    static Region spotifyScreen = new Screen();

    private SpotifyScreen() {};

    /**
     * Checks which page we are currently in i.e Player of Login. In either case returns a login screen
     * @return a Login screen to be used by any test that needs to log in.
     * @throws FindFailed
     * @throws InterruptedException
     */
    public static LoginScreen getLoginWindow() throws FindFailed, InterruptedException {
        try {
            spotifyScreen.find(loginPage.similar(SimilarityLevels.SEVENTY_PERCENT.getSimilarityLevel()));
            return new LoginScreen();
        }
        catch (FindFailed findFailed){
            try {
                spotifyScreen.find(playerPage.similar(SimilarityLevels.TEN_PERCENT.getSimilarityLevel()));
                PlayerScreen playerScreen = new PlayerScreen();
                playerScreen.logout();
                wait(LOGOUT_WAIT_TIME);
                return new LoginScreen();
            }
            catch (FindFailed findFailed1) {
                throw new IllegalStateException();
            }
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return A player screen with the given username logged in.
     * @throws FindFailed
     * @throws InterruptedException
     */
    public static PlayerScreen getPlayerWindow(String username, String password) throws FindFailed, InterruptedException {
        try {
            spotifyScreen.find(loginPage.similar(SimilarityLevels.SEVENTY_PERCENT.getSimilarityLevel()));
            LoginScreen loginWindow = new LoginScreen();
            loginWindow.login(username, password);
            wait(LOGIN_WAIT_TIME);
            return new PlayerScreen();
        }
        catch (FindFailed findFailed){
            try {
                spotifyScreen.find(playerPage.similar(SimilarityLevels.TEN_PERCENT.getSimilarityLevel()));
                return new PlayerScreen();
            }
            catch (FindFailed findFailed1) {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Checks what is the current screen the app is showing
     * @return The current screen app is showing i.e player or login
     */
    public static ScreenTypes getActiveScreen () {
        try {
            spotifyScreen.find(loginPage.similar(SimilarityLevels.SEVENTY_PERCENT.getSimilarityLevel()));
            return ScreenTypes.LOGIN;
        }
        catch (FindFailed findFailed){
            try {
                spotifyScreen.find(playerPage.similar(SimilarityLevels.TEN_PERCENT.getSimilarityLevel()));
                return ScreenTypes.PLAYER;
            }
            catch (FindFailed findFailed1) {
                return ScreenTypes.INVALID;
            }
        }
    }

    // Better to have a separate method for waiting.
    private static void wait (int waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }
}
