package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ClientDTO;
import com.mycompany.barber.DTO.CompanyDTO;
import com.mycompany.barber.DTO.UserDTO;
import com.mycompany.barber.Models.Company;
import com.mycompany.barber.Services.CompanyService;
import com.mycompany.barber.Utils.Client.ClientNotCreatedException;
import com.mycompany.barber.Utils.Client.ClientNotUpdatedException;
import com.mycompany.barber.Utils.Company.CompanyNotCreatedException;
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
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }


    /**
     * Получить список всех компаний
     */
    @GetMapping()
    public String allCompanies(Model model) {
        model.addAttribute("allCompanies", companyService.findAll().stream().map(this::convertToCompanyDTO).collect(Collectors.toList()));
        return "Company/allCompanies";
    }
    /**
     * Получить клиента по id
     */
    @GetMapping("/{companyId}")
    public String singleCompany(@PathVariable("companyId") int companyId, Model model) {
        model.addAttribute("company", convertToCompanyDTO(companyService.findById(companyId)));
        return "Company/singleCompany";
    }
    /**
     * Создать новую компанию
     */
    @GetMapping("/new")
    public String newCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        return "Company/newCompany";
    }

    @PostMapping("/new")
    public String createCompany(@ModelAttribute("company") @Valid CompanyDTO companyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            //throw new UserNotCreatedException(errorMsg.toString());
            return "Company/newCompany";
        }
        companyService.save(convertToCompany(companyDTO));
        return "redirect:/companies";
    }

    /**
     * Редактировать компанию
     */

    @GetMapping("/company/{id}/edit")
    public String editCompany(Model model, @PathVariable("id") int companyId) {
        model.addAttribute("company", convertToCompanyDTO(companyService.findById(companyId)));
        return "Company/editCompany";
    }

    @PatchMapping("/company/{id}/edit")
    public String updateCompany(@ModelAttribute("company") @Valid CompanyDTO companyDTO, BindingResult bindingResult, @PathVariable("id") int companyId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            //throw new UserNotCreatedException(errorMsg.toString());
            return "Company/editCompany";
        }
        companyService.update(companyId, convertToCompany(companyDTO));
        return "redirect:/companies";
    }


    /**
     * Удаление компании
     */
    @DeleteMapping("/company/{companyId}")
    public String deleteUser(@PathVariable("companyId") int companyId) {
        companyService.delete(companyId);
        return "redirect:/companies";
    }
    private Company convertToCompany(CompanyDTO companyDTO) {
        return modelMapper.map(companyDTO, Company.class);
    }

    private CompanyDTO convertToCompanyDTO(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }
}
