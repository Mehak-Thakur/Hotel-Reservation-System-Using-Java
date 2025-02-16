package com.user;

import java.util.Objects;

public class Customer {
    private String name;
    private String email;
    private String address;
    private long phoneNumber;

    public Customer(String name, String email, long phoneNumber, String address) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Customer(){

    }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getAddress() {
         return address;
     }

     public void setAddress(String address) {
         this.address = address;
     }

     public long getPhoneNumber() {
         return phoneNumber;
     }

     public void setPhoneNumber(long phoneNumber) {
         this.phoneNumber = phoneNumber;
     }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer user = (Customer) o;
        return phoneNumber == user.phoneNumber && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, address, phoneNumber);
    }

    public void showDetail(){
        System.out.println("Customer name is : "+name);
        System.out.println("Contact no is : "+phoneNumber);
        System.out.println("Email address is : "+email);
        System.out.println("Address is : "+address);
    }
}
