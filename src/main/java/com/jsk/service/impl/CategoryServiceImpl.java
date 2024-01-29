package com.jsk.service.impl;

import com.jsk.mapper.CategoryMapper;
import com.jsk.pojo.Category;
import com.jsk.service.CategoryService;
import com.jsk.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void addCategory(Category category) {
        //補充屬性
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        Map<String, Integer> map = ThreadLocalUtil.get();
        Integer id = map.get("id");
        category.setCreateUser(id);
        categoryMapper.addCategory(category);
    }

    @Override
    public List<Category> categoryList() {
        Map<String, Integer> map = ThreadLocalUtil.get();
        Integer userId = map.get("id");

       return categoryMapper.categoryList(userId);
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryMapper.findCategoryById(id);
    }

    @Override
    public void updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);

    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }
}
