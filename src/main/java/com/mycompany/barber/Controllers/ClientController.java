package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ClientDTO;
import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Services.ClientService;
import com.mycompany.barber.Utils.Client.ClientErrorResponse;
import com.mycompany.barber.Utils.Client.ClientNotCreatedException;
import com.mycompany.barber.Utils.Client.ClientNotDeletedException;
import com.mycompany.barber.Utils.Client.ClientNotUpdatedException;
import com.mycompany.barber.Utils.User.*;
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
@RequestMapping("/clients")
@Tag(name = "Контроллер клиентов", description = "Позволяет добавлять, удалять, редактировать клиентов")
public class ClientController {
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Получить список всех клиентов для пользователя с идентификатором")
    @GetMapping("/user/{userId}")
    public List<ClientDTO> allClientsForUser(@PathVariable int userId) {
        return clientService.findAllForUser(userId).stream().map(this::convertToClientDTO).collect(Collectors.toList());
    }

    @Operation(summary = "Получить список всех клиентов для компании")
    @GetMapping("/company/{companyName}")
    public List<ClientDTO> allClientsForUser(@PathVariable("companyName") String companyName) {
        return clientService.findAllForCompany(companyName).stream().map(this::convertToClientDTO).collect(Collectors.toList());
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Получить клиента по id")
    public ClientDTO singleClient(@PathVariable int clientId) {
        return convertToClientDTO(clientService.findById(clientId));
    }


    @PostMapping("/user/{userId}")
    @Operation(summary = "Создать нового клиента для пользователя с идентификатором")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult,
                                             @PathVariable int userId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new ClientNotCreatedException(errorMsg.toString());
        }
        clientDTO.setUserId(userId);
        clientService.save(convertToClient(clientDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Редактировать клиента")
    @PatchMapping("/{clientId}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult, @PathVariable("clientId") int clientId) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("<br>");
            }
            System.out.println(errorMsg);
            throw new ClientNotUpdatedException(errorMsg.toString());
        }

        clientService.update(clientId, convertToClient(clientDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удалить клиента")
    @DeleteMapping("{clientId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("clientId") int clientId) {
        clientService.delete(clientId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotDeletedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(UserNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse("Client not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotUpdatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(ClientNotCreatedException e) {
        ClientErrorResponse response = new ClientErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO, Client.class);
    }

    private ClientDTO convertToClientDTO(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
}
