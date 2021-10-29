import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class OnlinerTest {
    protected WebDriverWait waiter;
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions actions;
    protected final String url = "https://www.onliner.by/";
    Wait<WebDriver> wait;
    protected Select selectSizeFrom;
    protected Select selectSizeTo;

    private String catalogExpectedTitle = "Каталог Onlíner";
    private String tvExpectedTitle = "Телевизор купить в Минске";

    protected MainPage mainPage = new MainPage();
    protected CatalogPage catalogPage = new CatalogPage();
    protected TVPage tvPage = new TVPage();

    private String priceXpath = "//span[contains(@data-bind,'BYN')]";
    private String screenSizeXpath = "//span[contains(@data-bind, 'description')]";
    private String resolutionXpath = "//span[contains(@data-bind, 'description')]";
    private String manufacturerXpath = "//span[starts-with(text(),'Телевизор')]";

    private String topNavChoice = "Каталог";
    private String catalogChoice = "Электроника";
    private String electronicsChoice = "Телевидение";
    private String tvChoice = "Телевизоры";

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chr .driver", "chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        waiter = new WebDriverWait(driver, 5);
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver);


    }

    @BeforeTest
    public void profileSetup() {
        driver.manage().window().maximize();

    }

    @BeforeClass
    public void appSetup() {
        driver.get(url);
    }

    @Test
    public void enterCatalogPage() {
        driver.findElement(By.xpath(mainPage.topNavigationChoice(topNavChoice))).click();
        Assert.assertEquals(driver.getTitle(), catalogExpectedTitle);
    }

    @Test
    public void enterTvPage() {
        driver.findElement(By.xpath(catalogPage.catalogNavigation(catalogChoice))).click();
        actions.moveToElement(driver.findElement(By.xpath(catalogPage.electronicsNavigation(electronicsChoice)))).build().perform();
        driver.findElement(By.xpath(catalogPage.tvNavigation(tvChoice))).click();
        Assert.assertEquals(driver.getTitle(), tvExpectedTitle);
    }

    @Parameters({"manufacturer", "maximumPrice", "minScreenSize", "maxScreenSize", "resolution"})
    @Test
    public void tvPageActivities(String manufacturer, String maximumPrice, String minScreenSize, String maxScreenSize, String resolution) throws InterruptedException {
        driver.findElement(By.xpath(tvPage.getManufacturerChoice(manufacturer).toLowerCase(Locale.ROOT))).click();
        driver.findElement(By.xpath(tvPage.getPrice("до"))).sendKeys(maximumPrice);
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(tvPage.getResolution(resolution))));
        selectSizeFrom = new Select(driver.findElement(By.xpath(tvPage.getScreenSize("from"))));
        selectSizeTo = new Select(driver.findElement(By.xpath(tvPage.getScreenSize("to"))));
        selectSizeFrom.selectByValue(Integer.toString(Integer.parseInt(minScreenSize) * 10));
        selectSizeTo.selectByValue(Integer.toString(Integer.parseInt(maxScreenSize) * 10));

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
//        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@data-bind,'minPrice')]")));
        
    }

    @Parameters({"maximumPrice"})
    @Test
    public void tvPriceTest(String maximumPrice) {
        for (String s : tvPage.generatingTvList(driver, priceXpath)) {
            Assert.assertTrue(Double.parseDouble(s.split(" ")[0].replaceAll(",", "."))
                    <= Double.parseDouble(maximumPrice.replaceAll(",", ".")));
            System.out.println(s);
        }
    }

    @Parameters({"minScreenSize", "maxScreenSize"})
    @Test
    public void screenSizeTest(String minScreenSize, String maxScreenSize) {
        for (String s : tvPage.generatingTvList(driver, screenSizeXpath)) {
            Assert.assertTrue(Integer.parseInt(s.substring(0, 2)) >= Integer.parseInt(minScreenSize)
                    && Integer.parseInt(s.substring(0, 2)) <= Integer.parseInt(maxScreenSize));
            System.out.println(s);
        }
    }

    @Parameters({"resolution"})
    @Test
    public void resolutionTest(String resolution) {
        for (String s : tvPage.generatingTvList(driver, resolutionXpath)) {
            Assert.assertTrue(s.contains(resolution));
            System.out.println(s);
        }
    }

    @Parameters({"manufacturer"})
    @Test
    public void manufacturerTest(String manufacturer) {
        for (String s : tvPage.generatingTvList(driver, manufacturerXpath)) {
            Assert.assertTrue(s.contains(manufacturer));
            System.out.println(s);
        }
    }

    @AfterSuite
    public void driverClose() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
