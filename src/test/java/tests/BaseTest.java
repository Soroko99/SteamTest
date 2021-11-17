package tests;

import framework.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.SteamDownload;

public class BaseTest extends Browser {

    @BeforeTest
    public void driverStart(){
        setup();
    }

    @AfterTest
    public void testCompletion(){
        SteamDownload steamDownload = new SteamDownload();
        steamDownload.deleteSteamAfterTest();
        driverClose();
    }
}
