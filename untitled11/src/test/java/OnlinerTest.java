import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OnlinerTest extends ConfigureClass {

    protected static String mainUrl = "https://www.onliner.by/";
    protected String catalogExpectedTitle = "Каталог Onlíner";
    protected String tvExpectedTitle = "Телевизор купить в Минске";
    protected String priceXpath = "//span[contains(@data-bind,'BYN')]";
    protected String screenSizeXpath = "//span[contains(@data-bind, 'description')]";
    protected String resolutionXpath = "//span[contains(@data-bind, 'description')]";
    protected String manufacturerXpath = "//span[starts-with(text(),'Телевизор')]";

    @Parameters({"manufacturer", "maximumPrice", "minScreenSize", "maxScreenSize", "resolution"})
    @Test
    public void enterCatalogPage(String manufacturer, String maximumPrice, String minScreenSize, String maxScreenSize, String resolution){
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        TVPage tvPage = new TVPage(driver, (JavascriptExecutor) driver);
        driver.get(mainUrl);

        mainPage.topNavigationChoice("Каталог");
        Assert.assertEquals(driver.getTitle(), catalogExpectedTitle);

        catalogPage.catalogSubsectionChoice("Электроника");
        catalogPage.electronicsSubsectionChoice("Телевидение");
        catalogPage.productChoice("Телевизоры");
        Assert.assertEquals(driver.getTitle(), tvExpectedTitle);

        tvPage.manufacturerFiltration(manufacturer);
        tvPage.priceFiltration("до", maximumPrice);
        tvPage.resolutionFiltration(resolution);
        tvPage.screenSizeFiltration("from", minScreenSize);
        tvPage.screenSizeFiltration("to", maxScreenSize);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tvPage.manufacturerValidation(tvPage.generatingTvCharacteristicsList(driver, manufacturerXpath), manufacturer);
        tvPage.maxPriceValidation(tvPage.generatingTvCharacteristicsList(driver, priceXpath), maximumPrice);
        tvPage.screenSizeValidation(tvPage.generatingTvCharacteristicsList(driver, screenSizeXpath), minScreenSize, maxScreenSize);
        tvPage.resolutionValidation(tvPage.generatingTvCharacteristicsList(driver, resolutionXpath), resolution);
    }
}
