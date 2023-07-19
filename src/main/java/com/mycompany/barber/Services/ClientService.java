package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Models.Line;
import com.mycompany.barber.Repository.ClientRepository;
import com.mycompany.barber.Utils.Client.ClientNotDeletedException;
import com.mycompany.barber.Utils.Client.ClientNotFoundException;
import com.mycompany.barber.Utils.User.UserNotDeletedException;
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
    public List<Client> findAllForCompany(String companyName) {
        return clientRepository.findByCompanyName(companyName);
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
        fillClient(client);
        client.setClientId(id);
        clientRepository.save(client);
    }

    public void delete(int id) {
        if(!clientRepository.existsById(id)){
            throw new ClientNotDeletedException("Не существует клиента");
        }
        clientRepository.deleteById(id);
        if(clientRepository.existsById(id)){
            throw new ClientNotDeletedException("Не удалось удалить клиента");
        }
    }
    private void fillClient(Client client){
        client.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        client.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        client.setUpdatedBy("User");//TODO: сделать запись имени того, кто изменил поле
        client.setSpare1("spare1");
        client.setSpare2("spare2");
    }
}
