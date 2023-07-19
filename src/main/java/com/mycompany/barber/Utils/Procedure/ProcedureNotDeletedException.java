package com.mycompany.barber.Utils.Procedure;

public class ProcedureNotDeletedException extends RuntimeException{
    public ProcedureNotDeletedException(String message) {
        super(message);
    }
}