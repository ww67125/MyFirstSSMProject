package com.etc.mapper;

import com.etc.entity.User;
import com.etc.entity.UserVO;

import java.util.List;

public interface UserMapper {
    User findUser(User user);
    boolean updateUser(User user);
    boolean insertUser(User user);
    List<User> allUser();
    List<User> findUserList(UserVO userVO);
    boolean deleteUser(int userid);
    //查找用户名是否存在
    int findUsername(String username);
}
