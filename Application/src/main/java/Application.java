import models.User;

/**
 * Created by sky_w on 4/17/2017.
 */
public class Application {
    public static void main(String []args){
        try {
            DBConnector dbConnector = new DBConnector();
            dbConnector.createUser(new User("luke", "Simmons"));
            System.out.print(dbConnector.getUsers().get(0).getUserFirstName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
