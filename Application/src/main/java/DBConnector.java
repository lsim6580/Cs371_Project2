import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky_w on 4/17/2017.
 */
public class DBConnector {
    private Connection con;

    private PreparedStatement stmt;

    public DBConnector() throws Exception {
        String url = "jdbc:mysql://cs-371.cdzwjr1xplmp.us-east-1.rds.amazonaws.com:3306/Cs_371";

        String userID = "UMKCDatabase";

        String password = "Umkcdatabase";
        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (java.lang.ClassNotFoundException e) {

            System.out.println(e);

            System.exit(0);

        }


        con = DriverManager.getConnection(url, userID, password);
    }

    public void createUser(User user){
        String query = "INSERT INTO Users(userFirstName, userLastName) VALUES (?,?)";
        try{
            stmt = con.prepareStatement(query);
            stmt.setString(1, user.getUserFirstName());
            stmt.setString(2, user.getUserLastName());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<User> getUsers(){
        String query = "Select * From Users";
        List<User> users = new ArrayList<User>();
        try{

            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public void createStatus(Status status){
        String query = "INSERT INTO STATUS(statusName) VALUES (?)";
        try{
            stmt = con.prepareStatement(query);
            stmt.setString(1, status.getStatusName());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Status> getStatuses(){
        String query = "Select * From Status";
        List<Status> statuses = new ArrayList<Status>();
        try{

            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                statuses.add(new Status(rs.getInt(1), rs.getString(2)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return statuses;
    }

    public void createModerator(Moderator moderator){
        String query = "INSERT INTO MODERATOR(userID) VALUES (?)";
        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1, moderator.getUser().getUserID());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Moderator> getModerators(){
        String query = "Select * From Status";
        List<Moderator> moderators = new ArrayList<Moderator>();
        try{
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                moderators.add(new Moderator(new User(rs.getInt(1),rs.getString(2),rs.getString(3))));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return moderators;
    }

    public void createAdvertisement(Advertisement advertisement){
        String query = "INSERT INTO Advertisement(advertisementTitle, advertisementDetails, advertisementDataTime, price, userId," +
                "moderatorID, categoryID, statusID) VALUES (?,?,?,?,?,?,?,?)";
        try{
            stmt = con.prepareStatement(query);
            stmt.setString(1, advertisement.getAdvertisementTitle());
            stmt.setString(2, advertisement.getAdvertisementDetails());
            stmt.setDate(3, advertisement.getAdvertisementDateTime());
            stmt.setDouble(4, advertisement.getPrice());
            stmt.setInt(5, advertisement.getUser().getUserID());
            stmt.setInt(6, advertisement.getModerator().getUser().getUserID());
            stmt.setInt(7, advertisement.getCategory().getCategoryID());
            stmt.setInt(8, advertisement.getStatus().getStatusID());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Advertisement> getAdveritsementsByModerator(Moderator moderator){
        String query = "SELECT  * FROM Categories INNER JOIN" +
                "(SELECT advertisementID, advertisementTitle, advertisementDetails, advertisementDateTime," +
                " price, Moderator.userID, categoryID From Advertisements" +
                "INNER JOIN Moderators ON Advertisements.moderatorID = Moderators.UserID) AS S ON S.categoryID = Categories.categoryID" +
                "Where S.userID = ?";
        return getAdvertisement(query,moderator.getUser().getUserID());
    }

    public List<Advertisement> getAdvertisementsByUser(User user){
        String query = "SELECT  * FROM Categories" +
                "INNER JOIN (SELECT advertisementID, advertisementTitle, advertisementDetails, advertisementDateTime," +
                " price, categoryID, Users.userID From Advertisements" +
                "INNER JOIN Users ON Advertisements.userID = Users.UserID) AS S ON S.categoryID = Categories.categoryID" +
                "WHERE S.userID = ?";
        return getAdvertisement(query, user.getUserID());
    }

    private List<Advertisement> getAdvertisement(String query, int id){
        List<Advertisement> advertisements = new ArrayList<Advertisement>();

        try{
            stmt = con.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Category category;
            while (rs.next()) {
                Advertisement advertisement = new Advertisement(rs.getInt("advertisementID"), rs.getString("advertisementTitle"), rs.getString("advertisementDetails"),
                        rs.getDate("advertisementDateTime"), rs.getDouble("price"));
                advertisement.setCategory(new Category(rs.getString("categoryName")));
                advertisements.add(advertisement);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return advertisements;
    }
}
