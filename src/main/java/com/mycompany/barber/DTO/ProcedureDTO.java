package com.mycompany.barber.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureDTO {
//    @Schema(description = "Идентификатор процедуры", example = "0")
    private int procedureId;
//    @Schema(description = "Название процедуры", example = "Стрижка")
    private String procedureName;
//    @Schema(description = "Длительность процедуры", example = "30 минут")
    private String procedureDuration;
//    @Schema(description = "Идентификатор пользователя", example = "1")
    private int userId;
//    @Schema(description = "Идентификатор компании", example = "Парикмахерская №1")
    private String companyName;
}
