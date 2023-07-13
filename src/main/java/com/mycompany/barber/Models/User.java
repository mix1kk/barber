package com.mycompany.barber.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "\"userId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotEmpty(message = "Empty name")
    @Column(name = "\"userName\"")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String userName;
    @NotEmpty(message = "Empty company name")
    @Column(name = "\"userCompany\"")
    private String userCompany;
    @Column(name = "\"userPhoneNumber\"")
    private String userPhoneNumber;
    @Column(name = "\"userProfession\"")
    private String userProfession;
    @Column(name = "\"userRole\"")
    private String userRole;
    @Column(name = "\"userAccessed\"")
    private String usersAccessed;
    @NotEmpty(message = "Empty password")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 symbols")
    @Column(name = "\"password\"")
    private String password;
    @Email
    @NotEmpty(message = "Empty email")
    @Column(name = "\"Email\"")
    private String email;
    @Column(name = "\"picture\"")
    private String picture;
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

    public User() {
        this.userId = 0;
        this.userName = "userName";
        this.userCompany = "userCompany";
        this.userPhoneNumber = "userPhoneNumber";
        this.userProfession = "userProfession";
        this.userRole = "userRole";
        this.usersAccessed = "usersAccessed";
        this.password = "password";
        this.email = "email@email.com";
        this.picture = "url";
        this.createdAt = "createdAt";
        this.updatedAt = "updatedAt";
        this.updatedBy = "updatedBy";
        this.spare1 = "spare1";
        this.spare2 = "spare2";
    }
}
