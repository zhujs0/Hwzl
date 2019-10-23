package com.springboot.hwzl.controllers.users;

import com.springboot.hwzl.customannotation.PassToken;
import com.springboot.hwzl.entity.User;
import com.springboot.hwzl.global.globalentity.Response;
import com.springboot.hwzl.global.globalentity.RetCode;
import com.springboot.hwzl.services.userservice.userservice.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
public class UserController {
    @Autowired
    IUserService userService;

//    @ApiOperation(value="根据用户Id获取用户信息")
    //@ApiParam(name = "userId",value = "用户Id")
    @GetMapping("findUserById")
    @PassToken
    public Response findUserById(int userId){
        User user= userService.findUserById(userId);
        return  new Response(RetCode.SUCCESS,user);
    }

 /*   @ApiOperation(value="添加用户",httpMethod = "POST")
    @PostMapping("addUser")
    public Response addUser(@RequestBody User user){
        int userId=userService.add(user);
        return  new Response(RetCode.SUCCESS,userId);
    }

    @PutMapping("updateName")
    public Response updateName(@RequestBody User user){
        boolean result= userService.updateName(user.getUserName(),user.getUserId());
        return  new Response(RetCode.SUCCESS);
    }

    @ApiOperation(value="获取用户列表",httpMethod = "GET")
    @GetMapping("findUserList")
    public Response findUserList(@RequestParam(name = "pageSize",value = "页大小") int pageSize,
                                 @RequestParam(name = "pageIndex",value = "页码")int pageIndex){
        List<User> userList= userService.findUserList(pageSize,pageIndex);
        return  new Response(RetCode.SUCCESS,userList);
    }*/


}
