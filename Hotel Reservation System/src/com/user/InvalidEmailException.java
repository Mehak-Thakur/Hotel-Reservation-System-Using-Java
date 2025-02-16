package com.user;

public class InvalidEmailException extends Exception{
    InvalidEmailException(String msg){
        super(msg);
    }
}
