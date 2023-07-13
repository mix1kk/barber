package com.mycompany.barber.Controllers;

import com.mycompany.barber.Models.HttpMessages.Line;
import com.mycompany.barber.Models.HttpMessages.Record;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Services.LineService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.User.UserErrorResponse;
import com.mycompany.barber.Utils.User.UserNotCreatedException;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final LineService lineService;
    private final UserService userService;


    @Autowired
    public RecordController(LineService lineService, UserService userService) {
        this.lineService = lineService;
        this.userService = userService;
    }
    @GetMapping("/{userId}")
    public Record getAllRecordsForUser(@PathVariable int userId){
        return new Record(userService.findById(userId).getUserName(),"date",lineService.findAll(userId));
    }
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/{userId}")
    public ResponseEntity<HttpStatus> addLine (@RequestBody @Valid Line line, BindingResult bindingResult, @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new UserNotCreatedException(errorMsg.toString());
        }
        line.setUserId(userId);
        line.setUserCompany(userService.findById(userId).getUserCompany());
        lineService.save(line);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
