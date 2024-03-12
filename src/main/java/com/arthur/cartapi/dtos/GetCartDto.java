package com.arthur.cartapi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCartDto {
    private Long id;
    private Long userId;
    private String date;
    private List<ItemDto> products;
    private int __v;
}
