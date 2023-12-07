package com.jeeSpring.Business.Services;

import com.jeeSpring.Model.CompanyEntity;
import com.jeeSpring.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> getAllCompanies(){
        return companyRepository.findAll();
    }

    public CompanyEntity getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public void updateCompany(CompanyEntity company) {
        companyRepository.save(company);
    }

    public void createCompany(CompanyEntity company) {
        companyRepository.save(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);;
    }
}
