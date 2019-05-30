import Application.PreRunOptionsApp;
import Application.SortingApplication;

import java.io.File;
/** Run this twice. Once to get the settings you want, and a second time to run it with those settings. */
public class Main {
    public static void main(String[] args) {
        if(new File("options.txt").exists() == false)
            PreRunOptionsApp.main(new String[0]);
        else
            SortingApplication.main(new String[0]);
    }
}

