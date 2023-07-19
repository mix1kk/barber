package com.mycompany.barber.Repository;


import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Models.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findByUserId(int userId);

    List<Client> findByCompanyName(String companyName);
}
