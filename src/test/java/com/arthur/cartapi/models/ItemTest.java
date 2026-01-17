package com.arthur.cartapi.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Item Model Test Suite")
class ItemTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
    }

    @Test
    @DisplayName("Should set and get product ID")
    void testSetAndGetProductId() {
        Long expectedProductId = 42L;
        item.setProductId(expectedProductId);

        assertEquals(expectedProductId, item.getProductId());
    }

    @Test
    @DisplayName("Should set and get quantity")
    void testSetAndGetQuantity() {
        Long expectedQuantity = 10L;
        item.setQuantity(expectedQuantity);

        assertEquals(expectedQuantity, item.getQuantity());
    }

    @Test
    @DisplayName("Should set both product ID and quantity")
    void testSetBothProperties() {
        Long expectedProductId = 15L;
        Long expectedQuantity = 25L;

        item.setProductId(expectedProductId);
        item.setQuantity(expectedQuantity);

        assertEquals(expectedProductId, item.getProductId());
        assertEquals(expectedQuantity, item.getQuantity());
    }

    @Test
    @DisplayName("Should initialize with null values")
    void testInitializeWithNullValues() {
        assertNull(item.getProductId());
        assertNull(item.getQuantity());
    }

    @Test
    @DisplayName("Should handle zero quantity")
    void testZeroQuantity() {
        item.setProductId(1L);
        item.setQuantity(0L);

        assertEquals(1L, item.getProductId());
        assertEquals(0L, item.getQuantity());
    }

    @Test
    @DisplayName("Should handle large product IDs")
    void testLargeProductId() {
        Long largeProductId = 999999999L;
        item.setProductId(largeProductId);

        assertEquals(largeProductId, item.getProductId());
    }

    @Test
    @DisplayName("Should handle large quantities")
    void testLargeQuantity() {
        Long largeQuantity = 999999999L;
        item.setQuantity(largeQuantity);

        assertEquals(largeQuantity, item.getQuantity());
    }

    @Test
    @DisplayName("Should update quantity multiple times")
    void testUpdateQuantityMultipleTimes() {
        item.setProductId(5L);

        item.setQuantity(10L);
        assertEquals(10L, item.getQuantity());

        item.setQuantity(20L);
        assertEquals(20L, item.getQuantity());

        item.setQuantity(5L);
        assertEquals(5L, item.getQuantity());
    }

    @Test
    @DisplayName("Should handle single item quantity")
    void testSingleItemQuantity() {
        item.setProductId(1L);
        item.setQuantity(1L);

        assertEquals(1L, item.getProductId());
        assertEquals(1L, item.getQuantity());
    }
}
