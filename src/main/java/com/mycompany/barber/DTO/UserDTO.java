package com.mycompany.barber.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сущность пользователя")
public class UserDTO {
    @Schema(description = "Идентификатор пользователя", example = "1")
    private int userId;
    @NotEmpty(message = "Empty name")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 symbols")
    @Schema(description = "Имя пользователя", example = "John Doe")
    private String userName;
    @NotEmpty(message = "Empty company name")
    @Schema(description = "Название компании пользователя", example = "My Company")
    private String userCompany;
    @Schema(description = "Номер телефона пользователя", example = "8909058030")
    private String userPhoneNumber;
    @Schema(description = "Профессия пользователя", example = "Парикмахер")
    private String userProfession;
    @Schema(description = "Роль пользователя", example = "USER")
    private String userRole;
    @Schema(description = "Доступные пользователи", example = "доделать")
    private String usersAccessed;
    @NotEmpty(message = "Empty password")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 symbols")
    @Schema(description = "Пароль пользователя", example = "123456")
    private String password;
    @Email
    @NotEmpty(message = "Empty email")
    @Schema(description = "Email пользователя", example = "dycjh@example.com")
    private String email;
    @Schema(description = "URL Изображения пользователя")
    private String picture;
}
