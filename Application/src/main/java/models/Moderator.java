package models;

/**
 * Created by sky_w on 4/17/2017.
 */
public class Moderator {
    private User user;

    public Moderator(User user) {
        this.user = user;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
