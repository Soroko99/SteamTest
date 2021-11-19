package steam.tests;

import framework.PropertyManager;
import org.testng.annotations.Test;
import steam.pages.ActionPage;
import steam.pages.GamePage;
import steam.pages.HomePage;
import steam.pages.SteamDownload;

public class SteamTest extends BaseTest {

    @Test
    public void start(){
        PropertyManager propertyManager = new PropertyManager();

        HomePage homePage = new HomePage();
        homePage.getLanguage();
        homePage.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "home_title"));
        homePage.mainLabelNavigation(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "main_page_top_navigation"));
        homePage.subsectionChoice(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "main_page_top_nav_subsection"));

        ActionPage actionPage = new ActionPage();
        actionPage.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "action_title"));
        actionPage.lookingForBiggestSale();
        //actionPage.ageCheck();

        GamePage gamePage = new GamePage();
        gamePage.isRightPageOpenedAssertion(gamePage.getCurrentSale());
        gamePage.installSteamBtnClick();

        SteamDownload steamDownload = new SteamDownload();
        steamDownload.isRightPageOpenedAssertion(propertyManager.getExactProperty(HomePage.currentLanguagePropertyPath, "steam_title"));
        steamDownload.downloadSteam();

    }
}
