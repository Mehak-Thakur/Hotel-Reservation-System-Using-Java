package com.user;

import java.util.Objects;

public class Room {
    private String roomType;
    private int roomNumber;
    private double price;
    private int id=0;
    private Customer customer;
    private boolean booked;

    public Room(String roomType, int roomNumber, double price,int id) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.price = price;
        this.id=id;
        this.customer=null;
        this.booked=false;
    }

    public Room(){

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public boolean isBooked(){
        return booked;
    }

    public void book(Customer customer){
        this.booked=true;
        this.customer=customer;
    }

    public void isCancel(){
        this.booked=false;
        this.customer=null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber && Double.compare(price, room.price) == 0 && Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomType, roomNumber, price);
    }

    @Override
    public String toString() {
        return
                "\nroomType='" + roomType + '\'' +
                ", roomNumber=" + roomNumber +
                ", price=" + price ;
    }

    public void showDetail(){
        System.out.println("Room number is : "+roomNumber);
        System.out.println("Room type is : "+roomType);
        System.out.println("Price is : "+price);
        if(booked && customer!=null){
            System.out.println("Customer detail are : ");
            customer.showDetail();
        }
        else{
            System.out.println("Room is available ");
        }

    }



}
