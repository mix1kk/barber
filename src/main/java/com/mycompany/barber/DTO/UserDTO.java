package com.mycompany.barber.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int clientId;
    @NotEmpty(message = "Empty name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    private String userName;
    @NotEmpty(message = "Empty company name")
    private String userCompany;
    private String userPhoneNumber;
    private String userProfession;
    private String userRole;
    private String usersAccessed;
    @NotEmpty(message = "Empty password")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 symbols")
    private String password;
    @Email
    @NotEmpty(message = "Empty email")
    private String email;
    private String picture;
}
