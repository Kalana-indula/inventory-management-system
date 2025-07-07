package org.inventory.app.service;

import org.inventory.app.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();
}
