package com.jsk.pojo;


import com.jsk.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    @NotNull(groups = Article.Update.class)
    private Integer id;//主鍵ID

    @NotEmpty
    @Pattern(regexp = "^\\S{1,20}$")
    private String title;//文章標題

    @NotEmpty
    private String content;//文章內容

    @NotEmpty
    @URL
    private String coverImg;//封面圖片
    @State
    private String state;//發布狀態 已發布|草稿
    @NotNull
    private Integer categoryId;//文章分類id
    private Integer createUser;//建立人ID
    private LocalDateTime createTime;//建立時間
    private LocalDateTime updateTime;//更新時間

    public interface Add extends Default{

    }

    public interface Update extends Default{

    }
}