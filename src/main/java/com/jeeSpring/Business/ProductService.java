package com.jeeSpring.Business;

import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Repository.ProductRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProducts(){
        return productRepository.findAll();
    }

    public ProductEntity getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Long id, ProductEntity product) {
        product.setProductId(id);
        productRepository.save(product);
    }

    public void createProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);;
    }

    public List<ProductEntity> searchProductsByLabelAndDescription(String searchString) {
        return productRepository.findByLabelContainingOrDescriptionContaining(searchString, searchString);
    }

}
