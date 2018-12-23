/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Repositories.UserRepository;
import Repositories.ItemRepository;
import Entities.User;
import Entities.Item;
import Entities.Message;
import java.util.*;
import java.util.Scanner;

public class NormalUserController {

    Scanner input = new Scanner(System.in);

    public static boolean IsAdmin(String username) {
        String type = UserRepository.getType(username);
        if (type.equals("Admin")) {
            return true;
        } else {
            return false;
        }
    }

    public void SignUp(String username, String password, String phone) {

        User user = new User(username, password, phone);

        if (password.equals("admin")) {
            user.setType("Admin");
        } else {
            user.setType("Normal User");
        }

        UserRepository.Save(user);

        System.out.print(" --> You are Registerd Successfully...");
    }

    public String LogIn(String username, String password) {

        String userType = "none";
        if (UserRepository.Verify(username, password) == true) {
            if (IsAdmin(username)) {
                userType = "Admin";
            } else {
                userType = "Normal User";
            }
            System.out.println("\n --> Welcome " + username + "(" + userType + ")");
        }
        return userType;
    }

    public boolean isExist(String username) {
        boolean result = UserRepository.IsExist(username);
        return result;
    }

    public void postItem(String itemname, String username, String type, byte[] image) {
        Item item = new Item(itemname, username, type, image);
        ItemRepository.Save(item);
    }

    public static void SendMessage(String from, String to, String message) {
        if (UserRepository.IsExist(from) && UserRepository.IsExist(to)) {
            Message msg = new Message(from, to, message);
            UserRepository.sendMessage(msg);
        }
    }

    public static ArrayList<Message> get_All_Messages(String username) {
        ArrayList<Message> messages = UserRepository.getMessages(username);
        return messages;
    }
    public static void delete_All_Messages(String username){
        UserRepository.deleteMessages(username);
    }

}
