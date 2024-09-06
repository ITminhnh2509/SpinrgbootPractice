package com.example.demo.response;

import com.example.demo.model.Category;
import lombok.*;


@Data
@Builder
public class CategoryReponse extends BaseResponse {
    private Long id;
    private String name;

    public static CategoryReponse fromCategory(Category category){
        CategoryReponse categoryReponse = CategoryReponse.builder()
                .name(category.getName())
                .id(category.getId())
                .build();
        categoryReponse.setCreatedAt(category.getCreateAt());
        categoryReponse.setUpdatedAt(category.getUpdateAt());
        return categoryReponse;
    }
}
