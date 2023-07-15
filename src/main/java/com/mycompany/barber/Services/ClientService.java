package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Models.Line;
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

    public List<Client> findAllForUser(int userId) {
        return clientRepository.findByUserId(userId);
    }

    public Client findById(Integer clientId) {
        return clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    }

    public void save(Client client) {
        fillClient(client);
        client.setClientId(0);
        clientRepository.save(client);
    }

    public void update(int id, Client client) {
        client.setClientId(id);
        clientRepository.save(client);
    }

    public void delete(int id) {
        clientRepository.deleteById(id);
    }
    private void fillClient(Client client){
        client.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        client.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        client.setUpdatedBy("User");//TODO: сделать запись имени того, кто изменил поле
        client.setSpare1("spare1");
        client.setSpare2("spare2");
    }
}
