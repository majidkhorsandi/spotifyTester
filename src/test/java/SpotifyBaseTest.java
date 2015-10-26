/**
 * Created by majid on 23/10/15.
 */

import Helpers.PropertiesHandler;
import org.sikuli.script.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;



public class SpotifyBaseTest {

    private static final int APP_STARTUP_TIME = 10000; // time in ms
    private static final String SPOTIFY_PATH_PROPERTY_NAME = "spotify_path";

    private Sikulix script;
    private static Process app;

    @BeforeTest
    public void setUp() throws InterruptedException, FindFailed {
        app = run();
        wait(APP_STARTUP_TIME);
    }

    @AfterTest
    public void tearDown() {
        stop();
    }

    private Process run() {
        try {
            return Runtime.getRuntime().exec(PropertiesHandler.getPropertyValue(SPOTIFY_PATH_PROPERTY_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void stop() {
        app.destroy();
    }

    private void wait (int waitTime) throws InterruptedException {
        Thread.sleep(waitTime);
    }

}
