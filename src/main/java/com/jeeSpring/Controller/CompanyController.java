package com.jeeSpring.Controller;

import com.jeeSpring.Business.CompanyService;
import com.jeeSpring.Model.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/Companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyEntity> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping
    public void createCompany(@RequestBody CompanyEntity company){
        companyService.createCompany(company);
    }

    @GetMapping("{companyId}")
    public CompanyEntity getCompanyById(@PathVariable Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    @PutMapping("{companyId}")
    public void updateCompany(@PathVariable Long companyId, @RequestBody CompanyEntity company) {
        companyService.updateCompany(companyId, company);
    }

    @DeleteMapping(path="{companyId}")
    public void deleteCompany(@PathVariable("companyId") Long companyId){
        companyService.deleteCompany(companyId);
    }
}
