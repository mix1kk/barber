package com.mycompany.barber.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineDTO {
    private int lineId;
    private int userId;
    private String date;
    private String time;
    private String clientName;
    private String procedureName;
    private String comment;

}
