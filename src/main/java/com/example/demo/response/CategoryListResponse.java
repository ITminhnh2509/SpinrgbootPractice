package com.example.demo.response;

import lombok.*;

import java.util.List;

@Data
@Builder
public class CategoryListResponse {
    private List<CategoryReponse> categories;
    private int totalPages;
}
