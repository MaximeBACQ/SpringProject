package com.jeeSpring.Model;

import jakarta.persistence.*;

@Entity
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long bankId;
    private long bankCode;
    private String bankDate;
    private int cvv;
    private int bankBalance;

    public BankAccountEntity(){};
    public BankAccountEntity(long bankCode, String bankDate, int cvv, int bankBalance){
        this.bankCode = bankCode;
        this.bankDate = bankDate;
        this.cvv = cvv;
        this.bankBalance = bankBalance;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public long getBankCode() {
        return bankCode;
    }

    public void setBankCode(long bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankDate() {
        return bankDate;
    }

    public void setBankDate(String bankDate) {
        this.bankDate = bankDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }
    @Override
    public String toString() {
        return "bankAccount{"+
                "bankId=" + bankId +
                "bankCode=" + bankCode +
                "bankDate=" + bankDate +
                "cvv" + bankBalance +
                "bankBalance" + bankBalance +
                "}";
    }
}
