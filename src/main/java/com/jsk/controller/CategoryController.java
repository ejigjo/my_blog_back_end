package com.jsk.controller;

import com.jsk.pojo.Category;
import com.jsk.pojo.Result;
import com.jsk.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {

        categoryService.addCategory(category);

        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> categoryList() {
        List<Category> categoryList = categoryService.categoryList();

        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> findCategoryById(Integer id) {
        Category category = categoryService.findCategoryById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {

        categoryService.updateCategory(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }
}
