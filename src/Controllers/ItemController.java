/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Item;
import Repositories.ItemRepository;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Scanner;
import Controllers.NormalUserController;
import Controllers.AdminController;
import Controllers.ItemController;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import Entities.User;
import Entities.Item;
import Repositories.ItemRepository;
import Repositories.UserRepository;
import java.util.Scanner;

public class ItemController {

    public static ArrayList<Item> Get_All_Items() {
        ArrayList<Item> items = ItemRepository.getAll();
        return items;
    }

    public static void Add_Item(Item item) {
        ItemRepository.Save(item);
    }
    
    public static void deleteItem(int id) {
        ItemRepository.delete(id);
    }

}
