package com.jeeSpring.Controller;

import com.jeeSpring.Business.Services.CartService;
import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    public List<CartEntity> getAllCarts(){
        return cartService.getAllCarts();
    }


    public void createCart(CartEntity cart){
        cartService.createCart(cart);
    }


    public CartEntity getCartById(Long cartId) {
        return cartService.getCartById(cartId);
    }


    public void updateCart(CartEntity cart) {
        cartService.updateCart(cart);
    }

    public void deleteCart(Long cartId){
        cartService.deleteCart(cartId);
    }
    /*
    public List<CartEntity> getCartsByUserId(long userId) {
        return cartService.getCartsByUserId(userId);
    }

    public CartEntity getCartByUserAndProduct(long userId, long productId){
        return cartService.getCartByUserAndProduct(userId, productId);
    } */


    public List<CartEntity> getCartsByUser(User user) {
        return cartService.getCartsByUser(user);
    }


    public CartEntity getCartByUserAndProduct(User user, ProductEntity product){
        return cartService.getCartByUserAndProduct(user, product);
    }

}
