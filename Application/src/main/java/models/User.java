package models;

/**
 * Created by sky_w on 4/17/2017.
 */
public class User {
    private int userID;
    private String userFirstName;
    private String userLastName;

    //Constructor for new User Objects that do not exist in the Database
    public User(String userFirstName, String userLastName) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    //Constructor for User Objects that exist in the Database

    public User(int userID, String userFirstName, String userLastName) {
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
