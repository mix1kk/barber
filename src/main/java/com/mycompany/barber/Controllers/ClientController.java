package com.mycompany.barber.Controllers;

import com.mycompany.barber.DTO.ClientDTO;
import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Services.ClientService;
import com.mycompany.barber.Utils.Client.ClientErrorResponse;
import com.mycompany.barber.Utils.Client.ClientNotCreatedException;
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
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/user/{userId}")
    public List<ClientDTO> allClientsForUser(@PathVariable int userId) {
        return clientService.findAllForUser(userId).stream().map(this::convertToClientDTO).collect(Collectors.toList());
    }

    @GetMapping("/{clientId}")
    public ClientDTO singleClient(@PathVariable int clientId) {
        return convertToClientDTO(clientService.findById(clientId));
    }

    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(UserNotFoundException e) {
        ClientErrorResponse response = new ClientErrorResponse("Client not exist", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/{userId}")
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
    @ExceptionHandler
    private ResponseEntity<ClientErrorResponse> handleException(UserNotCreatedException e) {
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
