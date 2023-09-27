package com.mycompany.barber.Utils.Company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyErrorResponse {
    private String message;
    private long timestamp;

    public CompanyErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
