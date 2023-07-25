package com.mycompany.barber.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "procedure")
@Getter
@Setter
@ToString(includeFieldNames=true)
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
}
