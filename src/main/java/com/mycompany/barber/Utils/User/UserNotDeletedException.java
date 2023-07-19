package com.mycompany.barber.Utils.User;

public class UserNotDeletedException extends RuntimeException{
    public UserNotDeletedException(String message) {
        super(message);
    }
}
