package com.jeeSpring.Repository;

import com.jeeSpring.Model.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankAccountEntity, Long> {
    BankAccountEntity findByBankCodeAndBankDateAndCvv(long bankCode, String bankDate, int cvv);

    BankAccountEntity findByBankCode(long bankCode);

}
