package com.example.jeeSpring.SiteUser;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity(name="users")
@Table(name="users",uniqueConstraints={
        @UniqueConstraint(name="users_email_unique",columnNames="email")
})
public class SiteUser {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName="user_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator="user_sequence"
    )
    @Column(name="user_id",updatable=false)
    //sequence = another table in db that contains the value to use as the next id for every user
    private Long userId;
    @Column(name="name",updatable=false,columnDefinition="TEXT")
    private String name;
    @Column(name="surname",updatable=false,columnDefinition="TEXT")
    private String surname;
    @Column(name="username",columnDefinition="TEXT")
    private String username;
    @Column(name="email",columnDefinition="TEXT",unique=true)
    private String email;
    @Column(name="birth_date",updatable=false,columnDefinition="DATE")
    private LocalDate birthDate;
    @Column(name="gender")
    private String gender;
    @Column(name="password")
    private String password;
    @Column(name="is_admin")
    private boolean isAdmin;
    @Column(name="is_moderator")
    private boolean isModerator;
    @Column(name="is_seller")
    private boolean isSeller;
//    @ManyToOne
//    @JoinColumn(name = "companyId")
//    private CompanyEntity company;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "bankId")
//    private BankAccountEntity bank;

    public SiteUser(){

    }
    public SiteUser(String name, String surname, String username, String email, LocalDate birthDate, String gender,
                    String password, boolean isAdmin, boolean isModerator,boolean isSeller){          //our needed constructor
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email=email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isModerator = isModerator;
        this.isSeller=isSeller;
//        this.company = null;
//        this.bank = null;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getIsModerator() {return isModerator;}

    public void setIsModerator(boolean moderator) {isModerator = moderator;}

    public boolean getIsSeller(){return isSeller;}
    public void setIsSeller(boolean seller){isSeller = seller;}

//    public CompanyEntity getCompany() {
//        return company;
//    }
//
//    public void setCompany(CompanyEntity company) {
//        this.company = company;
//    }
//
//    public BankAccountEntity getBank() {
//        return bank;
//    }
//
//    public void setBank(BankAccountEntity bank) {
//        this.bank = bank;
//    }

    @Override
    public String toString() {
        String out =
                "SiteUser{" +
                        "userId=" + userId +
                        ", name='" + name + '\'' +
                        ", surname='" + surname + '\'' +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", birthDate=" + birthDate +
                        ", gender='" + gender + '\'' +
                        ", password='" + password + '\'' +
                        ", isAdmin=" + isAdmin +
                        ", isModerator=" + isModerator +
                        ", isSeller=" + isSeller;
        return out;
//        if(company != null){
//            out +=  ", companyId=" + company.getCompanyId();
//        }
//
//        if(bank == null){
//            return out + '}';
//        }else{
//            return out + ", bankId=" + bank.getBankId() + '}';
//        }

    }
}
