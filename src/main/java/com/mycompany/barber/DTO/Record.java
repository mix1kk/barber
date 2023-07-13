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
public class Record {
    private String userName;
    private String date;
    private List<Line> userRecords;
    public Record() {
        this.userName   =    "userName";
        this.date       =    "date";
        this.userRecords = new ArrayList<>(Arrays.asList(new Line())) ;
    }
    public Record(String userName, String date, List<Line> userRecords) {
        this.userName = userName;
        this.date = date;
        this.userRecords = userRecords;
    }
}
