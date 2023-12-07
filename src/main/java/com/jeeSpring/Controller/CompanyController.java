package com.jeeSpring.Controller;

import com.jeeSpring.Business.Services.CompanyService;
import com.jeeSpring.Model.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    public List<CompanyEntity> getAllCompanies(){
        return companyService.getAllCompanies();
    }


    public void createCompany(CompanyEntity company){
        companyService.createCompany(company);
    }


    public CompanyEntity getCompanyById(Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    public void updateCompany(CompanyEntity company) {
        companyService.updateCompany(company);
    }

    public void deleteCompany(Long companyId){
        companyService.deleteCompany(companyId);
    }
}
