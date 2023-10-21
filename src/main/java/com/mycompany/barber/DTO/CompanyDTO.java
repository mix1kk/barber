package com.mycompany.barber.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyDTO {
    @Schema(description = "Идентификатор компании", example = "0")
    private int companyId;
    @NotEmpty(message = "Empty name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    @Schema(description = "Название компании", example = "companyName")
    private String companyName;
    @NotEmpty(message = "Empty company address")
    @Schema(description = "Адрес компании", example = "Town, Street, number")
    private String address;
    @Schema(description = "Номер телефона компании", example = "8909058031")
    private String phoneNumber;
    @Email
    @NotEmpty(message = "Empty email")
    @Schema(description = "Email компании", example = "dycjh@example.com")
    private String email;
    @Schema(description = "Cайт компании", example = "https://site.com")
    private String siteLink;
    @Schema(description = "ОГРН компании", example = "1047712345678")
    private String ogrn;
    @Schema(description = "ИНН компании", example = "7712345678")
    private String inn;
    @Schema(description = "ОКПО компании", example = "12345678")
    private String okpo;
}
