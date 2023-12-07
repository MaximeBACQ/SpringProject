package com.jeeSpring.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long companyId;
    private String name;
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<User> workers;

    public CompanyEntity(){}
    public CompanyEntity(String name){
        this.name = name;
        this.products = null;
        this.workers = null;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    @JsonIgnore
    public List<User> getUserId() {
        return workers;
    }

    public void setUserId(List<User> users) {
        this.workers = users;
    }

    @Override
    public String toString() {
        return "CompanyEntity{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                //", products=" + products.toString() + '\'' +
                //", products=" + workers.toString() + '\'' +
                '}';
    }
}
