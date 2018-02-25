package mawaqaajo.com.coffeedistrict.model;

/**
 * Created by HP on 12/28/2017.
 */

public class OffersModel {
    public String offerId;
    public String offerImage;

    public OffersModel(String offerId, String offerImage) {
        this.offerId = offerId;
        this.offerImage = offerImage;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferImage() {
        return offerImage;
    }

    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
    }
}
