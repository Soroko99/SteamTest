package steam.tests;

import framework.Browser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import steam.pages.SteamDownload;

public class BaseTest extends Browser {

    @BeforeTest
    public void driverStart(){
        setup();
        SteamDownload steamDownload = new SteamDownload();
        steamDownload.deleteSteamAfterTest();
    }

    @AfterTest
    public void testCompletion(){

        driverClose();
    }
}
