package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest extends Browser {

    @BeforeTest (description = "removing SteamSetup.exe")
    public abstract void trashClean();

    @BeforeTest (description = "opening chosen browser")
    public void driverStart(){
        setup();
    }

    @AfterTest (alwaysRun = true)
    public void testCompletion(){
        driverClose();
    }
}
