package com.mycompany.barber.Repository;


import com.mycompany.barber.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
