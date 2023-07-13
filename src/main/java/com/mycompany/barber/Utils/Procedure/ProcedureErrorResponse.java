package com.mycompany.barber.Utils.Procedure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureErrorResponse {
    private String message;
    private long timestamp;

    public ProcedureErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
