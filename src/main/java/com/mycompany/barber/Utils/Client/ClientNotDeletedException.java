package com.mycompany.barber.Utils.Client;

public class ClientNotDeletedException extends RuntimeException{
    public ClientNotDeletedException(String message) {
        super(message);
    }
}
