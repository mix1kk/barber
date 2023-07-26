package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Services.LineService;
import com.mycompany.barber.Services.RecordService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.Line.*;
import com.mycompany.barber.Utils.Mappers.LineMapper;
import com.mycompany.barber.Utils.User.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/records")
@Tag(name = "Контроллер записей пользователя", description = "Позволяет добавлять, удалять, редактировать строки в таблице")

public class RecordController {
    private final LineService lineService;
    private final UserService userService;
    private final RecordService recordService;

    @Autowired
    public RecordController(LineService lineService, UserService userService, RecordService recordService) {
        this.lineService = lineService;
        this.userService = userService;
        this.recordService = recordService;
    }

    @Operation(summary = "Получить список записей пользователя")
    @GetMapping("/user/{userId}")
    public List<RecordDTO> getAllRecordsForUser(@PathVariable int userId,
                                                @RequestParam("startDate") String startDate,
                                                @RequestParam("endDate") String endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return recordService.findAllForUserFromDateToDate(userId, userService.findById(userId).getUserName(), startDate, endDate);
    }

    @Operation(summary = "Получить запись по id записи")
    @GetMapping("/{lineId}")
    public LineDTO singleUser(@PathVariable("lineId") int lineId) {
        return LineMapper.mapToLineDTO(lineService.findById(lineId));
    }


    @Operation(summary = "Создать новую запись пользователя")
    @PostMapping("/user/{userId}")
    public ResponseEntity<HttpStatus> addLine(@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult, @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new LineNotCreatedException(errorMsg.toString());
        }
        lineDTO.setUserId(userId);
        lineService.save(LineMapper.mapToLine(lineDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать запись пользователя")
    @PatchMapping("/line/{lineId}")
    public ResponseEntity<HttpStatus> updateRecord(@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult,
                                                   @PathVariable("userId") int userId, @PathVariable("lineId") int lineId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new LineNotUpdatedException(errorMsg.toString());
        }

        lineService.update(lineId, LineMapper.mapToLine(lineDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить запись")
    @DeleteMapping("/line/{lineId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("lineId") int lineId) {
        lineService.delete(lineId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<LineErrorResponse> handleException(LineNotDeletedException e) {
        LineErrorResponse response = new LineErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<LineErrorResponse> handleException(LineNotUpdatedException e) {
        LineErrorResponse response = new LineErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<LineErrorResponse> handleException(LineNotFoundException e) {
        LineErrorResponse response = new LineErrorResponse("Line not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
