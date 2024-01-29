package com.jsk.controller;

import com.jsk.pojo.Article;
import com.jsk.pojo.PageBean;
import com.jsk.pojo.Result;
import com.jsk.service.ArticleService;
import com.jsk.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result addArticle(@RequestBody @Validated(Article.Add.class) Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> articleList(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state) {

        PageBean<Article> pageBean = articleService.articleList(pageNum, pageSize, categoryId, state);

        return Result.success(pageBean);
    }

    @GetMapping("/detail")
    public Result<Article> findArticleById(Integer id){
       Article article1Info = articleService.findArticleById(id);

        return Result.success(article1Info);
    }

    @PutMapping
    public Result updateArticle(@RequestBody @Validated(Article.Update.class) Article article){
        articleService.updateArticle(article);

        return Result.success();
    }

    @DeleteMapping
    public Result deleteArticle(Integer id){
        articleService.deleteArticle(id);
        return Result.success();
    }
}
