package models;

import java.sql.Date;

/**
 * Created by sky_w on 4/17/2017.
 */
public class Advertisement {
    private int advertisementID;
    private String advertisementTitle;
    private String advertisementDetails;
    private Date advertisementDateTime;
    private User user;
    private double price;
    private Moderator moderator;
    private Category category;
    private Status status;

    public Advertisement(int advertisementID, String advertisementTitle, String advertisementDetails, Date advertisementDateTime, double price) {
        this.advertisementID = advertisementID;
        this.advertisementTitle = advertisementTitle;
        this.advertisementDetails = advertisementDetails;
        this.advertisementDateTime = advertisementDateTime;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAdvertisementID() {
        return advertisementID;
    }

    public void setAdvertisementID(int advertisementID) {
        this.advertisementID = advertisementID;
    }

    public String getAdvertisementTitle() {
        return advertisementTitle;
    }

    public void setAdvertisementTitle(String advertisementTitle) {
        this.advertisementTitle = advertisementTitle;
    }

    public String getAdvertisementDetails() {
        return advertisementDetails;
    }

    public void setAdvertisementDetails(String advertisementDetails) {
        this.advertisementDetails = advertisementDetails;
    }

    public Date getAdvertisementDateTime() {
        return advertisementDateTime;
    }

    public void setAdvertisementDateTime(Date advertisementDateTime) {
        this.advertisementDateTime = advertisementDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Moderator getModerator() {
        return moderator;
    }

    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
