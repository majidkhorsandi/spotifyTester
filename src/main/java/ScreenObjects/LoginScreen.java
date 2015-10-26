package ScreenObjects;

import Helpers.MouseAndKeyboardHelper;
import Helpers.PropertiesHandler;
import Helpers.SimilarityLevels;
import org.sikuli.script.*;

import java.util.Iterator;

/**
 * Created by majid on 23/10/15.
 */
public class LoginScreen {

    private static final int WAIT_TIME_FOR_LOGIN = 2000; // time in ms.


    private Pattern usernameTextBox;
    private Pattern passwordTextBox;
    private Pattern loginPage;
    private Pattern loginButton;
    private Pattern loginErrorMessage;
    private Pattern loginTextBoxes;
    private Region spotifyScreen;

    public LoginScreen() throws FindFailed {
        loginButton =  new Pattern(PropertiesHandler.getPatternFile("loginButton.png"));
        loginErrorMessage = new Pattern(PropertiesHandler.getPatternFile("loginErrBox.png"));
        usernameTextBox = new Pattern (PropertiesHandler.getPatternFile("txtUsername.png"));
        passwordTextBox = new Pattern (PropertiesHandler.getPatternFile("txtPassword.png"));
        loginPage = new Pattern (PropertiesHandler.getPatternFile("loginPage.png"));
        loginTextBoxes = new Pattern (PropertiesHandler.getPatternFile("generalTextBox.png"));
        spotifyScreen = new Screen().wait(loginPage.similar(SimilarityLevels.SEVENTY_PERCENT.getSimilarityLevel()));
    }

    /**
     * Cleans username and password text boxes first. Then types in the given username and password
     *  and presses the login button.
     * @param username
     * @param password
     * @throws FindFailed
     */
    public void login(String username, String password) throws FindFailed {
        clearLoginTextBoxes();
        spotifyScreen.type(usernameTextBox.similar(SimilarityLevels.EIGHTY_PERCENT.getSimilarityLevel()), username);
        spotifyScreen.type(passwordTextBox.similar(SimilarityLevels.EIGHTY_PERCENT.getSimilarityLevel()), password);
        spotifyScreen.click(loginButton);
    }

    /**
     * Verifies if the login error box is present on the login screen.
     * @return True if the login error box is visible and false if its not shown.
     */
    public boolean isLoginErrorVisible () {
        try {
            spotifyScreen.wait(loginErrorMessage, WAIT_TIME_FOR_LOGIN);
            return true;
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            return false;
        }
    }

    // In case text boxes on login window are not empty, we need to clean them first
    private void clearLoginTextBoxes() throws FindFailed {
        spotifyScreen.findAll(loginTextBoxes);
        for (Iterator<Match> textBoxes = spotifyScreen.getLastMatches(); textBoxes.hasNext();) {
            Match box = textBoxes.next();
            spotifyScreen.click(box);
            MouseAndKeyboardHelper.mouseTripleClick(spotifyScreen);
            MouseAndKeyboardHelper.backSpace(spotifyScreen);
        }
    }


}
