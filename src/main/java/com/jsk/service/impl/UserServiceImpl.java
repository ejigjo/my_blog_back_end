package com.jsk.service.impl;

import com.jsk.mapper.UserMapper;
import com.jsk.pojo.User;
import com.jsk.service.UserService;
import com.jsk.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User findUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //md5加密
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        userMapper.register(username,md5Password);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id =(Integer) map.get("id");
        userMapper.updateAvatar(avatar,id);
    }

    @Override
    public void updatePwd(String newPwd) {
    Map<String,Integer> map = ThreadLocalUtil.get();
        Integer id = map.get("id");
        String md5Pwd = DigestUtils.md5DigestAsHex(newPwd.getBytes());
        userMapper.updatePwd(md5Pwd,id);
    }
}
