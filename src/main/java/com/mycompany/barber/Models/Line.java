package com.mycompany.barber.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Entity
@Table(name = "line")
@Getter
@Setter
@ToString(includeFieldNames = true)
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
    private LocalDate date;
    @Column(name = "\"time\"")
    private String time;
    @Column(name = "\"clientName\"")
    private String clientName;
    @Column(name = "\"procedureName\"")
    private String procedureName;
    @Column(name = "\"procedureCost\"")
    private String procedureCost;
    @Column(name = "\"procedureDiscount\"")
    private String procedureDiscount;
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
        this.date = LocalDate.now();
    }
    public Line(int lineId, int userId, String userCompany, LocalDate date, String time, String clientName, String procedureName, String procedureCost, String procedureDiscount, String comment, String createdAt, String updatedAt, String updatedBy, String spare1, String spare2) {
        this.lineId = lineId;
        this.userId = userId;
        this.userCompany = userCompany;
        this.date = date;
        this.time = time;
        this.clientName = clientName;
        this.procedureName = procedureName;
        this.procedureCost = procedureCost;
        this.procedureDiscount = procedureDiscount;
        this.comment = comment;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.spare1 = spare1;
        this.spare2 = spare2;
    }
}

