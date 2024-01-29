package com.jsk.service;

import com.jsk.pojo.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    List<Category> categoryList();

    Category findCategoryById(Integer id);

    void updateCategory(Category category);

    void deleteCategory(Integer id);
}
