package com.jeeSpring.Business;

import com.jeeSpring.Model.BankAccountEntity;
import com.jeeSpring.Repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankAccountEntity> getAllBanks(){
        return bankRepository.findAll();
    }

    public BankAccountEntity getBankById(Long id){
        return bankRepository.findById(id).orElse(null);
    }

    public void updateBank(Long id, BankAccountEntity bank) {
        bank.setBankId(id);
        bankRepository.save(bank);
    }

    public void createBank(BankAccountEntity bank) {
        bankRepository.save(bank);
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);;
    }

    public BankAccountEntity isAccountValid(long bankCode, String bankDate, int cvv) {
        return bankRepository.findByBankCodeAndBankDateAndCvv(bankCode, bankDate, cvv);
    }

    public void accountPay(long bankCode, int price) {
        BankAccountEntity bank = bankRepository.findByBankCode(bankCode);
        bank.setBankBalance(bank.getBankBalance() - price);
        bankRepository.save(bank);
    }
}
