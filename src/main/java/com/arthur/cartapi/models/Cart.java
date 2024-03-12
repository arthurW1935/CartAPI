package com.arthur.cartapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private String date;
    List<Item> itemList;
}
