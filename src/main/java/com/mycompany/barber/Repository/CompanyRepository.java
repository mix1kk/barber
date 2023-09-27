package com.mycompany.barber.Repository;

import com.mycompany.barber.Models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    List<Company> findByUserId(int userId);
}
