package com.arthur.cartapi.services;

import com.arthur.cartapi.dtos.CreateCartDto;
import com.arthur.cartapi.dtos.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arthur.cartapi.models.Item;
import com.arthur.cartapi.models.Cart;
import com.arthur.cartapi.dtos.GetCartDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServices {
    RestTemplate restTemplate = new RestTemplate();

    public List<Cart> getAllCarts() {
        GetCartDto[] allCarts = restTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                GetCartDto[].class
        );

        List<Cart> cartList = new ArrayList<Cart>();

        for (GetCartDto cart : allCarts) {
            Cart newCart = new Cart();
            newCart.setId(cart.getId());
            newCart.setUserId(cart.getUserId());
            newCart.setDate(cart.getDate());
            List<Item> items = new ArrayList<>();
            for(ItemDto itemDto: cart.getProducts()){
                Item item = new Item();
                item.setProductId(itemDto.getProductId());
                item.setQuantity(itemDto.getQuantity());
                items.add(item);
            }
            newCart.setItemList(items);
            cartList.add(newCart);
        }
        return cartList;
    }

    public Cart getCartById(Long id) {
        GetCartDto cart = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + id,
                GetCartDto.class
        );

        Cart newCart = new Cart();
        newCart.setId(cart.getId());
        newCart.setUserId(cart.getUserId());
        newCart.setDate(cart.getDate());
        List<Item> items = new ArrayList<>();
        for(ItemDto itemDto: cart.getProducts()){
            Item item = new Item();
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            items.add(item);
        }
        newCart.setItemList(items);
        return newCart;
    }

    public List<Cart> getCartInDateRange(String startDate, String endDate) {
        GetCartDto[] allCarts = restTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate=" + startDate + "&enddate=" + endDate,
                GetCartDto[].class
        );

        List<Cart> cartList = new ArrayList<Cart>();

        for (GetCartDto cart : allCarts) {
            Cart newCart = new Cart();
            newCart.setId(cart.getId());
            newCart.setUserId(cart.getUserId());
            newCart.setDate(cart.getDate());
            List<Item> items = new ArrayList<>();
            for(ItemDto itemDto: cart.getProducts()){
                Item item = new Item();
                item.setProductId(itemDto.getProductId());
                item.setQuantity(itemDto.getQuantity());
                items.add(item);
            }
            newCart.setItemList(items);
            cartList.add(newCart);
        }
        return cartList;
    }

    public List<Cart> getCartByUserId(Long id) {
        GetCartDto[] allCarts = restTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + id,
                GetCartDto[].class
        );

        List<Cart> cartList = new ArrayList<Cart>();

        for (GetCartDto cart : allCarts) {
            Cart newCart = new Cart();
            newCart.setId(cart.getId());
            newCart.setUserId(cart.getUserId());
            newCart.setDate(cart.getDate());
            List<Item> items = new ArrayList<>();
            for(ItemDto itemDto: cart.getProducts()){
                Item item = new Item();
                item.setProductId(itemDto.getProductId());
                item.setQuantity(itemDto.getQuantity());
                items.add(item);
            }
            newCart.setItemList(items);
            cartList.add(newCart);
        }
        return cartList;
    }

    public Cart createCart(CreateCartDto cart){
        GetCartDto resultCartDto = restTemplate.postForObject(
                "https://fakestoreapi.com/carts",
                cart,
                GetCartDto.class
        );

        Cart newCart = new Cart();
        newCart.setId(resultCartDto.getId());
        newCart.setUserId(resultCartDto.getUserId());
        newCart.setDate(resultCartDto.getDate());
        List<Item> items = new ArrayList<>();
        for(ItemDto itemDto: resultCartDto.getProducts()){
            Item item = new Item();
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            items.add(item);
        }
        newCart.setItemList(items);

        return newCart;
    }

    public Cart updateCart(CreateCartDto cart, Long id){
        restTemplate.put(
                "https://fakestoreapi.com/carts/" + id,
                cart
        );

        Cart newCart = new Cart();
        newCart.setId(id);
        newCart.setUserId(cart.getUserId());
        newCart.setDate(cart.getDate());
        List<Item> items = new ArrayList<>();
        for(ItemDto itemDto: cart.getProducts()){
            Item item = new Item();
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            items.add(item);
        }
        newCart.setItemList(items);

        return newCart;
    }

    public Cart deleteCart(Long id){
        Cart cart = this.getCartById(id);
        restTemplate.delete(
                "https://fakestoreapi.com/carts/" + id
            );

        return cart;
    }

}
