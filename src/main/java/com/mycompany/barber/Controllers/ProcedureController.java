package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ProcedureDTO;
import com.mycompany.barber.DTO.UserDTO;
import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Services.ProcedureService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.Procedure.ProcedureErrorResponse;
import com.mycompany.barber.Utils.Procedure.ProcedureNotCreatedException;
import com.mycompany.barber.Utils.Procedure.ProcedureNotFoundException;
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
@RequestMapping("/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;
    private final ModelMapper modelMapper;
    @Autowired
    public ProcedureController(ProcedureService procedureService, ModelMapper modelMapper) {
        this.procedureService = procedureService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/user/{userId}")
    public List<ProcedureDTO> allProceduresForUser(@PathVariable int userId) {
        return procedureService.findAllForUser(userId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList());
    }

    @GetMapping("/{procedureId}")
    public ProcedureDTO singleProcedure(@PathVariable int procedureId) {
        return convertToProcedureDTO(procedureService.findById(procedureId));
    }

    @ExceptionHandler
    private ResponseEntity<ProcedureErrorResponse> handleException(ProcedureNotFoundException e) {
        ProcedureErrorResponse response = new ProcedureErrorResponse("Procedure not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/{userId}")
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
