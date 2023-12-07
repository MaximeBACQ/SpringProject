package com.jeeSpring.Controller;

import com.jeeSpring.Business.Services.BankService;
import com.jeeSpring.Model.BankAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/Banks")
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankAccountEntity> getAllBanks(){
        return bankService.getAllBanks();
    }

    @PostMapping
    public void createBank(@RequestBody BankAccountEntity bank){
        bankService.createBank(bank);
    }

    @GetMapping("{bankId}")
    public BankAccountEntity getBankById(@PathVariable Long bankId) {
        return bankService.getBankById(bankId);
    }

    @PutMapping("{bankId}")
    public void updateBank(@RequestBody BankAccountEntity bank) {
        bankService.updateBank(bank);
    }

    @DeleteMapping(path="{bankId}")
    public void deleteBank(@PathVariable("bankId") Long bankId){
        bankService.deleteBank(bankId);
    }


    public BankAccountEntity isAccountValid(long bankCode, String bankDate, int cvv){
        return bankService.isAccountValid(bankCode, bankDate, cvv);
    }

    public void accountPay(long bankCode, int price){
        bankService.accountPay(bankCode, price);
    }


}
