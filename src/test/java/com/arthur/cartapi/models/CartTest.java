package com.arthur.cartapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cart Model Test Suite")
class CartTest {

    private Cart cart;
    private List<Item> items;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        items = new ArrayList<>();
    }

    @Test
    @DisplayName("Should set and get cart ID")
    void testSetAndGetId() {
        Long expectedId = 1L;
        cart.setId(expectedId);

        assertEquals(expectedId, cart.getId());
    }

    @Test
    @DisplayName("Should set and get user ID")
    void testSetAndGetUserId() {
        Long expectedUserId = 100L;
        cart.setUserId(expectedUserId);

        assertEquals(expectedUserId, cart.getUserId());
    }

    @Test
    @DisplayName("Should set and get date")
    void testSetAndGetDate() {
        String expectedDate = "2024-01-17";
        cart.setDate(expectedDate);

        assertEquals(expectedDate, cart.getDate());
    }

    @Test
    @DisplayName("Should set and get item list")
    void testSetAndGetItemList() {
        Item item1 = new Item();
        item1.setProductId(1L);
        item1.setQuantity(2L);

        Item item2 = new Item();
        item2.setProductId(2L);
        item2.setQuantity(3L);

        items.add(item1);
        items.add(item2);
        cart.setItemList(items);

        assertNotNull(cart.getItemList());
        assertEquals(2, cart.getItemList().size());
        assertEquals(1L, cart.getItemList().get(0).getProductId());
        assertEquals(2L, cart.getItemList().get(1).getProductId());
    }

    @Test
    @DisplayName("Should handle empty item list")
    void testEmptyItemList() {
        cart.setItemList(new ArrayList<>());

        assertNotNull(cart.getItemList());
        assertEquals(0, cart.getItemList().size());
    }

    @Test
    @DisplayName("Should set multiple properties successfully")
    void testSetMultipleProperties() {
        Long expectedId = 5L;
        Long expectedUserId = 50L;
        String expectedDate = "2024-02-20";

        Item item = new Item();
        item.setProductId(10L);
        item.setQuantity(5L);
        items.add(item);

        cart.setId(expectedId);
        cart.setUserId(expectedUserId);
        cart.setDate(expectedDate);
        cart.setItemList(items);

        assertEquals(expectedId, cart.getId());
        assertEquals(expectedUserId, cart.getUserId());
        assertEquals(expectedDate, cart.getDate());
        assertEquals(1, cart.getItemList().size());
    }

    @Test
    @DisplayName("Should initialize with null values")
    void testInitializeWithNullValues() {
        assertNull(cart.getId());
        assertNull(cart.getUserId());
        assertNull(cart.getDate());
        assertNull(cart.getItemList());
    }

    @Test
    @DisplayName("Should handle large ID values")
    void testLargeIdValues() {
        Long largeId = 9999999999L;
        Long largeUserId = 9999999998L;

        cart.setId(largeId);
        cart.setUserId(largeUserId);

        assertEquals(largeId, cart.getId());
        assertEquals(largeUserId, cart.getUserId());
    }
}
