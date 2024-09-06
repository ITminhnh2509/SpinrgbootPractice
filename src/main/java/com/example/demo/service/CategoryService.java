package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.response.CategoryReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository repository;

    @Override
    public Category getCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    @Override
    public Category saveNewCategory(CategoryDTO categoryDTO) {
        Category category = Category
                .builder()
                .name(categoryDTO.getNameCategory())
                .build();
        return repository.save(category);
    }

    @Override
    public Category updateCategory(Long id,CategoryDTO categoryDTO) {
        Category category1 = getCategoryById(id);
        category1.setName(categoryDTO.getNameCategory());
        category1.setUpdateAt(LocalDateTime.now());
        return repository.save(category1);
    }

    @Override
    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CategoryReponse> getAllCategories(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(category -> {
                    return CategoryReponse.fromCategory(category);
                });
    }
}
