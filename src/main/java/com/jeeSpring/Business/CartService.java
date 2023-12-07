package com.jeeSpring.Business;

import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import com.jeeSpring.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartEntity> getAllCarts(){
        return cartRepository.findAll();
    }

    public CartEntity getCartById(Long id){
        return cartRepository.findById(id).orElse(null);
    }

    public void updateCart(Long id, CartEntity cart) {
        cart.setCartId(id);
        cartRepository.save(cart);
    }

    public void createCart(CartEntity cart) {
        cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);;
    }

    public List<CartEntity> getCartsByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public CartEntity getCartByUserAndProduct(User user, ProductEntity product) {
        return  cartRepository.findByUserAndProduct(user, product);
    }
}
