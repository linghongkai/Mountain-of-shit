package com.medicalretrieval.controller;

import com.medicalretrieval.pojo.user.User;
import com.medicalretrieval.service.UserService;
import com.medicalretrieval.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    void addUser(@RequestBody User user){
        userService.save(user);
        System.out.println("添加成功："+user);
    }

    @DeleteMapping("/")
    void deleteUser(@RequestBody User user){
        userService.deleteById(user.getId());
        System.out.println("删除成功："+user);
    }

    @PutMapping("/")
    void updateUser(@RequestBody User user){
        userService.updatePasswordAndEmailAndTelephoneAndPermissionGroupIdAndAvatarAndDisabledById(user.getPassword(),user.getEmail(),user.getTelephone(), user.getPermissionGroupId(), user.getAvatar(), user.getDisabled(), user.getId());
        System.out.println("修改成功："+user);
    }

    @GetMapping("/")
    Object queryUser(String account,String password){
        User user = userService.findByAccountAndPassword(account,password);
        if (user==null){
            return null;
        }
        System.out.println("查询成功："+user);
        return Result.success(user);
    }




}
