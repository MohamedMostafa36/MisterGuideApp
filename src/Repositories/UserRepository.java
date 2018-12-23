package Repositories;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import Entities.User;
import Entities.Message;
import Entities.Item;
import java.sql.ResultSet;
import java.util.*;

public class UserRepository {

    public UserRepository() {
    }

    public static void Save(User user) {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "INSERT INTO users (username,password,phone,type) VALUES(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getType());
            pstmt.executeUpdate();

            conn.commit();
            conn.close();
            System.out.println("\n --> User Added successfully...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void delete(String username) {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            Statement stmt = conn.createStatement();
            String sql = "DELETE from users where username = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);

            pstmt.executeUpdate();

            conn.commit();
            conn.close();
            System.out.println("\n --> User deleted successfully...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static boolean IsExist(String username) {
        boolean checker = false;
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select * from users where username = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("username").length() > 0) {
                    checker = true;
                }
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checker;
    }
    public static boolean Verify(String username, String password) {
        boolean found = false;
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select * from users where username = ? AND password = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            
            String name="",pass="";
            while (rs.next()) {
                name = rs.getString("username");
                pass = rs.getString("password");
            }
            if (name.equals(username) && pass.equals(password)){
                found = true;
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return found;
    }

    public static ArrayList<User> getAll() {

        ArrayList<User> users = new ArrayList();

        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select * from users";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //pstmt.setString(1, "Normal User");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("phone"));
                user.setType(rs.getString("type"));
                users.add(user);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public static String getType(String username) {

        String Type = "";
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select type from users where username = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setString(1, username);
            //
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Type = rs.getString("type");
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Type;
    }

    public static void sendMessage(Message msg) {

        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";
        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "INSERT INTO messages(sender,reciever,message) VALUES(?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, msg.getFrom());
            pstmt.setString(2, msg.getTo());
            pstmt.setString(3, msg.getMessage());
            pstmt.executeUpdate();

            conn.commit();
            conn.close();
            System.out.println("\n --> Message sent successfully...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Message> getMessages(String To) {

        ArrayList<Message> messages = new ArrayList();

        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select * from messages where reciever = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, To);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Message msg = new Message(rs.getString("sender"),rs.getString("reciever"),rs.getString("message"));
                messages.add(msg);
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messages;
    }
    public static void deleteMessages(String username) {

        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/D0ocuments/NetBeansProjects/MisterGuide/GuideApp.db";
        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "delete from messages where reciever = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, username);
            pstmt.executeUpdate();

            conn.commit();
            conn.close();
            System.out.println("\n --> All Messages deleted successfully...");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
