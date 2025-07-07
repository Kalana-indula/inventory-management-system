package org.inventory.app.controller;

import org.inventory.app.entity.Category;
import org.inventory.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        try{
            Category createdCategory = categoryService.createCategory(category);

            return ResponseEntity.status(HttpStatus.OK).body(createdCategory);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> findAllCategories(){
        try{
            List<Category> categories=categoryService.getAllCategories();

            if(categories.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(categories);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
