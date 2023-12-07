package com.jeeSpring.Controller;

import com.jeeSpring.Business.Services.ProductService;
import com.jeeSpring.Model.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }


    public void createProduct(ProductEntity product) {
        productService.createProduct(product);
    }


    public ProductEntity getProductById(Long productId) {
        return productService.getProductById(productId);
    }


    public void updateProduct(ProductEntity product) {
        productService.updateProduct(product);
    }


    public void deleteProduct(Long productId) {
        productService.deleteProduct(productId);
    }


    public List<ProductEntity> searchProductsByLabelAndDescription(String searchString) {
        return productService.searchProductsByLabelAndDescription(searchString);
    }

    public List<ProductEntity> searchProductsByLabel(String searchString) {
        return productService.searchProductsByLabel(searchString);
    }

}
