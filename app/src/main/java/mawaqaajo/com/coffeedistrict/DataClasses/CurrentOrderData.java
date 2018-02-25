package mawaqaajo.com.coffeedistrict.DataClasses;

/**
 * Created by HP on 8/9/2017.
 */

public class CurrentOrderData {

    private String CurrentOrderId;
    private String CurrentOrderNumber;
    private String CurrentOrderDare;
    private String CurrentOrderCafeName;
    private String CurrentOrderStatus;
    private String CurrentOrderTotal;
    private String CurrentOrderOrderTime;
    private String CurrentOrderPaymentMethod;
    private String CurrentOrderDriverName;

    public CurrentOrderData(String currentOrderId, String currentOrderNumber, String currentOrderDare, String currentOrderCafeName, String currentOrderStatus, String currentOrderTotal, String currentOrderOrderTime, String currentOrderPaymentMethod, String currentOrderDriverName) {
        CurrentOrderId = currentOrderId;
        CurrentOrderNumber = currentOrderNumber;
        CurrentOrderDare = currentOrderDare;
        CurrentOrderCafeName = currentOrderCafeName;
        CurrentOrderStatus = currentOrderStatus;
        CurrentOrderTotal = currentOrderTotal;
        CurrentOrderOrderTime = currentOrderOrderTime;
        CurrentOrderPaymentMethod = currentOrderPaymentMethod;
        CurrentOrderDriverName = currentOrderDriverName;
    }

    public void setCurrentOrderId(String currentOrderId) {
        CurrentOrderId = currentOrderId;
    }

    public void setCurrentOrderNumber(String currentOrderNumber) {
        CurrentOrderNumber = currentOrderNumber;
    }

    public void setCurrentOrderDare(String currentOrderDare) {
        CurrentOrderDare = currentOrderDare;
    }

    public void setCurrentOrderCafeName(String currentOrderCafeName) {
        CurrentOrderCafeName = currentOrderCafeName;
    }

    public void setCurrentOrderStatus(String currentOrderStatus) {
        CurrentOrderStatus = currentOrderStatus;
    }

    public void setCurrentOrderTotal(String currentOrderTotal) {
        CurrentOrderTotal = currentOrderTotal;
    }

    public void setCurrentOrderOrderTime(String currentOrderOrderTime) {
        CurrentOrderOrderTime = currentOrderOrderTime;
    }

    public void setCurrentOrderPaymentMethod(String currentOrderPaymentMethod) {
        CurrentOrderPaymentMethod = currentOrderPaymentMethod;
    }

    public void setCurrentOrderDriverName(String currentOrderDriverName) {
        CurrentOrderDriverName = currentOrderDriverName;
    }

    public String getCurrentOrderId() {
        return CurrentOrderId;
    }

    public String getCurrentOrderNumber() {
        return CurrentOrderNumber;
    }

    public String getCurrentOrderDare() {
        return CurrentOrderDare;
    }

    public String getCurrentOrderCafeName() {
        return CurrentOrderCafeName;
    }

    public String getCurrentOrderStatus() {
        return CurrentOrderStatus;
    }

    public String getCurrentOrderTotal() {
        return CurrentOrderTotal;
    }

    public String getCurrentOrderOrderTime() {
        return CurrentOrderOrderTime;
    }

    public String getCurrentOrderPaymentMethod() {
        return CurrentOrderPaymentMethod;
    }

    public String getCurrentOrderDriverName() {
        return CurrentOrderDriverName;
    }
}
