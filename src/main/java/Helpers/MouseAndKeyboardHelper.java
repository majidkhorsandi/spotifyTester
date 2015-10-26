package Helpers;

import org.sikuli.script.Button;
import org.sikuli.script.Region;

/**
 * Created by majid on 26/10/15.
 */
public class MouseAndKeyboardHelper {

    private static final int BACKSPACE_KEY_CODE = 8;

    /**
     * A helper method to simulate mouse triple click. This is specially use full to select all text in a texbox
     * @param regionToClick A region to click on
     */
    public static void mouseTripleClick (Region regionToClick) {
        for (int i=1; i<= 3; i++) {
            regionToClick.mouseDown(Button.LEFT);
            regionToClick.mouseUp(Button.LEFT);
        }
    }


    public static void backSpace (Region whereToType) {
        whereToType.keyDown(BACKSPACE_KEY_CODE);
        whereToType.keyUp(BACKSPACE_KEY_CODE);
    }




}
