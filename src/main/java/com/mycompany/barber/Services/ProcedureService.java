package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Repository.ProcedureRepository;
import com.mycompany.barber.Utils.Procedure.ProcedureNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class ProcedureService {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> findAllForUser(int userId) {
        return procedureRepository.findByUserId(userId);
    }

    public Procedure findById(Integer procedureId) {
        return procedureRepository.findById(procedureId).orElseThrow(ProcedureNotFoundException::new);
    }

    public void save(Procedure procedure) {
        fillProcedure(procedure);
        procedure.setProcedureId(0);
        procedureRepository.save(procedure);
    }

    public void update(int id, Procedure procedure) {
        procedure.setProcedureId(id);
        procedureRepository.save(procedure);
    }

    public void delete(int id) {
        procedureRepository.deleteById(id);
    }

    private void fillProcedure(Procedure procedure){
        procedure.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        procedure.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        procedure.setUpdatedBy("User");//TODO: сделать запись имени того, кто изменил поле
        procedure.setSpare1("spare1");
        procedure.setSpare2("spare2");
    }

}
