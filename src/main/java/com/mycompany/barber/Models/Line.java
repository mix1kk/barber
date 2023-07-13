package com.mycompany.barber.Models;

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
    @Column(name = "\"createdAt\"")
    private String createdAt;
    @Column(name = "\"updatedAt\"")
    private String updatedAt;
    @Column(name = "\"updatedBy\"")
    private String updatedBy;
    @Column(name = "\"spare1\"")
    private String spare1;
    @Column(name = "\"spare2\"")
    private String spare2;

    public Line() {
        this.lineId = 1;
        this.userId = 1;
        this.userCompany = "userCompany";
        this.date = "date";
        this.time = "time";
        this.clientName = "clientName";
        this.procedureName = "procedureName";
        this.comment = "comment";
        this.createdAt = "createdAt";
        this.updatedAt = "updatedAt";
        this.updatedBy = "updatedBy";
        this.spare1 = "spare1";
        this.spare2 = "spare2";
    }
}
