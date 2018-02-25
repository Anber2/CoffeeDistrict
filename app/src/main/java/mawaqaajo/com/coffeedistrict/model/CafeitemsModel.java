package mawaqaajo.com.coffeedistrict.model;

/**
 * Created by HP on 8/9/2017.
 */

public class CafeitemsModel {
    private String idItem;
    private String menueSectionItem;
    private String category_id;
    private String quantity;
    private String imageItem;
    private String nameItem;
    private String descItem;
    private String priceItem;
    private String shortDescItem;
    private String status;
    private String rating;

    public CafeitemsModel(String idItem, String menueSectionItem, String category_id, String quantity, String imageItem, String nameItem, String descItem, String priceItem, String shortDescItem, String status, String rating) {
        this.setIdItem(idItem);
        this.setMenueSectionItem(menueSectionItem);
        this.setCategory_id(category_id);
        this.setQuantity(quantity);
        this.setImageItem(imageItem);
        this.setNameItem(nameItem);
        this.setDescItem(descItem);
        this.setPriceItem(priceItem);
        this.setShortDescItem(shortDescItem);
        this.setStatus(status);
        this.setRating(rating);
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public String getMenueSectionItem() {
        return menueSectionItem;
    }

    public void setMenueSectionItem(String menueSectionItem) {
        this.menueSectionItem = menueSectionItem;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageItem() {
        return imageItem;
    }

    public void setImageItem(String imageItem) {
        this.imageItem = imageItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public String getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem;
    }

    public String getShortDescItem() {
        return shortDescItem;
    }

    public void setShortDescItem(String shortDescItem) {
        this.shortDescItem = shortDescItem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
