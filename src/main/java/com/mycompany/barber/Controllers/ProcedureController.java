package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ProcedureDTO;
import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Services.ProcedureService;
import com.mycompany.barber.Utils.Procedure.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/procedures")
@Tag(name = "Контроллер процедур", description = "Позволяет добавлять, удалять, редактировать процедуры")
public class ProcedureController {

    private final ProcedureService procedureService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProcedureController(ProcedureService procedureService, ModelMapper modelMapper) {
        this.procedureService = procedureService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Получить список всех процедур для пользователя")
    public List<ProcedureDTO> allProceduresForUser(@PathVariable int userId) {
        return procedureService.findAllForUser(userId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList());
    }

    @GetMapping("/company/{companyName}")
    @Operation(summary = "Получить список всех процедур для компании")
    public List<ProcedureDTO> allProceduresForCompany(@PathVariable("companyName") String companyName) {
        return procedureService.findByCompanyName(companyName).stream().map(this::convertToProcedureDTO).collect(Collectors.toList());
    }

    @GetMapping("/{procedureId}")
    @Operation(summary = "Получить одну процедуру по id процедуры")
    public ProcedureDTO singleProcedure(@PathVariable int procedureId) {
        return convertToProcedureDTO(procedureService.findById(procedureId));
    }

    @PostMapping("/user/{userId}")
    @Operation(summary = "Сохранить новую процедуру")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProcedureDTO procedureDTO, BindingResult bindingResult,
                                             @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new ProcedureNotCreatedException(errorMsg.toString());
        }
        procedureDTO.setUserId(userId);
        procedureService.save(convertToProcedure(procedureDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать процедуру")
    @PatchMapping("/{procedureId}")
    public ResponseEntity<HttpStatus> updateProcedure(@RequestBody @Valid ProcedureDTO procedureDTO, BindingResult bindingResult, @PathVariable("procedureId") int procedureId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new ProcedureNotUpdatedException(errorMsg.toString());
        }

        procedureService.update(procedureId, convertToProcedure(procedureDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить Процедуру")
    @DeleteMapping("/{procedureId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("procedureId") int procedureId) {
        procedureService.delete(procedureId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotDeletedException e) {
        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotFoundException e) {
        ProcedureErrorResponse response = new ProcedureErrorResponse("Procedure not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotUpdatedException e) {
        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotCreatedException e) {
        ProcedureErrorResponse response = new ProcedureErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Procedure convertToProcedure(ProcedureDTO procedureDTO) {
        return modelMapper.map(procedureDTO, Procedure.class);
    }

    private ProcedureDTO convertToProcedureDTO(Procedure procedure) {
        return modelMapper.map(procedure, ProcedureDTO.class);
    }
}
