package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.response.CategoryReponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category saveNewCategory(CategoryDTO categoryDTO);
    Category updateCategory(Long id,CategoryDTO categoryDTO);
    void deleteCategory(Long id);
    Page<CategoryReponse> getAllCategories(PageRequest pageRequest);
}
