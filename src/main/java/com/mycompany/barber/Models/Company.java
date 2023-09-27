package com.mycompany.barber.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "\"company\"")
@Getter
@Setter
@ToString(includeFieldNames=true)
public class Company {
    @Id
    @Column(name = "\"companyId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    @NotEmpty(message = "Empty name")
    @Column(name = "\"companyName\"")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String companyName;
    @Column(name = "\"address\"")
    private String address;
    @Column(name = "\"phoneNumber\"")
    private String phoneNumber;
    @Column(name = "\"email\"")
    private String email;
    @Column(name = "\"siteLink\"")
    private String siteLink;
    @Column(name = "\"ogrn\"")
    private String ogrn;
    @Column(name = "\"inn\"")
    private String inn;
    @Column(name = "\"okpo\"")
    private String okpo;
    @Column(name = "\"createdBy\"")
    private String createdBy;
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
