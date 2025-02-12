package com.advancedjavawork.controller;

import com.advancedjavawork.entity.User;
import com.advancedjavawork.service.IUserService;
import com.advancedjavawork.utils.JWTUtils;
import com.advancedjavawork.utils.Result;
import com.advancedjavawork.vo.UserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qihui
 * @since 2025-01-08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/login")
    public Result login(User user){
        if (user.getUserName() == null || user.getUserPassword() == null) return Result.fail();
        User result = userService.login(user);
        if (result == null) return Result.fail();
        Map<String,String> map =new HashMap<>();
        map.put("userName",user.getUserName());
        map.put("userPassword",user.getUserPassword());
        String token = JWTUtils.getToken(map);
        setUserOnlineStatus(user.getUserName(),1);
        result.setToken(token);
        result.setUserPassword(null);
        return Result.success(result);
    }

    @PostMapping("/register")
    public Result register(User user){
        if (user.getUserName() == null || user.getUserPassword() == null) return Result.fail();
        int result = userService.addUser(user);
        if (result == 0) return Result.fail();
        return Result.success();
    }

    @PostMapping("/banned")
    public Result setUserStatus(Integer userId){
        if (userId == null) return Result.fail();
        boolean result = userService.setUserStatus(userId);
        if (!result) return Result.fail();
        return Result.success();
    }

    @GetMapping("/userList")
    public Result userList(UserVo vo){
        if (vo.getPageNum() == null || vo.getPageSize() == null) return Result.fail();
        Page<User> user = userService.selectUserPage(vo);
        if (user == null) return Result.fail();
        return Result.success(user);
    }

    public void setUserOnlineStatus(String userName,int status){
        if (userName!=null){
            userService.setOnlineStatus(userName,status);
        }
    }


}
