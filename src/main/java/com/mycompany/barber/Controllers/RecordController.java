package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.UserDTO;
import com.mycompany.barber.Models.Line;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Services.LineService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.User.UserErrorResponse;
import com.mycompany.barber.Utils.User.UserNotCreatedException;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/records")
public class RecordController {
    private final LineService lineService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RecordController(LineService lineService, UserService userService, ModelMapper modelMapper) {
        this.lineService = lineService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{userId}")
    public RecordDTO getAllRecordsForUser(@PathVariable int userId) {
        return new RecordDTO(userService.findById(userId).getUserName(), "date",
                lineService.findAll(userId).stream().map(this::convertToLineDTO).collect(Collectors.toList()));
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<HttpStatus> addLine(@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult, @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new UserNotCreatedException(errorMsg.toString());
        }
        lineDTO.setUserId(userId);
        lineService.save(convertToLine(lineDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Line convertToLine(LineDTO lineDTO) {
        return modelMapper.map(lineDTO, Line.class);
    }

    private LineDTO convertToLineDTO(Line line) {
        return modelMapper.map(line, LineDTO.class);
    }
}
