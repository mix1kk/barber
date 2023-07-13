package com.mycompany.barber.DTO;

import com.mycompany.barber.Models.Line;
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
    private String userName;
    private String date;
    private List<LineDTO> userRecords;

    public RecordDTO() {
    }

    public RecordDTO(String userName, String date, List<LineDTO> userRecords) {
        this.userName = userName;
        this.date = date;
        this.userRecords = userRecords;
    }
}
