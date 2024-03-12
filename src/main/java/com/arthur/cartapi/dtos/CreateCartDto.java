package com.arthur.cartapi.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateCartDto {
    private Long userId;
    private String date;
    private List<ItemDto> products;
}
