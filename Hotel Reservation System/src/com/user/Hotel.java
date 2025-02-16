package com.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel  {
    private String name;
    private List<Room> list;

    public Hotel(String name){
        this.name=name;
        this.list=new ArrayList<>();

    }
    Hotel(){}


public void validCustomer(Customer customer) throws IllegalCustomerException, InvalidNameException, InvalidEmailException, InvalidMobileNumberException {
        if(customer==null){
            throw new IllegalCustomerException("Customer can not be null");
        }

        validName(customer.getName());
        validEmail(customer.getEmail());
        validNumber(customer.getPhoneNumber());

}
public void validName(String name) throws InvalidNameException{
        if(name.trim().length()<3){
            throw new InvalidNameException("Customer name must be at least three character ");
        }

}
public void validEmail(String email) throws InvalidEmailException {
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    if (!email.matches(emailRegex)) {
        throw new InvalidEmailException("Invalid email format.");
    }

}
public void validNumber(long number) throws InvalidMobileNumberException {
        if(String.valueOf(number).length()!=10){
            throw new InvalidMobileNumberException("Mobile number should be 10 digit");
        }
}

    public void bookRoom(Room room,Customer customer) {

        try{
            validCustomer(customer);
            if(!isBooked(room.getId())){
                System.out.println("Room is already booked  with this id :  "+room.getId());

            }
            else {

                room.book(customer);
                list.add(room);
                System.out.println("Your booking is successful for type  : " + room.getRoomType() + " and room number is :  " + room.getRoomNumber() + " and price is :  " + room.getPrice());


            }
        }
        catch (InvalidMobileNumberException | InvalidNameException | InvalidEmailException | IllegalCustomerException e){
            System.err.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }



    }


    public   boolean searchRoom(String category){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        boolean found=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");
            String query = "select * from hotelRoom where roomType =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                found=true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return found;

    }
    public static boolean isRoomAvailable(String roomType) {
        boolean available = false;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");

            // Query to check if any room of the specified type is available (not booked)
            String query = "SELECT * FROM hotelRoom WHERE roomType = ? " +
                    "AND roomNumber NOT IN (SELECT roomNumber FROM booking WHERE status = 'booked')";
            ps = connection.prepareStatement(query);
            ps.setString(1, roomType);  // Set room type (e.g., single)

            rs = ps.executeQuery();
            if (rs.next()) {
                available = true;  // Room is available
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error during availability check: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

        return available;  // Return true if room of the specified type is available
    }



    public   boolean searchRoomInBookingTable(int roomNumber){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        boolean found=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "root");
            String query = "select * from booking where roomType =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(2, roomNumber);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                found=true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return found;

    }



//    public void bookRoom(Room room,Customer customer) {
//
//        try{
//            validCustomer(customer);
//
//            if(room.getRoomType().equals("single")){
//
//                System.out.println("Your booking is successful for type  : " + room.getRoomType() + " and room number is :  " + room.getRoomNumber() + " and price is :  " +5000);
//
//
//            }
//        }
//        catch (InvalidMobileNumberException | InvalidNameException | InvalidEmailException | IllegalCustomerException e){
//            System.err.println(e);
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//
//
//
//    }


//    public boolean isBooked(int id) {
//        for (Room room : list) {
//            if (room.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean isBooked(int id) {
        boolean booked = false;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

//            for oracle
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            establish a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");
            String query = "select count(*) from rooms where id =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

//
            rs = preparedStatement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                booked = true;
            }


        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }

            return booked;
        }
    }

    public boolean isCancel(int id) {
        for (Room room : list) {
            if (room.getId() == id) {
                list.remove(room);
                room.isCancel();
                return true;

            }
        }
            return false;


    }

        public void getRoom(){
            Connection connection=null;
            Statement statement=null;
            ResultSet rs=null;
            try{
//            load and register mysql driver
                Class.forName("com.mysql.cj.jdbc.Driver");

//            for oracle
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            establish a connection
                connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement","root","Mth@$0034");

                statement=connection.createStatement();
                String query="select * from rooms ; ";

//
                rs= statement.executeQuery(query);

                while(rs.next()){
                    System.out.println(rs.getInt(1)+ "\t"+ rs.getString(2)+" \t"+rs.getInt(3)+"\t"+rs.getDouble(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getLong(8));
                }

            }
            catch (ClassNotFoundException e){
                System.out.println(e);
            }
            catch (Exception e){
                System.out.println(e);
            }

    }


    public void getRoomByCategory(String category){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Load and register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");

            // Use PreparedStatement instead of Statement
            String query = "SELECT * FROM rooms WHERE roomType = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category);

            rs = preparedStatement.executeQuery();

            if (!rs.isBeforeFirst()) {  // No records found
                System.err.println("No rooms found for category: " + category);
                return;
            }
            // Print column headers
            System.out.println("ID\tRoom Type\tRoom Number\tPrice\tName\tEmail\tAddress\tPhone");



            while(rs.next()){
                System.out.println(rs.getInt(1)+ "\t"+ rs.getString(2)+" \t"+rs.getInt(3)+"\t"+rs.getDouble(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getLong(8));
            }
            System.out.println("----------------------------------------------------------------------------");


        }
        catch (ClassNotFoundException e){
            System.err.println(e);
        }
        catch (Exception e){
            System.err.println(e);
        }

    }



    public void  showBookingDetail(){
        ;
        if (list.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Room room : list) {
                if (room != null) {
                    room.showDetail();
                }
            }
        }



            }




        }




