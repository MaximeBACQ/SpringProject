package com.jeeSpring.Controller;

import com.jeeSpring.Business.CartService;
import com.jeeSpring.Model.CartEntity;
import com.jeeSpring.Model.ProductEntity;
import com.jeeSpring.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/Carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartEntity> getAllCarts(){
        return cartService.getAllCarts();
    }

    @PostMapping
    public void createCart(@RequestBody CartEntity cart){
        cartService.createCart(cart);
    }

    @GetMapping("{cartId}")
    public CartEntity getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PutMapping("{cartId}")
    public void updateCart(@PathVariable Long cartId, @RequestBody CartEntity cart) {
        cartService.updateCart(cartId, cart);
    }

    @DeleteMapping(path="{cartId}")
    public void deleteCart(@PathVariable("cartId") Long cartId){
        cartService.deleteCart(cartId);
    }
    /*
    public List<CartEntity> getCartsByUserId(long userId) {
        return cartService.getCartsByUserId(userId);
    }

    public CartEntity getCartByUserAndProduct(long userId, long productId){
        return cartService.getCartByUserAndProduct(userId, productId);
    } */

    @PostMapping("/getCarts")
    public List<CartEntity> getCartsByUser(@RequestBody User user) {
        return cartService.getCartsByUser(user);
    }


    public CartEntity getCartByUserAndProduct(User user, ProductEntity product){
        return cartService.getCartByUserAndProduct(user, product);
    }

}
