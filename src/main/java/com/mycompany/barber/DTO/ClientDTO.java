package com.mycompany.barber.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    @Schema(description = "Идентификатор клиента", example = "1")
    private int clientId;
    @Schema(description = "Имя клиента", example = "Иванов")
    private String clientName;
    @Schema(description = "Идентификатор пользователя", example = "1")
    private int userId;//id пользователя, который завел клиента
    @Schema(description = "Название компании", example = "Парикмахерская №1")
    private String companyName;//название компании, которая завела клиента в базу
    @Schema(description = "Номер телефона клиента", example = "89090958030")
    private String clientPhoneNumber;
    @Email
    @Schema(description = "Email клиента", example = "kenaa@example.com")
    private String clientEmail;

}
