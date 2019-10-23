package com.springboot.hwzl.services.userservice.userservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.hwzl.dao.UserMapper;
import com.springboot.hwzl.entity.User;
import com.springboot.hwzl.global.BusinessException;
import com.springboot.hwzl.global.globalentity.RetCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveClusterGeoCommands;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    UserMapper userMapper;


    @Override
    public User findUserById(int Id) {
        return  userMapper.findUserById(Id);
    }

    /*@Override
    @Transactional(rollbackFor=Exception.class,propagation= Propagation.REQUIRED)
    public int add(User user) {
        userMapper.add(user);
        User user1=null;
        return user.getUserId();
    }



    @Override
    public User userLogin(String account, String password) {
        return userMapper.userLogin(account,password);
    }

    @Override
    public List<User> findUserList(int pageSize,int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<User> users = userMapper.findUserList();
        //PageInfo<User> page = new PageInfo<User>(users);
        return  users;
    }

    @Override
    public boolean updateName(String userName, int userId) {
        return userMapper.updateName(userName,userId);
    }*/

}
