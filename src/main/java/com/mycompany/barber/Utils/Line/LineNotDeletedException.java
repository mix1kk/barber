package com.mycompany.barber.Utils.Line;

public class LineNotDeletedException extends RuntimeException{
    public LineNotDeletedException(String message) {
        super(message);
    }
}
