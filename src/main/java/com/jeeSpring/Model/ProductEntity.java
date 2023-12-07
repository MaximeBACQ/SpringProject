package com.jeeSpring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long productId;
    private String label;
    private int price;
    private int stock;
    private String description;
    private String productImage;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    public ProductEntity(){}


    public ProductEntity(String label, int price, int stock, String description, String productImage, CompanyEntity company){
        this.label = label;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.company = company;
        this.productImage = productImage;
    }
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {return productImage;}

    public void setProductImage(String productImage) {this.productImage = productImage;}

    @JsonIgnore
    public CompanyEntity getCompanyId() {
        return company;
    }

    public void setCompanyId(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "productId=" + productId +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                ", image link" + productImage +
                '}';
    }
}
