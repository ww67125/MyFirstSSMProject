package com.etc.service;

import com.etc.entity.User;
import com.etc.entity.UserVO;
import com.etc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Transactional
    public User login(User user){

        //获取登录成功后的加分
        int loginScore = user.getScore();

        user = userMapper.findUser(user);

        if(user!=null){   //查找用户成功

            //更新积分
            user.setScore(user.getScore() + loginScore);

            if(userMapper.updateUser(user)){   //更新数据库的积分成功
                return user;
            }
        }

        return null;
    }
    @Transactional
    public boolean register(User user){
        int initScore=user.getScore();
        user.setScore(initScore);
        user.setRegdatetime(new Date());
        try {
            return userMapper.insertUser(user);
        }catch (Exception e){
            return false;
        }


    }
    @Transactional
    public List<User> allUser(){
        return userMapper.allUser();
    }
    @Transactional(readOnly = true)
    public List<User> getUserList(UserVO userVO){
        //return userMapper.findUserList();
        return userMapper.findUserList(userVO);
    }
    @Transactional
    public boolean deleteUser(int userid) {
        return userMapper.deleteUser(userid);
    }

    //检查用户名是否存在
    @Transactional(readOnly=true)
    public boolean checkUsername(String username) {
        return userMapper.findUsername(username) > 0;
    }
}
