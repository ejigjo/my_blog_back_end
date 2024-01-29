package com.jsk.controller;

import com.jsk.pojo.Result;
import com.jsk.pojo.User;
import com.jsk.service.UserService;
import com.jsk.utils.JwtUtil;
import com.jsk.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByUserName(username);
        //驗證用戶是否存在
        if (user == null) {
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("該用戶已存在");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findUserByUserName(username);
        //驗證用戶是否存在
        if (user == null) {
            return Result.error("用戶不存在");
        }
        //將密碼用MD5加密
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            //生成JWT令牌
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String jwt = JwtUtil.genToken(claims);
            return Result.success(jwt);
        }
        return Result.error("密碼不正確");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        //利用局部線程獲取token
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User userInfo = userService.findUserByUserName(username);

        return Result.success(userInfo);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {

        userService.update(user);

        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@URL String avatarUrl) {

        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> updatePwd) {
        String oldPwd = updatePwd.get("old_pwd");
        String newPwd = updatePwd.get("new_pwd");
        String rePwd = updatePwd.get("re_pwd");

        Map<String, String> map = ThreadLocalUtil.get();
        String username = map.get("username");
        User userInfo = userService.findUserByUserName(username);
        if (oldPwd == null || newPwd == null || rePwd == null) {
            return Result.error("密碼不能為空白");
        }
        if(!DigestUtils.md5DigestAsHex(oldPwd.getBytes()).equals(userInfo.getPassword())){
            return Result.error("原密碼不正確");
        }
        if(DigestUtils.md5DigestAsHex(newPwd.getBytes()).equals(userInfo.getPassword())){
            return Result.error("新密碼不能與舊密碼相同");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("兩次密碼填寫不一致");
        }
        userService.updatePwd(newPwd);
            return Result.success();
    }
}
