package com.arthur.cartapi.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CreateCartDto Test Suite")
class CreateCartDtoTest {

    private CreateCartDto createCartDto;
    private List<ItemDto> products;

    @BeforeEach
    void setUp() {
        createCartDto = new CreateCartDto();
        products = new ArrayList<>();
    }

    @Test
    @DisplayName("Should set and get user ID")
    void testSetAndGetUserId() {
        Long expectedUserId = 10L;
        createCartDto.setUserId(expectedUserId);

        assertEquals(expectedUserId, createCartDto.getUserId());
    }

    @Test
    @DisplayName("Should set and get date")
    void testSetAndGetDate() {
        String expectedDate = "2024-01-17";
        createCartDto.setDate(expectedDate);

        assertEquals(expectedDate, createCartDto.getDate());
    }

    @Test
    @DisplayName("Should set and get products")
    void testSetAndGetProducts() {
        ItemDto itemDto1 = new ItemDto();
        itemDto1.setProductId(1L);
        itemDto1.setQuantity(2L);

        ItemDto itemDto2 = new ItemDto();
        itemDto2.setProductId(2L);
        itemDto2.setQuantity(3L);

        products.add(itemDto1);
        products.add(itemDto2);
        createCartDto.setProducts(products);

        assertNotNull(createCartDto.getProducts());
        assertEquals(2, createCartDto.getProducts().size());
        assertEquals(1L, createCartDto.getProducts().get(0).getProductId());
        assertEquals(2L, createCartDto.getProducts().get(1).getProductId());
    }

    @Test
    @DisplayName("Should handle empty products list")
    void testEmptyProductsList() {
        createCartDto.setProducts(new ArrayList<>());

        assertNotNull(createCartDto.getProducts());
        assertEquals(0, createCartDto.getProducts().size());
    }

    @Test
    @DisplayName("Should set all properties successfully")
    void testSetAllProperties() {
        Long expectedUserId = 100L;
        String expectedDate = "2024-02-20";

        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(5L);
        itemDto.setQuantity(10L);
        products.add(itemDto);

        createCartDto.setUserId(expectedUserId);
        createCartDto.setDate(expectedDate);
        createCartDto.setProducts(products);

        assertEquals(expectedUserId, createCartDto.getUserId());
        assertEquals(expectedDate, createCartDto.getDate());
        assertEquals(1, createCartDto.getProducts().size());
        assertEquals(5L, createCartDto.getProducts().get(0).getProductId());
    }

    @Test
    @DisplayName("Should initialize with null values")
    void testInitializeWithNullValues() {
        assertNull(createCartDto.getUserId());
        assertNull(createCartDto.getDate());
        assertNull(createCartDto.getProducts());
    }

    @Test
    @DisplayName("Should handle multiple products with different quantities")
    void testMultipleProductsWithDifferentQuantities() {
        for (int i = 1; i <= 5; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setProductId((long) i);
            itemDto.setQuantity((long) (i * 10));
            products.add(itemDto);
        }
        createCartDto.setProducts(products);

        assertEquals(5, createCartDto.getProducts().size());
        assertEquals(1L, createCartDto.getProducts().get(0).getProductId());
        assertEquals(50L, createCartDto.getProducts().get(4).getQuantity());
    }

    @Test
    @DisplayName("Should handle null products list after setting")
    void testNullProductsAfterSetting() {
        createCartDto.setProducts(null);

        assertNull(createCartDto.getProducts());
    }

    @Test
    @DisplayName("Should handle different date formats")
    void testDifferentDateFormats() {
        String date1 = "2024-01-17";
        String date2 = "17/01/2024";
        String date3 = "January 17, 2024";

        createCartDto.setDate(date1);
        assertEquals(date1, createCartDto.getDate());

        createCartDto.setDate(date2);
        assertEquals(date2, createCartDto.getDate());

        createCartDto.setDate(date3);
        assertEquals(date3, createCartDto.getDate());
    }
}
