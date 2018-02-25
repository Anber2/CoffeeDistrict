package mawaqaajo.com.coffeedistrict.DataClasses;

/**
 * Created by HP on 8/6/2017.
 */

public class CoffeeShopData {

    private String CoffeeShopID;
    private String CoffeeShopName;

    public void setCoffeeShopID(String coffeeShopID) {
        CoffeeShopID = coffeeShopID;
    }

    public void setCoffeeShopName(String coffeeShopName) {
        CoffeeShopName = coffeeShopName;
    }

    public String getCoffeeShopID() {
        return CoffeeShopID;
    }

    public String getCoffeeShopName() {
        return CoffeeShopName;
    }

    public CoffeeShopData(String coffeeShopID, String coffeeShopName) {
        CoffeeShopID = coffeeShopID;
        CoffeeShopName = coffeeShopName;
    }
}
