package com.mycompany.barber.Controllers;


import com.mycompany.barber.DTO.UserDTO;
import com.mycompany.barber.Models.*;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.User.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@Tag(name = "Контроллер пользователей", description = "Позволяет добавлять, удалять, редактировать пользователей")
@RequestMapping()
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping("/users")
//    public List<UserDTO> allUsers() {
//        return userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList());
//    }
    public String allUsers(Model model) {
        model.addAttribute("AllUsers", userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList()));
        return "User/allUsers";
    }


    @Operation(summary = "Получить список всех пользователей в компании")
    @GetMapping("/users/{userCompany}")
//    public List<UserDTO> allUsersForCompany(@PathVariable("userCompany") String userCompany) {
    //        return userService.findByUserCompany(userCompany).stream().map(this::convertToUserDTO).collect(Collectors.toList());
//    }
    public String allUsersForCompany(Model model, @PathVariable("userCompany") String userCompany) {
        model.addAttribute("AllUsers", userService.findByUserCompany(userCompany).stream().map(this::convertToUserDTO).collect(Collectors.toList()));
        return "User/allUsers";
    }

    @Operation(summary = "Получить пользователя по id")
    @GetMapping("/user/{id}")
//    public UserDTO singleUser(@PathVariable("id") int id) {
//        return convertToUserDTO(userService.findById(id));
//    }
    public String singleUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", convertToUserDTO(userService.findById(id)));
        return "User/singleUser";
    }

    @Operation(summary = "Создать нового пользователя")
    @GetMapping("/users/new")
//    public ResponseEntity<HttpStatus> createUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors) {
//                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
//            }
//            System.out.println(errorMsg);
//            throw new UserNotCreatedException(errorMsg.toString());
//        }
//        userService.save(convertToUser(userDTO));
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            //throw new UserNotCreatedException(errorMsg.toString());
            return "User/newUser";
        }
        userService.save(convertToUser(userDTO));
        return "User/allUsers";
    }

    @Operation(summary = "Редактировать пользователя")
    @PatchMapping("/user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult, @PathVariable("id") int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new UserNotUpdatedException(errorMsg.toString());
        }

        userService.update(userId, convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userId") int userId) {
        userService.delete(userId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotUpdatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotDeletedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
