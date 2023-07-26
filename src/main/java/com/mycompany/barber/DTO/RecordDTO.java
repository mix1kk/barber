package com.mycompany.barber.DTO;

import com.mycompany.barber.Models.Line;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
public class RecordDTO {
    @Schema(description = "Идентификатор пользователя", example = "1")
    private int userId;
    @Schema(description = "Имя пользователя", example = "John Doe")
    private String userName;
    @Schema(description = "Дата", example = "01-01-2020")
    private String date;
    @Schema(description = "Список записей", example = "[" +
            "{\"lineId\":0," +
            "\"userId\":0," +
            "\"date\":\"01-01-2020\"," +
            "\"time\":\"8:30\"," +
            "\"clientName\":\"clientName\"," +
            "\"procedureName\":\"procedureName\"," +
            "\"procedureCost\":\"1000\"," +
            "\"procedureDiscount\":\"10\"," +
            "\"comment\":\"comment\"," +
            "}" +
            "]")
    private List<LineDTO> userRecords;

    public RecordDTO() {
    }

    public RecordDTO(int userId, String userName, String date, List<LineDTO> userRecords) {
        this.userId = userId;
        this.userName = userName;
        this.date = date;
        this.userRecords = userRecords;
    }
}
