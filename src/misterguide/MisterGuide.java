package misterguide;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import Entities.User;
import Entities.Message;
import Entities.Item;
import Controllers.NormalUserController;
import Controllers.AdminController;
import Controllers.ItemController;
import Repositories.ItemRepository;
import Repositories.UserRepository;
import java.util.ArrayList;

public class MisterGuide {

    public static NormalUserController normaluserCont = new NormalUserController();
    public static AdminController adminCont = new AdminController();
    public static ItemController itemCont = new ItemController();
    static Scanner input = new Scanner(System.in);

    public static void createNewTableUsers() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users "
                + "(username  varchar(100) PRIMARY KEY,"
                + " password  text,"
                + " phone     varchar(50),"
                + " type      varchar(100))";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table users created successfully");
    }

    public static void createNewTableItems() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS items "
                + "(id       INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " itemname  varchar(100),"
                + " username  varchar(100),"
                + " type      varchar(100),"
                + " image     BLOB,"
                + " FOREIGN KEY (username) REFERENCES users(username))";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table items created successfully");
    }
    
    public static void dropTableItems() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        // SQL statement for creating a new table
        String sql = "DROP TABLE items";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table items deleted successfully");
    }
    
    public static void createNewTableMessages() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS messages (sender VARCHAR(100), reciever varchar(100) , message text)";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
           System.out.println("Table messages created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public static void DropTableMessages() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        // SQL statement for creating a new table
        String sql = "DROP TABLE messages";

        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Table messages Deleted");
    }


    

    public static byte[] readFile(String file) {
        ByteArrayOutputStream bos = null;
        try {
            File f = new File(file);
            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[1024];
            bos = new ByteArrayOutputStream();
            for (int len; (len = fis.read(buffer)) != -1;) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e2) {
            System.err.println(e2.getMessage());
        }
        return bos != null ? bos.toByteArray() : null;
    }

    public static void main(String[] args) {
    }

}
