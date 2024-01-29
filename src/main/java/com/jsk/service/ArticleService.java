package com.jsk.service;

import com.jsk.pojo.Article;
import com.jsk.pojo.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    PageBean<Article> articleList(Integer pageNum, Integer pageSize, Integer categoryld, String state);

    void updateArticle(Article article);

    void deleteArticle(Integer id);

    Article findArticleById(Integer id);
}
