package com.user;

import java.sql.*;
import java.util.Scanner;
public class HotelManagementSystem  {


    public static void main(String[] args){
        Connection connection=null;
        Statement statement=null;
        ResultSet rs=null;
        PreparedStatement preparedStatement=null;
        String name="",email="",address="" ,roomType="";
        long number =0L;
        int id=21,roomNumber=110;
        double price=0.0;


        Hotel hotel=new Hotel("Grand Ambassador hotel");
        Customer customer;
        Room room;


        hotel.showBookingDetail();
        int choice;
        Scanner sc=new Scanner(System.in);

        do{
            System.out.println("1 . Booking room ");
            System.out.println("2 . show booking detail");
            System.out.println("3 . find room is booked or not ");
            System.out.println("4 . Cancelation booking ");
            System.out.println("5 . show all room");
            System.out.println("6 . show all room by category ");

            System.out.println("7 . 0 for exit ");

            System.out.println("Enter choice  ...");
            choice=sc.nextInt();
            sc.nextLine();
            switch (choice){

                case 1: {
                    try {


                        System.out.print("Enter name : ");


                        name = sc.nextLine();
                        hotel.validName(name);


                        System.out.print("Enter email id of customer : ");
                        email = sc.nextLine();
                        hotel.validEmail(email);
                        System.out.print("Enter contact number of customer : ");
                        number = sc.nextLong();
                        hotel.validNumber(number);

                        sc.nextLine();
                        System.out.print("Enter addrerss of customer : ");
                        address = sc.nextLine();
                        System.out.print("Enter room type like Single , Double ,Luxury ...  room ");
                        roomType = sc.nextLine().toLowerCase();
                        if(roomType.equals("single")){
                            price=4000;
                            ++roomNumber;
                            ++id;
                        }
                        else if(roomType.equals("double")){
                            price=6000;
                            ++roomNumber;
                            ++id;
                        }
                        else if(roomType.equals("luxury")){
                            price=10000;
                            ++roomNumber;
                            ++id;
                        }
                        else{
                            System.out.println("Enter valid type ");
                            return;
                        }
//
//                        System.out.print("Enter room type like Single seater , Double seater ... fourth seater ");
//                        roomType = sc.nextLine();
//                        System.out.print("Enter roomNumber of room : ");
//                        roomNumber = sc.nextInt();
//                        System.out.print("Enter id of room : ");
//                        id = sc.nextInt();
//                        System.out.print("Enter price of room : ");
//                        price = sc.nextDouble();


                        customer = new Customer(name, email, number, address);
                        room = new Room(roomType, roomNumber, price, id);


                        connection = DbUitl.getDbConnection();
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");
                        connection.setAutoCommit(true);
                        String query = "insert into rooms (id,roomType,roomNumber,price,name,email,address,phone) values( ?,?,?,?,?,?,?,?) ";


                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, id);
                        preparedStatement.setString(2, roomType);
                        preparedStatement.setInt(3, roomNumber);
                        preparedStatement.setDouble(4, price);
                        preparedStatement.setString(5, name);
                        preparedStatement.setString(6, email);
                        preparedStatement.setString(7, address);
                        preparedStatement.setLong(8, number);

                        int count = preparedStatement.executeUpdate();
                        if (count > 0) {
                            System.out.println("insert successfully");
                        } else {
                            System.err.println("some error occured in inserting ");
                        }


                        hotel.bookRoom(room, customer);


                    }

                    catch (ClassNotFoundException e) {
                        System.out.println(e);

                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }







                    break;

                }
                case 2: {

                   hotel.showBookingDetail();


                    break;
                }

                case 3:
                    System.out.println("Enter id to search particular room is booked or not : ");
                    id=sc.nextInt();
                    if(hotel.isBooked(id)){
                        System.out.println("Room with "+id+" is already booked");
                    }
                    else{
                        System.out.println("Room is available for booking ");
                    }

                    break;


                case  4:
                    try {


                        System.out.println("Enter id to search particular room is booked or not : ");
                        id = sc.nextInt();

                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement", "root", "Mth@$0034");
                        String query = "DELETE FROM rooms WHERE id = ?";
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setInt(1, id);

                        int rowsDeleted = preparedStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Booking canceled successfully for Room ID: " + id);
                        } else {
                            System.out.println("Room ID not found. No booking was canceled.");
                        }

//                        if (hotel.isCancel(id)) {
//                            System.out.println("Booking cancel with this id : " + id);
//
//
//                        } else {
//                            System.out.println("Room id is not found ");
//
//                        }

                    }

                    catch (Exception e){
                        System.out.println(e);
                    }
                    break;


                case 5:{

                    hotel.getRoom();

                    break;
                }

                case 6:{
                    System.out.println("Enter category to search room ");
                    roomType=sc.nextLine().toLowerCase();
                    hotel.getRoomByCategory(roomType);
                    break;
                }
                case 7:
                    System.exit(0);


                default:
                    System.err.println("Give correct choice between 1 to 7");
                    break;
            }
        }while (choice!=0);
    }
}
