package top.mxzero.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.productservice.model.Category;
import top.mxzero.productservice.service.CategoryServiceImpl;

import javax.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    @GetMapping("/categories")
    public Object listCategory(){
        return service.list();
    }

    @GetMapping("/categories/{id}")
    public Object getCategory(@PathVariable("id") int id){
        return service.getById(id);
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/categories")
    public Object addCategory(@Valid @RequestBody Category category){
        return service.save(category);
    }

    @JWTAuthentication(role = "staff")
    @PutMapping("/categories/{id}")
    public Object updateCategory(@PathVariable("id") int id, @Valid @RequestBody Category category){
        category.setId(id);
        return service.updateById(category);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/categories/{id}")
    public Object deleteCategory(@PathVariable("id") int id){
        return service.removeById(id);
    }
}
