package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Services.LineService;
import com.mycompany.barber.Services.RecordService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.Line.*;
import com.mycompany.barber.Utils.Mappers.LineMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Получить список записей пользователя на конкретную дату
     * дата передается в параметрах запроса
     */
    @GetMapping("/records/user/{userId}")
    public String getRecordsForUser(@PathVariable int userId,
                                    @RequestParam(required = false, name = "date") String date,
                                    @RequestParam(required = false, name = "direction") String direction,
                                    Model model) {
        if (direction != null) {
            return "redirect:/records/user/" + userId + "?date=" + RecordService.nextOrPreviousDate(date, direction);
        }
        RecordDTO recordDTO = recordService.findAllForUserOnDate(userId, userService.findById(userId).getUserName(), date);
        model.addAttribute("record", recordDTO);
        model.addAttribute("allLines", recordDTO.getUserRecords());
        return "Record/allRecords";
    }

    /**
     * Получить и отредактировать единичную запись по айди
     *
     * @param lineId
     * @return
     */


    @GetMapping("/records/{lineId}")
    public String showSingleRecord(@PathVariable("lineId") int lineId, Model model) {
        model.addAttribute("line", LineMapper.mapToLineDTO(lineService.findById(lineId)));
        return "Record/editRecord";
    }

//    @PatchMapping ("/records/{lineId}")
//    public String editSingleRecord(@RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult,@PathVariable("lineId") int lineId) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors) {
//                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
//            }
//            System.out.println(errorMsg);
//            throw new LineNotUpdatedException(errorMsg.toString());
//        }
//        lineDTO.setLineId(lineId);
//        lineService.save(LineMapper.mapToLine(lineDTO));
//        return "redirect:/records/user/" + lineDTO.getUserId() + "?date=" + lineDTO.getDate();
//    }


    /**
     * Создать новую запись пользователя или отредактировать существующую
     *
     * @param lineDTO
     * @param bindingResult
     * @param userId
     * @return
     */
    @PatchMapping("/records/user/{userId}/{lineId}")
    public String addRecord(@ModelAttribute("line") @RequestBody @Valid LineDTO lineDTO, BindingResult bindingResult, @PathVariable int userId, @PathVariable int lineId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new LineNotCreatedException(errorMsg.toString());
        }
        if (RecordService.checkOnNotEmptyRecord(lineDTO)) {
            lineDTO.setUserId(userId);
            lineDTO.setLineId(lineId);
            lineService.save(LineMapper.mapToLine(lineDTO));
        }
        return "redirect:/records/user/" + userId + "?date=" + lineDTO.getDate();
    }

    /**
     * Удалить запись пользователя
     *
     * @param lineId
     * @return
     */
//    @Operation(summary = "Удалить запись")
    @DeleteMapping("/records/user/{userId}/line/{lineId}")
    public String delete(@PathVariable("lineId") int lineId, @PathVariable int userId, @RequestParam(name = "date") String date) {
        lineService.delete(lineId);
        return "redirect:/records/user/" + userId + "?date=" + date;
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
