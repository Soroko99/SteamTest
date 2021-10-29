public class CatalogPage {

    public String catalogNavigation(String name)
    {
        return String.format("//ul//span[contains(text(),'%s')]//ancestor::li", name);
    }

    public String electronicsNavigation(String name)
    {
        return String.format("//div[contains(text(), '%s')]", name);
    }

    public String tvNavigation(String name)
    {
        return String.format("//span[contains(text(),'%s')]//ancestor::a", name);
    }
}
