package com.arthur.cartapi.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GetCartDto Test Suite")
class GetCartDtoTest {

    private GetCartDto getCartDto;
    private List<ItemDto> products;

    @BeforeEach
    void setUp() {
        getCartDto = new GetCartDto();
        products = new ArrayList<>();
    }

    @Test
    @DisplayName("Should set and get ID")
    void testSetAndGetId() {
        Long expectedId = 5L;
        getCartDto.setId(expectedId);

        assertEquals(expectedId, getCartDto.getId());
    }

    @Test
    @DisplayName("Should set and get user ID")
    void testSetAndGetUserId() {
        Long expectedUserId = 50L;
        getCartDto.setUserId(expectedUserId);

        assertEquals(expectedUserId, getCartDto.getUserId());
    }

    @Test
    @DisplayName("Should set and get date")
    void testSetAndGetDate() {
        String expectedDate = "2024-01-17";
        getCartDto.setDate(expectedDate);

        assertEquals(expectedDate, getCartDto.getDate());
    }

    @Test
    @DisplayName("Should set and get products")
    void testSetAndGetProducts() {
        ItemDto itemDto1 = new ItemDto();
        itemDto1.setProductId(1L);
        itemDto1.setQuantity(2L);

        ItemDto itemDto2 = new ItemDto();
        itemDto2.setProductId(3L);
        itemDto2.setQuantity(4L);

        products.add(itemDto1);
        products.add(itemDto2);
        getCartDto.setProducts(products);

        assertNotNull(getCartDto.getProducts());
        assertEquals(2, getCartDto.getProducts().size());
        assertEquals(1L, getCartDto.getProducts().get(0).getProductId());
        assertEquals(3L, getCartDto.getProducts().get(1).getProductId());
    }

    @Test
    @DisplayName("Should set and get version field")
    void testSetAndGetVersion() {
        int expectedVersion = 2;
        getCartDto.set__v(expectedVersion);

        assertEquals(expectedVersion, getCartDto.get__v());
    }

    @Test
    @DisplayName("Should set all properties successfully")
    void testSetAllProperties() {
        Long expectedId = 10L;
        Long expectedUserId = 100L;
        String expectedDate = "2024-02-20";
        int expectedVersion = 1;

        ItemDto itemDto = new ItemDto();
        itemDto.setProductId(5L);
        itemDto.setQuantity(10L);
        products.add(itemDto);

        getCartDto.setId(expectedId);
        getCartDto.setUserId(expectedUserId);
        getCartDto.setDate(expectedDate);
        getCartDto.setProducts(products);
        getCartDto.set__v(expectedVersion);

        assertEquals(expectedId, getCartDto.getId());
        assertEquals(expectedUserId, getCartDto.getUserId());
        assertEquals(expectedDate, getCartDto.getDate());
        assertEquals(1, getCartDto.getProducts().size());
        assertEquals(expectedVersion, getCartDto.get__v());
    }

    @Test
    @DisplayName("Should initialize with null values")
    void testInitializeWithNullValues() {
        assertNull(getCartDto.getId());
        assertNull(getCartDto.getUserId());
        assertNull(getCartDto.getDate());
        assertNull(getCartDto.getProducts());
    }

    @Test
    @DisplayName("Should handle empty products list")
    void testEmptyProductsList() {
        getCartDto.setProducts(new ArrayList<>());

        assertNotNull(getCartDto.getProducts());
        assertEquals(0, getCartDto.getProducts().size());
    }

    @Test
    @DisplayName("Should handle multiple products")
    void testMultipleProducts() {
        for (int i = 1; i <= 5; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setProductId((long) i);
            itemDto.setQuantity((long) (i * 5));
            products.add(itemDto);
        }
        getCartDto.setProducts(products);

        assertEquals(5, getCartDto.getProducts().size());
        assertEquals(1L, getCartDto.getProducts().get(0).getProductId());
        assertEquals(5L, getCartDto.getProducts().get(4).getProductId());
        assertEquals(25L, getCartDto.getProducts().get(4).getQuantity());
    }

    @Test
    @DisplayName("Should handle version field updates")
    void testVersionFieldUpdates() {
        getCartDto.set__v(0);
        assertEquals(0, getCartDto.get__v());

        getCartDto.set__v(5);
        assertEquals(5, getCartDto.get__v());
    }
}
