package com.arthur.cartapi.services;

import com.arthur.cartapi.dtos.CreateCartDto;
import com.arthur.cartapi.dtos.GetCartDto;
import com.arthur.cartapi.dtos.ItemDto;
import com.arthur.cartapi.models.Cart;
import com.arthur.cartapi.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Cart Services Test Suite")
class CartServicesTest {

    private CartServices cartServices;
    private RestTemplate mockRestTemplate;

    private GetCartDto mockGetCartDto;
    private CreateCartDto mockCreateCartDto;

    @BeforeEach
    void setUp() {
        mockRestTemplate = mock(RestTemplate.class);
        cartServices = new CartServices();
        ReflectionTestUtils.setField(cartServices, "restTemplate", mockRestTemplate);

        mockGetCartDto = new GetCartDto();
        mockGetCartDto.setId(1L);
        mockGetCartDto.setUserId(1L);
        mockGetCartDto.setDate("2024-01-17");

        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(1L);
        itemDto.setQuantity(2L);

        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto);
        mockGetCartDto.setProducts(itemDtos);

        mockCreateCartDto = new CreateCartDto();
        mockCreateCartDto.setUserId(1L);
        mockCreateCartDto.setDate("2024-01-17");
        mockCreateCartDto.setProducts(itemDtos);
    }

    @Test
    @DisplayName("Should return all carts successfully")
    void testGetAllCarts() {
        GetCartDto[] mockCarts = {mockGetCartDto};
        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                GetCartDto[].class
        )).thenReturn(mockCarts);

        List<Cart> result = cartServices.getAllCarts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(1L, result.get(0).getUserId());
        assertEquals("2024-01-17", result.get(0).getDate());
        assertEquals(1, result.get(0).getItemList().size());
        verify(mockRestTemplate, times(1)).getForObject(
                "https://fakestoreapi.com/carts",
                GetCartDto[].class
        );
    }

    @Test
    @DisplayName("Should return cart by ID successfully")
    void testGetCartById() {
        Long cartId = 1L;
        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts/" + cartId,
                GetCartDto.class
        )).thenReturn(mockGetCartDto);

        Cart result = cartServices.getCartById(cartId);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getUserId());
        assertEquals("2024-01-17", result.getDate());
        assertEquals(1, result.getItemList().size());
        assertEquals(1L, result.getItemList().get(0).getProductId());
        assertEquals(2L, result.getItemList().get(0).getQuantity());
    }

    @Test
    @DisplayName("Should return carts in date range successfully")
    void testGetCartInDateRange() {
        String startDate = "2024-01-01";
        String endDate = "2024-01-31";
        GetCartDto[] mockCarts = {mockGetCartDto};

        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts?startdate=" + startDate + "&enddate=" + endDate,
                GetCartDto[].class
        )).thenReturn(mockCarts);

        List<Cart> result = cartServices.getCartInDateRange(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("2024-01-17", result.get(0).getDate());
        verify(mockRestTemplate, times(1)).getForObject(
                "https://fakestoreapi.com/carts?startdate=" + startDate + "&enddate=" + endDate,
                GetCartDto[].class
        );
    }

    @Test
    @DisplayName("Should return carts by user ID successfully")
    void testGetCartByUserId() {
        Long userId = 1L;
        GetCartDto[] mockCarts = {mockGetCartDto};

        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts/user/" + userId,
                GetCartDto[].class
        )).thenReturn(mockCarts);

        List<Cart> result = cartServices.getCartByUserId(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getUserId());
        verify(mockRestTemplate, times(1)).getForObject(
                "https://fakestoreapi.com/carts/user/" + userId,
                GetCartDto[].class
        );
    }

    @Test
    @DisplayName("Should handle empty cart list")
    void testGetAllCartsEmpty() {
        GetCartDto[] mockCarts = {};
        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts",
                GetCartDto[].class
        )).thenReturn(mockCarts);

        List<Cart> result = cartServices.getAllCarts();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    @DisplayName("Should handle multiple items in cart")
    void testGetCartWithMultipleItems() {
        ItemDto itemDto1 = new ItemDto();
        itemDto1.setProductId(1L);
        itemDto1.setQuantity(2L);

        ItemDto itemDto2 = new ItemDto();
        itemDto2.setProductId(2L);
        itemDto2.setQuantity(3L);

        List<ItemDto> itemDtos = new ArrayList<>();
        itemDtos.add(itemDto1);
        itemDtos.add(itemDto2);

        GetCartDto cartWithMultipleItems = new GetCartDto();
        cartWithMultipleItems.setId(1L);
        cartWithMultipleItems.setUserId(1L);
        cartWithMultipleItems.setDate("2024-01-17");
        cartWithMultipleItems.setProducts(itemDtos);

        when(mockRestTemplate.getForObject(
                "https://fakestoreapi.com/carts/1",
                GetCartDto.class
        )).thenReturn(cartWithMultipleItems);

        Cart result = cartServices.getCartById(1L);

        assertNotNull(result);
        assertEquals(2, result.getItemList().size());
        assertEquals(1L, result.getItemList().get(0).getProductId());
        assertEquals(2L, result.getItemList().get(1).getProductId());
    }
}
