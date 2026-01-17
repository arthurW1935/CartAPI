package com.arthur.cartapi.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ItemDto Test Suite")
class ItemDtoTest {

    private ItemDto itemDto;

    @BeforeEach
    void setUp() {
        itemDto = new ItemDto();
    }

    @Test
    @DisplayName("Should set and get product ID")
    void testSetAndGetProductId() {
        Long expectedProductId = 25L;
        itemDto.setProductId(expectedProductId);

        assertEquals(expectedProductId, itemDto.getProductId());
    }

    @Test
    @DisplayName("Should set and get quantity")
    void testSetAndGetQuantity() {
        Long expectedQuantity = 15L;
        itemDto.setQuantity(expectedQuantity);

        assertEquals(expectedQuantity, itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should set both product ID and quantity")
    void testSetBothProperties() {
        Long expectedProductId = 10L;
        Long expectedQuantity = 20L;

        itemDto.setProductId(expectedProductId);
        itemDto.setQuantity(expectedQuantity);

        assertEquals(expectedProductId, itemDto.getProductId());
        assertEquals(expectedQuantity, itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should initialize with null values")
    void testInitializeWithNullValues() {
        assertNull(itemDto.getProductId());
        assertNull(itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should handle zero quantity")
    void testZeroQuantity() {
        itemDto.setProductId(5L);
        itemDto.setQuantity(0L);

        assertEquals(5L, itemDto.getProductId());
        assertEquals(0L, itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should handle large numbers")
    void testLargeNumbers() {
        Long largeNumber = 9999999999L;

        itemDto.setProductId(largeNumber);
        itemDto.setQuantity(largeNumber);

        assertEquals(largeNumber, itemDto.getProductId());
        assertEquals(largeNumber, itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should update values independently")
    void testUpdateValuesIndependently() {
        itemDto.setProductId(1L);
        itemDto.setQuantity(5L);

        itemDto.setProductId(2L);
        assertEquals(2L, itemDto.getProductId());
        assertEquals(5L, itemDto.getQuantity());

        itemDto.setQuantity(10L);
        assertEquals(2L, itemDto.getProductId());
        assertEquals(10L, itemDto.getQuantity());
    }

    @Test
    @DisplayName("Should handle negative values")
    void testNegativeValues() {
        itemDto.setProductId(-1L);
        itemDto.setQuantity(-10L);

        assertEquals(-1L, itemDto.getProductId());
        assertEquals(-10L, itemDto.getQuantity());
    }
}
