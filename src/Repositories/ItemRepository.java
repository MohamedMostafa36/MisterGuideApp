/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import Entities.User;
import Entities.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ItemRepository {

    public static void Save(Item item) {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "INSERT INTO items (itemname,username,type,image) VALUES(?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, item.getItemname());
            pstmt.setString(2, item.getUsername());
            pstmt.setString(3, item.getType());
            pstmt.setBytes(4, item.getImage());
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n --> Item Added successfully...");
    }

    public static void delete(int id) {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            Statement stmt = conn.createStatement();
            String sql = "DELETE from items where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            conn.commit();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n --> Item deleted successfully...");
    }
    public static ArrayList<Item> getAll() {

        ArrayList<Item> items = new ArrayList();

        String url = "jdbc:sqlite:C:/Users/MohamedMostafa/Documents/NetBeansProjects/MisterGuide/GuideApp.db";

        try {

            Connection conn = DriverManager.getConnection(url);
            //System.out.println("Database opened successfully");

            String sql = "select * from items";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            //pstmt.setString(1, "Normal User");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Item item = new Item(rs.getString("itemname"),rs.getString("username"), rs.getString("type"), rs.getBytes("image"));
                item.setId(rs.getInt("id"));
                items.add(item);
                //System.out.println("\n Cr : " + rs.getInt("id"));
            }

            
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

}
