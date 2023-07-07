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
    @Column(name = "lineId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lineId;
    @Column
    private int userId;
    @Column
    private String date;
    @Column
    private String time;
    @Column
    private String userCompany;
    @Column
    private String clientName;
    @Column
    private String procedureName;
    @Column
    private String comment;


}
