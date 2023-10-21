package com.mycompany.barber.Utils.Company;

public class CompanyNotDeletedException extends RuntimeException{
    public CompanyNotDeletedException(String message) {
        super(message);
    }
}
