package com.mycompany.barber.Repository;



import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    List<Procedure> findByUserId(int userId);
    List<Procedure> findByCompanyName(String companyName);
}
