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
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userId;
    @Column
    private String userName;
    @Column
    private String userCompany;
    @Column
    private String userPhoneNumber;
    @Column
    private String userProfession;
    @Column
    private String userRole;
    @Column
    private int userAccessed1;
    @Column
    private int userAccessed2;

}
