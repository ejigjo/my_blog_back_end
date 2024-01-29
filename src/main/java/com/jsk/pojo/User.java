package com.jsk.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @NotNull
    private Integer id;//主鍵ID
    private String username;//使用者名
    @JsonIgnore//這註解的意思是當前Class轉換成Json格式時,忽略備標註的屬性,所以最後的響應Json就沒有password了
    private String password;//密碼
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//暱稱
    @NotEmpty
    @Email
    private String email;//信箱
    private String userPic;//使用者頭像位址
    private LocalDateTime createTime;//建立時間
    private LocalDateTime updateTime;//更新時間
}