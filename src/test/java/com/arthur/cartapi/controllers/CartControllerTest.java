package com.arthur.cartapi.controllers;

import com.arthur.cartapi.dtos.CreateCartDto;
import com.arthur.cartapi.dtos.ItemDto;
import com.arthur.cartapi.models.Cart;
import com.arthur.cartapi.models.Item;
import com.arthur.cartapi.services.CartServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private CartServices cartServices;

    @InjectMocks
    private cartController cartController;

    private Cart mockCart;
    private CreateCartDto mockCreateCartDto;
    private List<Cart> mockCartList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockCart = new Cart();
        mockCart.setId(1L);
        mockCart.setUserId(1L);
        mockCart.setDate("2024-01-17");

        Item item = new Item();
        item.setProductId(1L);
        item.setQuantity(2L);

        List<Item> items = new ArrayList<>();
        items.add(item);
        mockCart.setItemList(items);

        mockCreateCartDto = new CreateCartDto();
        mockCreateCartDto.setUserId(1L);
        mockCreateCartDto.setDate("2024-01-17");

        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(1L);
        itemDto.setQuantity(2L);

        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        mockCreateCartDto.setProducts(itemDtos);

        mockCartList = new ArrayList<>();
        mockCartList.add(mockCart);
    }

    @Test
    void testGetAllCarts() {
        when(cartServices.getAllCarts()).thenReturn(mockCartList);

        List<Cart> result = cartController.getAllCarts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(1L, result.get(0).getUserId());
        verify(cartServices, times(1)).getAllCarts();
    }

    @Test
    void testGetCartById() {
        Long cartId = 1L;
        when(cartServices.getCartById(cartId)).thenReturn(mockCart);

        Cart result = cartController.getCartById(cartId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        assertEquals("2024-01-17", result.getDate());
        assertEquals(1, result.getItemList().size());
        verify(cartServices, times(1)).getCartById(cartId);
    }

    @Test
    void testGetCartByUserId() {
        Long userId = 1L;
        when(cartServices.getCartByUserId(userId)).thenReturn(mockCartList);

        List<Cart> result = cartController.getCartByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getUserId());
        verify(cartServices, times(1)).getCartByUserId(userId);
    }

    @Test
    void testGetCartInDateRange() {
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        when(cartServices.getCartInDateRange(startDate, endDate)).thenReturn(mockCartList);

        List<Cart> result = cartController.getCartInDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(cartServices, times(1)).getCartInDateRange(startDate, endDate);
    }

    @Test
    void testCreateCart() {
        when(cartServices.createCart(mockCreateCartDto)).thenReturn(mockCart);

        Cart result = cartController.createCart(mockCreateCartDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        verify(cartServices, times(1)).createCart(mockCreateCartDto);
    }

    @Test
    void testUpdateCart() {
        Long cartId = 1L;
        when(cartServices.updateCart(mockCreateCartDto, cartId)).thenReturn(mockCart);

        Cart result = cartController.updateCart(mockCreateCartDto, cartId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(cartServices, times(1)).updateCart(mockCreateCartDto, cartId);
    }

    @Test
    void testDeleteCart() {
        Long cartId = 1L;
        when(cartServices.deleteCart(cartId)).thenReturn(mockCart);

        Cart result = cartController.deleteCart(cartId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(cartServices, times(1)).deleteCart(cartId);
    }

    @Test
    void testGetAllCartsEmpty() {
        when(cartServices.getAllCarts()).thenReturn(new ArrayList<>());

        List<Cart> result = cartController.getAllCarts();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(cartServices, times(1)).getAllCarts();
    }
}
