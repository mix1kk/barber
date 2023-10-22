package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ProcedureDTO;
import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Services.ProcedureService;
import com.mycompany.barber.Utils.Procedure.*;
import io.swagger.v3.oas.annotations.Operation;
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
//@Tag(name = "Контроллер процедур", description = "Позволяет добавлять, удалять, редактировать процедуры")
public class ProcedureController {

    private final ProcedureService procedureService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProcedureController(ProcedureService procedureService, ModelMapper modelMapper) {
        this.procedureService = procedureService;
        this.modelMapper = modelMapper;
    }

    /**
     * Получить список всех процедур для пользователя
     *
     * @param userId
     * @return
     */
    @GetMapping("/procedures/user/{userId}")
//    @Operation(summary = "Получить список всех процедур для пользователя")
    public String allProceduresForUser(@PathVariable int userId, Model model) {
        model.addAttribute("allProcedures", procedureService.findAllForUser(userId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList()));
        return "Procedure/allProcedures";
    }

    /**
     * Получить список всех процедур для компании
     *
     * @param companyId
     * @param model
     * @return
     */
    @GetMapping("/procedures/company/{companyId}")
//    @Operation(summary = "Получить список всех процедур для компании")
    public String allProceduresForCompany(@PathVariable("companyId") String companyId, Model model) {
        model.addAttribute("allProcedures", procedureService.findByCompanyName(companyId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList()));
        return "Procedure/allProcedures";
    }

    /**
     * Получить одну процедуру по id процедуры
     *
     * @param procedureId
     * @return
     */
    @GetMapping("/procedures/{procedureId}")
//    @Operation(summary = "Получить одну процедуру по id процедуры")
    public String singleProcedure(@PathVariable int procedureId, Model model) {
        model.addAttribute("procedure", convertToProcedureDTO(procedureService.findById(procedureId)));
        return "Procedure/singleProcedure";
    }

//    /**
//     * Сохранить новую процедуру
//     *
//     * @param procedureDTO
//     * @param bindingResult
//     * @param userId
//     * @return
//     */
//    @PostMapping("/procedures/user/{userId}")
////    @Operation(summary = "Сохранить новую процедуру")
//    public String create(@RequestBody @Valid ProcedureDTO procedureDTO, BindingResult bindingResult,
//                         @PathVariable int userId) {
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMsg = new StringBuilder();
//            List<FieldError> errors = bindingResult.getFieldErrors();
//            for (FieldError error : errors) {
//                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
//            }
//            throw new ProcedureNotCreatedException(errorMsg.toString());
//        }
//        procedureDTO.setUserId(userId);
//        procedureService.save(convertToProcedure(procedureDTO));
//        return "Procedure/allProcedures";
//    }

    /**
     * Редактировать процедуру
     *
     * @param procedureDTO
     * @param bindingResult
     * @param procedureId
     * @return
     */
//    @Operation(summary = "Редактировать процедуру")
    @PatchMapping("/procedures/{procedureId}")
    public String updateProcedure(@RequestBody @Valid ProcedureDTO procedureDTO, BindingResult bindingResult, @PathVariable("procedureId") int procedureId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new ProcedureNotUpdatedException(errorMsg.toString());
        }
        procedureService.save(convertToProcedure(procedureDTO));
        return "Procedure/allProcedures";
    }

    /**
     * Удалить Процедуру
     *
     * @param procedureId
     * @return
     */
//    @Operation(summary = "Удалить Процедуру")
    @DeleteMapping("/procedures/{procedureId}")
    public String delete(@PathVariable("procedureId") int procedureId) {
        procedureService.delete(procedureId);
        return "Procedure/allProcedures";
    }

//    @ExceptionHandler
//    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotDeletedException e) {
//        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotFoundException e) {
//        ProcedureErrorResponse response = new ProcedureErrorResponse("Procedure not exist", System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotUpdatedException e) {
//        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotCreatedException e) {
//        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    private Procedure convertToProcedure(ProcedureDTO procedureDTO) {
        return modelMapper.map(procedureDTO, Procedure.class);
    }

    private ProcedureDTO convertToProcedureDTO(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDTO.class);
    }
}
