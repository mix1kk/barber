package com.mycompany.barber.Models.HttpMessages;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Entity
@Table
public class Line {
    @Id
    @Column(name = "\"lineId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineId;
    @Column(name = "\"userId\"")
    private int userId;
    @Column(name = "\"userCompany\"")
    private String userCompany;
    @Column(name = "\"date\"")
    private String date;
    @Column(name = "\"time\"")
    private String time;
    @Column(name = "\"clientName\"")
    private String clientName;
    @Column(name = "\"procedureName\"")
    private String procedureName;
    @Column(name = "\"comment\"")
    private String comment;

    public Line() {
        this.lineId = 1;
        this.userId = 1;
        this.userCompany = "userCompany";
        this.date = "date";
        this.time = "time";
        this.clientName = "clientName";
        this.procedureName = "procedureName";
        this.comment = "comment";
    }
}
