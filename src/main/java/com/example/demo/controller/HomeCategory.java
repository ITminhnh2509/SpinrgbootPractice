package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeCategory {
    @GetMapping("/")
    public String home(){
        return "Home page";
    }

    @GetMapping("/product")
    public String product(){
        return "Product page";
    }

    @PostMapping("")
    public String productPost(){
        return "Product Post";
    }

    @DeleteMapping
    public String productDelete(){
        return "Product delete";
    }

    @PutMapping
    public String productPut(){
        return "Product put";
    }

}
