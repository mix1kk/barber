package com.mycompany.barber.DTO;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private int clientId;

    private String clientName;

    private int userId;//id пользователя, который завел клиента

    private String companyName;//название компании, которая завела клиента в базу

    private String clientPhoneNumber;
    @Email
    private String clientEmail;

}
