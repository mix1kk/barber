package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Client;
import com.mycompany.barber.Models.Procedure;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.ClientRepository;
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

    public List<Procedure> findAll() {
        return procedureRepository.findAll();
    }

    public Procedure findById(Integer procedureId) {
        return procedureRepository.findById(procedureId).orElseThrow(ProcedureNotFoundException::new);
    }

    public void save(Procedure procedure) {
        procedureRepository.save(procedure);
    }

    public void update(int id, Procedure procedure) {
        procedure.setProcedureId(id);
        procedureRepository.save(procedure);
    }

    public void delete(int id) {
        procedureRepository.deleteById(id);
    }
}
