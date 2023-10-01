package com.mycompany.barber.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class LineDTO {
    @Schema(description = "Идентификатор строки", example = "0")
    private int lineId;
    @Schema(description = "Идентификатор пользователя", example = "1")
    private int userId;
    @Schema(description = "Дата", example = "01-01-2020")
    private String date;
    @Schema(description = "Время", example = "09:30")
    private String time;
    @Schema(description = "Имя клиента", example = "Магомет")
    private String clientName;
    @Schema(description = "Имя процедуры", example = "Стрижка")
    private String procedureName;
    @Schema(description = "Стоимость", example = "1000")
    private String procedureCost;
    @Schema(description = "Скидка", example = "10%")
    private String procedureDiscount;
    @Schema(description = "Комментарий", example = "Предупредить за 30 минут")
    private String comment;

}
