package com.arthur.cartapi.controllers;

import com.arthur.cartapi.dtos.CreateCartDto;
import com.arthur.cartapi.models.Cart;
import com.arthur.cartapi.services.CartServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class cartController {

    CartServices cartServices;
    public cartController(CartServices cartServices){
        this.cartServices = cartServices;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @GetMapping("/carts")
    public List<Cart> getAllCarts(){
        return cartServices.getAllCarts();
    }

    @GetMapping("/carts/{id}")
    public Cart getCartById(@PathVariable Long id){
        return cartServices.getCartById(id);
    }

    @GetMapping("/carts?startdate={startDate}&enddatde={endDate}")
    public List<Cart> getCartInDateRange(@PathVariable String startDate, @PathVariable String endDate){
        return cartServices.getCartInDateRange(startDate, endDate);
    }

    @GetMapping("/carts/user/{id}")
    public List<Cart> getCartByUserId(@PathVariable Long id){
        return cartServices.getCartByUserId(id);
    }

    @PostMapping("/carts")
    public Cart createCart(@RequestBody CreateCartDto cart){
        return cartServices.createCart(cart);
    }

    @PutMapping("/carts/{id}")
    public Cart updateCart(@RequestBody CreateCartDto cart, @PathVariable Long id){
        return cartServices.updateCart(cart, id);
    }

    @DeleteMapping("/carts/{id}")
    public Cart deleteCart(@PathVariable Long id){
        return cartServices.deleteCart(id);
    }



}
