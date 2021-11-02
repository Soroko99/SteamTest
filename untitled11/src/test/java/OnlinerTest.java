import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OnlinerTest extends ConfigureClass {

    protected String manufacturerXpath = "//span[starts-with(text(),'Телевизор')]";
    protected String priceXpath = "//span[contains(@data-bind,'BYN')]";
    protected String screenSizeXpath = "//span[contains(@data-bind, 'description')]";
    protected String resolutionXpath = "//span[contains(@data-bind, 'description')]";

    @Parameters({"manufacturer", "maximumPrice", "minScreenSize", "maxScreenSize", "resolution"})
    @Test
    public void test(String manufacturer, String maximumPrice, String minScreenSize, String maxScreenSize, String resolution){
        MainPage mainPage = new MainPage(driver);
        CatalogPage catalogPage = new CatalogPage(driver);
        TVPage tvPage = new TVPage(driver, (JavascriptExecutor) driver);

        mainPage.topNavigationChoice("Каталог");
        mainPage.catalogTitleAssertion();

        catalogPage.catalogSubsectionChoice("Электроника");
        catalogPage.electronicsSubsectionChoice("Телевидение");
        catalogPage.productChoice("Телевизоры");
        catalogPage.tvTitleAssertion();

        tvPage.manufacturerFiltration(manufacturer);
        tvPage.priceFiltration("до", maximumPrice);
        tvPage.resolutionFiltration(resolution);
        tvPage.screenSizeFiltration("from", minScreenSize);
        tvPage.screenSizeFiltration("to", maxScreenSize);

        sleep();

        tvPage.manufacturerValidation(tvPage.generatingTvCharacteristicsList(driver, manufacturerXpath), manufacturer);
        tvPage.maxPriceValidation(tvPage.generatingTvCharacteristicsList(driver, priceXpath), maximumPrice);
        tvPage.screenSizeValidation(tvPage.generatingTvCharacteristicsList(driver, screenSizeXpath), minScreenSize, maxScreenSize);
        tvPage.resolutionValidation(tvPage.generatingTvCharacteristicsList(driver, resolutionXpath), resolution);
    }
}
