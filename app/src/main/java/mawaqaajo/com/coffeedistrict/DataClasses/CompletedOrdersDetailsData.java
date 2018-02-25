package mawaqaajo.com.coffeedistrict.DataClasses;

/**
 * Created by HP on 8/10/2017.
 */

public class CompletedOrdersDetailsData {

    private String CompletedOrdersDetailsOrderItemId;
    private String CompletedOrdersDetailsOrderItemName;
    private String CompletedOrdersDetailsOrderItemDescription;
    private String CompletedOrdersDetailsOrderItemPrice;

    public CompletedOrdersDetailsData(String completedOrdersDetailsOrderItemId, String completedOrdersDetailsOrderItemName, String completedOrdersDetailsOrderItemDescription, String completedOrdersDetailsOrderItemPrice) {
        CompletedOrdersDetailsOrderItemId = completedOrdersDetailsOrderItemId;
        CompletedOrdersDetailsOrderItemName = completedOrdersDetailsOrderItemName;
        CompletedOrdersDetailsOrderItemDescription = completedOrdersDetailsOrderItemDescription;
        CompletedOrdersDetailsOrderItemPrice = completedOrdersDetailsOrderItemPrice;
    }

    public String getCompletedOrdersDetailsOrderItemId() {
        return CompletedOrdersDetailsOrderItemId;
    }

    public void setCompletedOrdersDetailsOrderItemId(String completedOrdersDetailsOrderItemId) {
        CompletedOrdersDetailsOrderItemId = completedOrdersDetailsOrderItemId;
    }

    public String getCompletedOrdersDetailsOrderItemName() {
        return CompletedOrdersDetailsOrderItemName;
    }

    public void setCompletedOrdersDetailsOrderItemName(String completedOrdersDetailsOrderItemName) {
        CompletedOrdersDetailsOrderItemName = completedOrdersDetailsOrderItemName;
    }

    public String getCompletedOrdersDetailsOrderItemDescription() {
        return CompletedOrdersDetailsOrderItemDescription;
    }

    public void setCompletedOrdersDetailsOrderItemDescription(String completedOrdersDetailsOrderItemDescription) {
        CompletedOrdersDetailsOrderItemDescription = completedOrdersDetailsOrderItemDescription;
    }

    public String getCompletedOrdersDetailsOrderItemPrice() {
        return CompletedOrdersDetailsOrderItemPrice;
    }

    public void setCompletedOrdersDetailsOrderItemPrice(String completedOrdersDetailsOrderItemPrice) {
        CompletedOrdersDetailsOrderItemPrice = completedOrdersDetailsOrderItemPrice;
    }
}
