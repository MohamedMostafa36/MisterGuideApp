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
import java.util.Scanner;
import java.util.*;

public class AdminController {

    Scanner input = new Scanner(System.in);

    public static ArrayList<User> get_All_Users() {
        ArrayList<User> users = UserRepository.getAll();
        return users;
    }

    public static void deleteUser(String username) {
        UserRepository.delete(username);
    }

    public void deleteItem(int id){
        ItemRepository.delete(id);
    }
}
