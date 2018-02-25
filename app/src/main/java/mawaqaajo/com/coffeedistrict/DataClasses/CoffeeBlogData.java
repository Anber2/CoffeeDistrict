package mawaqaajo.com.coffeedistrict.DataClasses;

/**
 * Created by HP on 8/7/2017.
 */

public class CoffeeBlogData {
    private String CoffeeBlogID;
    private String CoffeeBlogImage;
    private String CoffeeBlogDescription;
    private String CoffeeBlogUrl;

    public CoffeeBlogData(String coffeeBlogID, String coffeeBlogImage, String coffeeBlogDescription, String coffeeBlogUrl) {
        CoffeeBlogID = coffeeBlogID;
        CoffeeBlogImage = coffeeBlogImage;
        CoffeeBlogDescription = coffeeBlogDescription;
        CoffeeBlogUrl = coffeeBlogUrl;
    }

    public void setCoffeeBlogID(String coffeeBlogID) {
        CoffeeBlogID = coffeeBlogID;
    }

    public void setCoffeeBlogImage(String coffeeBlogImage) {
        CoffeeBlogImage = coffeeBlogImage;
    }

    public void setCoffeeBlogDescription(String coffeeBlogDescription) {
        CoffeeBlogDescription = coffeeBlogDescription;
    }

    public void setCoffeeBlogUrl(String coffeeBlogUrl) {
        CoffeeBlogUrl = coffeeBlogUrl;
    }

    public String getCoffeeBlogID() {
        return CoffeeBlogID;
    }

    public String getCoffeeBlogImage() {
        return CoffeeBlogImage;
    }

    public String getCoffeeBlogDescription() {
        return CoffeeBlogDescription;
    }

    public String getCoffeeBlogUrl() {
        return CoffeeBlogUrl;
    }
}
