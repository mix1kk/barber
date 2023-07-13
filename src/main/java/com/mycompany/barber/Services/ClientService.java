package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Repository.ClientRepository;
import com.mycompany.barber.Utils.Client.ClientNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Integer clientId) {
        return clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void update(int id, Client client) {
        client.setClientId(id);
        clientRepository.save(client);
    }

    public void delete(int id) {
        clientRepository.deleteById(id);
    }
}
