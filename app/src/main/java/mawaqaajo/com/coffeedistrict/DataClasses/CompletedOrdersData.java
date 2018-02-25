package mawaqaajo.com.coffeedistrict.DataClasses;

/**
 * Created by HP on 8/10/2017.
 */

public class CompletedOrdersData {

    private String CompletedOrdersId;
    private String CompletedOrdersStatus;
    private String CompletedOrdersDate;
    private String CompletedOrdersAddress;
    private String CompletedOrdersPrice;

    public CompletedOrdersData(String completedOrdersId, String completedOrdersStatus, String completedOrdersDate, String completedOrdersAddress, String completedOrdersPrice) {
        CompletedOrdersId = completedOrdersId;
        CompletedOrdersStatus = completedOrdersStatus;
        CompletedOrdersDate = completedOrdersDate;
        CompletedOrdersAddress = completedOrdersAddress;
        CompletedOrdersPrice = completedOrdersPrice;
    }

    public String getCompletedOrdersId() {
        return CompletedOrdersId;
    }

    public void setCompletedOrdersId(String completedOrdersId) {
        CompletedOrdersId = completedOrdersId;
    }

    public String getCompletedOrdersStatus() {
        return CompletedOrdersStatus;
    }

    public void setCompletedOrdersStatus(String completedOrdersStatus) {
        CompletedOrdersStatus = completedOrdersStatus;
    }

    public String getCompletedOrdersDate() {
        return CompletedOrdersDate;
    }

    public void setCompletedOrdersDate(String completedOrdersDate) {
        CompletedOrdersDate = completedOrdersDate;
    }

    public String getCompletedOrdersAddress() {
        return CompletedOrdersAddress;
    }

    public void setCompletedOrdersAddress(String completedOrdersAddress) {
        CompletedOrdersAddress = completedOrdersAddress;
    }

    public String getCompletedOrdersPrice() {
        return CompletedOrdersPrice;
    }

    public void setCompletedOrdersPrice(String completedOrdersPrice) {
        CompletedOrdersPrice = completedOrdersPrice;
    }
}
