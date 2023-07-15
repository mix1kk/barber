package com.mycompany.barber.Utils.User;

public class UserNotUpdatedException extends RuntimeException{
    public UserNotUpdatedException(String message) {
        super(message);
    }
}
