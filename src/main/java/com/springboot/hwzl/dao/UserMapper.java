package com.springboot.hwzl.dao;

import com.springboot.hwzl.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper  {

     User findUserById(int Id);
     /*int add(User user);
     //@Param("account")
     //@Param("password")
     User userLogin(String account, String password);
     List<User> findUserList();
     boolean updateName(@Param("userName")String userName,@Param("userId")int userId);*/
}
