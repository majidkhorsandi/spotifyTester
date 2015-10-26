package ScreenObjects;

import Helpers.PropertiesHandler;
import Helpers.SimilarityLevels;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

/**
 * Created by majid on 24/10/15.
 */
public class PlayerScreen {

    private Pattern mainPage;
    private Pattern searchBox;
    private Pattern showResults;
    private Pattern clearSearchBox;
    private Pattern foundTrackIndicator;
    private Pattern resultsFound;
    private Pattern foundTrackPlaying;
    private Pattern menuButton;
    private Pattern logoutButton;
    private Pattern addToYourMusicButton;
    private Pattern yourSongButton;
    private Pattern tomorrowNeverDies;
    private Pattern menuBar;
    private Region mainScreen;

    public PlayerScreen() throws FindFailed {
        mainPage = new Pattern(PropertiesHandler.getPatternFile("mainPage.png"));
        searchBox = new Pattern(PropertiesHandler.getPatternFile("searchBox.png"));
        showResults = new Pattern(PropertiesHandler.getPatternFile("showResults.png"));
        resultsFound = new Pattern(PropertiesHandler.getPatternFile("showResultsFor.png"));
        clearSearchBox = new Pattern(PropertiesHandler.getPatternFile("clearSearchBox.png"));
        foundTrackIndicator = new Pattern(PropertiesHandler.getPatternFile("whereDoYouGo.png"));
        foundTrackPlaying = new Pattern(PropertiesHandler.getPatternFile("foundTrackPlaying.png"));
        menuButton = new Pattern (PropertiesHandler.getPatternFile("menu.png"));
        logoutButton = new Pattern(PropertiesHandler.getPatternFile("logout.png"));
        addToYourMusicButton = new Pattern(PropertiesHandler.getPatternFile("addToYourMusic.png"));
        yourSongButton = new Pattern(PropertiesHandler.getPatternFile("yourSong.png"));
        tomorrowNeverDies = new Pattern(PropertiesHandler.getPatternFile("tomorrowNeverDiesInList.png"));
        menuBar = new Pattern(PropertiesHandler.getPatternFile("menuBar.png"));
        mainScreen = new Screen().wait(mainPage.similar(SimilarityLevels.TEN_PERCENT.getSimilarityLevel()));
        clickToGetFocus();
    }

    public boolean search (String whatToSearch) throws FindFailed {
        clearSearchBox();
        fillInSearchBox(whatToSearch);
        return matchSearchPattern(resultsFound);
    }

    private void clearSearchBox () throws FindFailed {
        try {
            mainScreen.click(clearSearchBox);
        }
        catch (FindFailed findFailed) {
           return; // Search Box delete button is not found, so assume it is already clean.
        }
    }

    private void fillInSearchBox (String whatToSearch) throws FindFailed {
        mainScreen.click(searchBox);
        mainScreen.type(searchBox, whatToSearch);
        mainScreen.click(showResults);
    }

    private boolean matchSearchPattern (Pattern patternToSearch) {
        try {
            mainScreen.wait(patternToSearch.similar(SimilarityLevels.NINETY_PERCENT.getSimilarityLevel()));
            return true;
        }
        catch (FindFailed findFailed) {
            return false;
        }
    }

    public boolean playAndVerify (String trackName) throws FindFailed {
        if (search(trackName)) {
            mainScreen.doubleClick(foundTrackIndicator);
        }
        return matchSearchPattern(foundTrackPlaying);
    }

    public void addToYourMusic (String trackName) throws FindFailed {
        if (search(trackName)) {
            mainScreen.click(addToYourMusicButton.similar((float) 0.9));
        }
    }

    public void logout () throws FindFailed {
        mainScreen.click(menuButton);
        mainScreen.wait(logoutButton);
        mainScreen.click(logoutButton);
    }

    public boolean checkIfTrackIsInYourSong() throws FindFailed {
        mainScreen.click(yourSongButton);
        try {
            mainScreen.wait(tomorrowNeverDies);
            return true;
        }
        catch (FindFailed findFailed) {
            return false;
        }
    }

    // This method is sometimes needed to bring the focus back to Spotify's player window
    private void clickToGetFocus () throws FindFailed {
        mainScreen.click(menuBar.similar(SimilarityLevels.EIGHTY_PERCENT.getSimilarityLevel()));
    }


}
