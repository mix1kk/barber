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
    @Column(name = "procedureId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int procedureId;
    @Column
    private String procedureName;
    @Column
    private String procedureDuration;
    @Column
    private int userId;
    @Column
    private String companyName;
}
