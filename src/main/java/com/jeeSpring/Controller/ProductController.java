package com.jeeSpring.Controller;

import com.jeeSpring.Business.ProductService;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/Products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody ProductEntity product) {
        productService.createProduct(product);
    }

    @GetMapping("{productId}")
    public ProductEntity getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("{productId}")
    public void updateProduct(@PathVariable Long productId, @RequestBody ProductEntity product) {
        productService.updateProduct(productId, product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
    }

    @GetMapping("/search")
    public List<ProductEntity> searchProductsByLabelAndDescription(@RequestParam String searchString) {
        return productService.searchProductsByLabelAndDescription(searchString);
    }

}
