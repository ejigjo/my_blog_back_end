package com.jsk.service;

import com.jsk.pojo.User;

public interface UserService {
    User findUserByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatar);

    void updatePwd(String newPwd);
}
