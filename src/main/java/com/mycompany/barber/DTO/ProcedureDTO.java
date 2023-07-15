package com.mycompany.barber.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureDTO {
    private int procedureId;
    private String procedureName;
    private String procedureDuration;
    private int userId;
    private String companyName;
}
