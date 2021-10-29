public class MainPage {

    public String topNavigationChoice(String name){
        return String.format("//ul[@class='b-main-navigation']//span[contains(text(), '%s')]", name);
    }

}
