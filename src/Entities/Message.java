/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

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
import Entities.Item;
import Controllers.NormalUserController;
import Controllers.AdminController;
import Controllers.ItemController;
import Repositories.ItemRepository;
import Repositories.UserRepository;
import java.util.ArrayList;

public class Message {
    private String from , to, message;
    
    public Message(String from , String to , String message){
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
    
}
