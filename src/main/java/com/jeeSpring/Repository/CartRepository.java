package com.jeeSpring.Repository;

import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    /* List<CartEntity> findByUser_id(long userId);

    CartEntity findByUser_idAndProduct_id(long userId, long productId); */
    List<CartEntity> findByUser(User user);

    CartEntity findByUserAndProduct(User user, ProductEntity product);

}
