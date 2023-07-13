package com.mycompany.barber.Utils.Line;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineErrorResponse {
    private String message;
    private long timestamp;

    public LineErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
