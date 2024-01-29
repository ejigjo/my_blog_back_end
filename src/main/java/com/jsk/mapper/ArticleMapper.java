package com.jsk.mapper;

import com.jsk.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "VALUES(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime}) ")
    void addArticle(Article article);

    List<Article> articleList(Integer userId, Integer categoryId, String state);
    @Update("update article set title = #{title},content = #{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time = now()" +
            " where id = #{id}")
    void updateArticle(Article article);
    @Delete("delete from article where id = #{id}")
    void deleteArticle(Integer id);
    @Select("select * from article where id = #{id}")
    Article findArticleById(Integer id);
}
