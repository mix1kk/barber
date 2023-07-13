package com.mycompany.barber.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table
@Getter
@Setter
public class Procedure {
    @Id
    @Column(name = "\"procedureId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int procedureId;
    @Column(name = "\"procedureName\"")
    private String procedureName;
    @Column(name = "\"procedureDuration\"")
    private String procedureDuration;
    @Column(name = "\"userId\"")
    private int userId;
    @Column(name = "\"companyName\"")
    private String companyName;
}
