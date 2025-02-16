package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUitl {

    public static Connection getDbConnection(){
        Connection connection=null;
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement","root","Mth@$0034");

        }
        catch (ClassNotFoundException e){
            System.out.println(e);
        }
        catch (SQLException e) {
            System.out.println(e);
        }

        System.out.println("connection establish ");
        return connection;
    }

    public static void main(String[] args) {

    }
}
