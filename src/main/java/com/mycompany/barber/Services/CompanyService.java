package com.mycompany.barber.Services;

import com.mycompany.barber.Models.Company;
import com.mycompany.barber.Models.User;
import com.mycompany.barber.Repository.CompanyRepository;
import com.mycompany.barber.Utils.Company.CompanyNotDeletedException;
import com.mycompany.barber.Utils.Company.CompanyNotFoundException;
import com.mycompany.barber.Utils.User.UserNotDeletedException;
import com.mycompany.barber.Utils.User.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;

    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Integer companyId) {
        return companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
    }

    public void save(Company company) {
        fillCompany(company);
        companyRepository.save(company);
    }

    public void update(int companyId, Company company) {
        String createdAt = companyRepository.findById(companyId).orElseThrow(UserNotFoundException::new).getCreatedAt();
        fillCompany(company);
        company.setCreatedAt(createdAt);
        company.setCompanyId(companyId);
        companyRepository.save(company);
    }

    public void delete(int companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new CompanyNotDeletedException("Не существует такой компании");
        }
        companyRepository.deleteById(companyId);
        if (companyRepository.existsById(companyId)) {
            throw new CompanyNotDeletedException("Не удалось удалить компанию");
        }
    }

    private void fillCompany(Company company) {
        if (company.getCreatedAt() == null) {
            company.setCreatedAt(String.valueOf(System.currentTimeMillis()));
        }
        company.setUpdatedAt(String.valueOf(System.currentTimeMillis()));
        company.setUpdatedBy(company.getCompanyName());//TODO: сделать запись имени того, кто изменил поле
    }
    public static HashMap<String,String> convertToCompanyMap(List<Company> companyList){
        HashMap<String,String> resultMap = new HashMap<>();
        for (Company company : companyList){
            resultMap.put(String.valueOf(company.getCompanyId()),company.getCompanyName());
        }
        return resultMap;
    }
}
