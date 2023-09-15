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
@RequestMapping()
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Получить список всех пользователей
     */
    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("allUsers", userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList()));
        return "User/allUsers";
    }

    /**
     * получить список всех пользователей в компаниии
     */
    @GetMapping("/users/{userCompany}")
    public String allUsersForCompany(Model model, @PathVariable("userCompany") String userCompany) {
        model.addAttribute("AllUsers", userService.findByUserCompany(userCompany).stream().map(this::convertToUserDTO).collect(Collectors.toList()));
        return "User/allUsers";
    }

    /**
     * Получить пользователя по id
     */
    @GetMapping("/user/{id}")
    public String singleUser(@PathVariable("id") int id, Model model) {
        System.out.println(convertToUserDTO(userService.findById(id)));
        model.addAttribute("user", convertToUserDTO(userService.findById(id)));
        return "User/singleUser";
    }

    /**
     * Создать нового пользователя
     */

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") UserDTO userDTO) {
        return "User/newUser";
    }

    @PostMapping("/users/new")
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
        return "redirect:/users";
    }

    /**
     * Редактировать пользователя
     */

    @GetMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int userId) {
        model.addAttribute("user", convertToUserDTO(userService.findById(userId)));
        return "User/editUser";
    }

    @PatchMapping("/user/{id}/edit")
    public String updateUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, @PathVariable("id") int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            //throw new UserNotCreatedException(errorMsg.toString());
            return "User/editUser";
        }
        userService.update(userId, convertToUser(userDTO));
        return "redirect:/users";
    }

    /**
     * Удаление пользователя
     */
    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotUpdatedException e) {
//        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotDeletedException e) {
//        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
//        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
//        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
