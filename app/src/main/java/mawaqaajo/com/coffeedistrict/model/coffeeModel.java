package mawaqaajo.com.coffeedistrict.model;

/**
 * Created by Ayadi on 8/7/2017.
 */

public class coffeeModel {
    private String id;
    private String Name;
    private String email;
    private String telephone;
    private String status;
    private String working_hour_from;
    private String working_hour_to;
    private String rating;
    private String website;
    private String img;
    private String longt;
    private String lat;
    private String address;

    public coffeeModel(String id, String name, String email, String telephone, String status, String working_hour_from, String working_hour_to, String rating, String website, String img, String longt, String lat, String address) {
        this.setId(id);
        setName(name);
        this.setEmail(email);
        this.setTelephone(telephone);
        this.setStatus(status);
        this.setWorking_hour_from(working_hour_from);
        this.setWorking_hour_to(working_hour_to);
        this.setRating(rating);
        this.setWebsite(website);
        this.setImg(img);
        this.setLongt(longt);
        this.setLat(lat);
        this.setAddress(address);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorking_hour_from() {
        return working_hour_from;
    }

    public void setWorking_hour_from(String working_hour_from) {
        this.working_hour_from = working_hour_from;
    }

    public String getWorking_hour_to() {
        return working_hour_to;
    }

    public void setWorking_hour_to(String working_hour_to) {
        this.working_hour_to = working_hour_to;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLongt() {
        return longt;
    }

    public void setLongt(String longt) {
        this.longt = longt;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
