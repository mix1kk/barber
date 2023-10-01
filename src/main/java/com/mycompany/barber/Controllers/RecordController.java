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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping()
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

//    /**
//     * Получить список записей пользователя от даты до даты
//     * даты передаются в параметрах запроса
//     */
//    @GetMapping("/records/user/{userId}")
//    public List<RecordDTO> getAllRecordsForUser(@PathVariable int userId,
//                                                @RequestParam("startDate") String startDate,
//                                                @RequestParam("endDate") String endDate) {
//        return recordService.findAllForUserFromDateToDate(userId, userService.findById(userId).getUserName(), startDate, endDate);
//    }

    /**
     * Получить список записей пользователя на конкретную дату
     * дата передается в параметрах запроса
     */
    @GetMapping("/records/user/{userId}")
    public String getRecordsForUser(@PathVariable int userId,
                                    @RequestParam(required = false, name="date") String date,
                                    Model model) {
        RecordDTO recordDTO = recordService.findAllForUserOnDate(userId, userService.findById(userId).getUserName(), date);
        model.addAttribute("record", recordDTO);
        model.addAttribute("allLines",recordDTO.getUserRecords());
        return "Record/allRecords";
    }

    @Operation(summary = "Получить запись по id записи")
    @GetMapping("/records/{lineId}")
    public LineDTO singleRecord(@PathVariable("lineId") int lineId) {
        return LineMapper.mapToLineDTO(lineService.findById(lineId));
    }

    /**Создать новую запись пользователя
     *
     * @param lineDTO
     * @param bindingResult
     * @param userId
     * @return
     */
    @PostMapping("/records/user/{userId}")
    public String addRecord(@ModelAttribute("line")@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult, @PathVariable int userId, Model model, @RequestParam(required = false, name="date") String date) {
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
//        RecordDTO recordDTO = recordService.findAllForUserOnDate(userId, userService.findById(userId).getUserName(), date);
//        model.addAttribute("record", recordDTO);
//        model.addAttribute("allLines",recordDTO.getUserRecords());
        return "redirect:/records/user/"+userId;
    }

    /**
     * Редактировать запись пользователя
     * @param lineDTO
     * @param bindingResult
     * @param lineId
     * @return
     */
    @PatchMapping("/records/line/{lineId}")
    public ResponseEntity<HttpStatus> updateRecord(@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult,
                                                   @PathVariable("lineId") int lineId) {
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
    @DeleteMapping("/records/line/{lineId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("lineId") int lineId) {
        lineService.delete(lineId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
//
//    @ExceptionHandler
//    private ResponseEntity<LineErrorResponse> handleException(LineNotDeletedException e) {
//        LineErrorResponse response = new LineErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
//        UserErrorResponse response = new UserErrorResponse("User not exist", System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<LineErrorResponse> handleException(LineNotUpdatedException e) {
//        LineErrorResponse response = new LineErrorResponse(e.getMessage(), System.currentTimeMillis());
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
//    private ResponseEntity<LineErrorResponse> handleException(LineNotFoundException e) {
//        LineErrorResponse response = new LineErrorResponse("Line not exist", System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

}
