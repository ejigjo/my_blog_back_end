package com.jsk.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主鍵ID

    @NotEmpty
    private String categoryName;//分類名稱

    @NotEmpty
    private String categoryAlias;//分類別名

    private Integer createUser;//建立人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//建立時間

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新時間

    public interface Add extends Default {

    }

    public interface Update extends Default{

    }



}