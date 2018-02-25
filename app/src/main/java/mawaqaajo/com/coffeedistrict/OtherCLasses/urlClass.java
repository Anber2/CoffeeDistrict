package mawaqaajo.com.coffeedistrict.OtherCLasses;

/**
 * Created by HP on 8/1/2017.
 */

public class urlClass {
    public static String baseURL = "http://cofe.fintolog.com/";
    private static String ipHeader = "?ajaxRequest=1";

    public static String imageBaseURL = "http://cofe.fintolog.com";
    public static String getCofeShopListURL = baseURL + "shop?ajaxRequest=1";
    //
    public static String currentOrder = baseURL + "my-order?ajaxRequest=1&current=1";
    public static String CompletedOrders = baseURL + "my-order?ajaxRequest=1&complete=1";

    public static String getCofeCategoryWithItemsURL = baseURL + "shop/menuItem?ajaxRequest=1";
    public static String contactUSURL = baseURL + "contact_us/create";
    public static String aboutUSURL = baseURL + "about";
    public static String coffeeBlogUSURL = baseURL + "blog";
    public static String faqURL = baseURL + "faq" + ipHeader ;

    public static String addAddressURL=baseURL+"AddressCreate?ajaxRequest=1";

    public static String getlistOfAddress = baseURL+ "getlistOfAddress?users_id=";

    public static String getUserProfile = baseURL + "getUserProfil?user_id=";
    public static String updateUserProfile = baseURL + "update_user_profile";
    public static String contactUs = baseURL+ "contact_us_api";

    public static String scheduleDataAPI = baseURL+ "order/scheduleDataAPI?date=";

    public static String changePasswordURL = baseURL+ "resetPassword";
    //
    public static String registerURL = baseURL + "users" + ipHeader;
    public static String loginURL = baseURL + "login" + ipHeader;
    public static String recoverPasswordURL = baseURL + "recover" + ipHeader;
    public static String getAllAreaURL = baseURL + "getAllArea" + ipHeader;

    public static String cataring = baseURL + "catering_api?ajaxRequest=1";
    public static String getBlog = baseURL + "get_all_blog?ajaxRequest=1";
    public static String getBlogDetailsURL = baseURL + "blog";
    public static String offers = baseURL + "offer?ajaxRequest=1";



}
