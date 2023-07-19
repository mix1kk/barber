package com.mycompany.barber.Utils.Client;

public class ClientNotUpdatedException extends RuntimeException{
    public ClientNotUpdatedException(String message) {
        super(message);
    }
}
