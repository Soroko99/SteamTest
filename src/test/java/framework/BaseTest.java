package framework;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest extends Browser {

    @BeforeTest
    public abstract void trashClean();

    @BeforeTest
    public void driverStart(){
        setup();
    }

    @AfterTest
    public void testCompletion(){
        driverClose();
    }
}
