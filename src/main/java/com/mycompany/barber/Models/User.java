package com.mycompany.barber.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Component
@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "\"userId\"")
//    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
//    @GeneratedValue(strategy = SEQUENCE, generator = "USER_SEQ")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name = "\"userName\"")
    private String userName;
    @Column(name = "\"userCompany\"")
    private String userCompany;
    @Column(name = "\"userPhoneNumber\"")
    private String userPhoneNumber;
    @Column(name = "\"userProfession\"")
    private String userProfession;
    @Column(name = "\"userRole\"")
    private String userRole;
    @Column(name = "\"userAccessed1\"")
    private int userAccessed1;
    @Column(name = "\"userAccessed2\"")
    private int userAccessed2;

    public User() {
        this.userId = 0;
        this.userName = "userName";
        this.userCompany = "userCompany";
        this.userPhoneNumber = "userPhoneNumber";
        this.userProfession = "userProfession";
        this.userRole = "userRole";
        this.userAccessed1 = 0;
        this.userAccessed2 = 0;
    }
}
