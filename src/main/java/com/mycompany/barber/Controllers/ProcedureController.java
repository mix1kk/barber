package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ProcedureDTO;
import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Services.CompanyService;
import com.mycompany.barber.Services.ProcedureService;
import com.mycompany.barber.Services.UserService;
import com.mycompany.barber.Utils.Procedure.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;
    private final CompanyService companyService;

    @Autowired
    public ProcedureController(ProcedureService procedureService, ModelMapper modelMapper, UserService userService, CompanyService companyService) {
        this.procedureService = procedureService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.companyService = companyService;
    }

    /**
     * Создать новую процедуру
     * @param userId
     * @param procedureDTO
     * @param model
     * @return
     */
    @GetMapping("/procedures/{userId}/new")
    public String newUProcedure(@PathVariable int userId, @ModelAttribute("procedure") ProcedureDTO procedureDTO, Model model) {

        User user = userService.findById(userId);
        String companyId = user.getUserCompany();
        model.addAttribute("user",user);
        model.addAttribute("company",companyService.findById(Integer.parseInt(companyId)));
//        model.addAttribute("allUsers", userService.findByUserCompany(companyId));
        return "Procedure/newProcedure";
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
        model.addAttribute("user",userService.findById(userId));
        model.addAttribute("allProcedures", procedureService.findAllForUser(userId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList()));
        return "Procedure/allProcedures";
    }

//    /**
//     * Получить список всех процедур для компании
//     *
//     * @param companyId
//     * @param model
//     * @return
//     */
//    @GetMapping("/procedures/company/{companyId}")
////    @Operation(summary = "Получить список всех процедур для компании")
//    public String allProceduresForCompany(@PathVariable("companyId") String companyId, Model model) {
//        model.addAttribute("allProcedures", procedureService.findByCompanyName(companyId).stream().map(this::convertToProcedureDTO).collect(Collectors.toList()));
//        return "Procedure/allProcedures";
//    }

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
        return "Procedure/editProcedure";
    }

    /**
     * Сохранить новую процедуру
     *
     * @param procedureDTO
     * @param bindingResult
     * @param userId
     * @return
     */
    @PostMapping("/procedures/user/{userId}")
//    @Operation(summary = "Сохранить новую процедуру")
    public String create(@ModelAttribute @Valid ProcedureDTO procedureDTO, BindingResult bindingResult,
                         @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new ProcedureNotCreatedException(errorMsg.toString());
        }
        procedureService.save(convertToProcedure(procedureDTO));
        return "redirect:/procedures/user/" + procedureDTO.getUserId();
    }

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
    public String updateProcedure(@Valid ProcedureDTO procedureDTO, BindingResult bindingResult, @PathVariable("procedureId") int procedureId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            throw new ProcedureNotUpdatedException(errorMsg.toString());
        }
        procedureDTO.setProcedureId(procedureId);
        procedureService.update(procedureId,convertToProcedure(procedureDTO));
        return  "redirect:/procedures/user/" + procedureDTO.getUserId();
    }

    /**
     * Удалить Процедуру
     *
     * @param procedureId
     * @return
     */
//    @Operation(summary = "Удалить Процедуру")
    @DeleteMapping("/procedures/user/{userId}/deleteProcedure/{procedureId}")
    public String delete(@PathVariable("procedureId") int procedureId, @PathVariable("userId") int userId) {
        procedureService.delete(procedureId);
        return "redirect:/procedures/user/" + userId;
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
