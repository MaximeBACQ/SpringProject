package com.jeeSpring.Repository;

import com.jeeSpring.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByLabelContainingOrDescriptionContaining(String label, String description);
}
