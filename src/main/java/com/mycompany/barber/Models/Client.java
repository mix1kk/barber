package com.mycompany.barber.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Table(name ="client")
@Getter
@Setter
@Entity
@ToString(includeFieldNames=true)
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
