package com.jsk.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jsk.mapper.ArticleMapper;
import com.jsk.pojo.Article;
import com.jsk.pojo.PageBean;
import com.jsk.service.ArticleService;
import com.jsk.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
@Autowired
    ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String,Integer> map = ThreadLocalUtil.get();
        Integer userId = map.get("id");
        article.setCreateUser(userId);
        articleMapper.addArticle(article);
    }

    @Override
    public PageBean<Article> articleList(Integer pageNum, Integer pageSize, Integer categoryld, String state) {
        //創建PageBean對象
        PageBean<Article> pageBean = new PageBean<>();
        //使用Pagehelper分頁查詢
        PageHelper.startPage(pageNum,pageSize);
        //調用Mapper獲取LIST
        Map<String,Integer> map = ThreadLocalUtil.get();
        Integer userId = map.get("id");
        List<Article> list = articleMapper.articleList(userId,categoryld,state);
        Page<Article> articleList = (Page<Article>) list;
        pageBean.setTotal(articleList.getTotal());
        pageBean.setItems(articleList.getResult());

        return pageBean;
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public Article findArticleById(Integer id) {
       Article articleInfo = articleMapper.findArticleById(id);
        return articleInfo;
    }
}
