package com.jeeSpring.Controller;

import com.jeeSpring.Business.Services.BankService;
import com.jeeSpring.Model.BankAccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    public List<BankAccountEntity> getAllBanks(){
        return bankService.getAllBanks();
    }

    public void createBank(@RequestBody BankAccountEntity bank){
        bankService.createBank(bank);
    }

    public BankAccountEntity getBankById(@PathVariable Long bankId) {
        return bankService.getBankById(bankId);
    }

    public void updateBank(@RequestBody BankAccountEntity bank) {
        bankService.updateBank(bank);
    }

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
