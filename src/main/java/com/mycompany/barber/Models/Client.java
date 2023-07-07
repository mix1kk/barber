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
    @Column(name = "clientId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    @Column
    private String clientName;
    @Column
    private int userId;//id пользователя, который завел клиента
    @Column
    private String companyName;//название компании, которая завела клиента в базу
    @Column
    private String clientPhoneNumber;
    @Column
    private String clientEmail;

}
