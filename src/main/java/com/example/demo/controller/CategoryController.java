package com.example.demo.controller;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.response.CategoryListResponse;
import com.example.demo.response.CategoryReponse;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService service;

    @GetMapping("")
    public List<Category> index(){
        return service.getAllCategories();
    }

    @PostMapping("/save")
    public Category saveCategory(@RequestBody CategoryDTO categoryDTO){
        return service.saveNewCategory(categoryDTO);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable("id") Long id,@RequestBody CategoryDTO categoryDTO){
        return service.updateCategory(id,categoryDTO);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id")Long id){
        service.deleteCategory(id);
        return "Đã xoá category với id: " + id;
    }

    @PostMapping("/insert1")
    public String saveCategory1(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> listErro = bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .toList();
            return listErro.toString();
        }
        service.saveNewCategory(categoryDTO);
        return "insert" + categoryDTO;
    }
    @GetMapping("/list")
    public ResponseEntity<CategoryListResponse> getAllCategories(@RequestParam("page") int page,
                                                                 @RequestParam("limit") int limit){
        PageRequest pageRequest = PageRequest.of(
                page,limit,
                Sort.by("createAt").descending()
        );
        Page<CategoryReponse> categoryReponsePage = service.getAllCategories(pageRequest);

        int totalPages =categoryReponsePage.getTotalPages();

        List<CategoryReponse> reponsesCategories = categoryReponsePage.getContent();
        return ResponseEntity.ok(CategoryListResponse.builder()
                .categories(reponsesCategories)
                .totalPages(totalPages)
                .build());
    }
}
