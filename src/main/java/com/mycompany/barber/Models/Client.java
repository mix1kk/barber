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
public class Client {
    @Id
    @Column(name = "\"clientId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    @Column(name = "\"clientName\"")
    private String clientName;
    @Column(name = "\"userId\"")
    private int userId;//id пользователя, который завел клиента
    @Column(name = "\"companyName\"")
    private String companyName;//название компании, которая завела клиента в базу
    @Column(name = "\"clientPhoneNumber\"")
    private String clientPhoneNumber;
    @Column(name = "\"clientEmail\"")
    private String clientEmail;

}
