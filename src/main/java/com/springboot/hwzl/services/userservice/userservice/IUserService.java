package com.springboot.hwzl.services.userservice.userservice;

import com.springboot.hwzl.entity.User;

import java.util.List;

public interface IUserService {

     User findUserById(int Id);
    /* int add(User user);
     User userLogin(String account,  String password);
     List<User> findUserList(int pageSize,int pageIndex);
     boolean updateName(String userName,int userId);*/
}
