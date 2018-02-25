package mawaqaajo.com.coffeedistrict.model;

/**
 * Created by HP on 12/31/2017.
 */

public class AddressListModule {

    String addressId;
    String areaId;
    String addressName;
    String addressBlock;
    String addressStreet;

    public AddressListModule(String addressId, String areaId, String addressName, String addressBlock, String addressStreet) {
        this.addressId = addressId;
        this.areaId = areaId;
        this.addressName = addressName;
        this.addressBlock = addressBlock;
        this.addressStreet = addressStreet;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressBlock() {
        return addressBlock;
    }

    public void setAddressBlock(String addressBlock) {
        this.addressBlock = addressBlock;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
}
