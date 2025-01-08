package com.advancedjavawork.controller;

import com.advancedjavawork.entity.User;
import com.advancedjavawork.service.IUserService;
import com.advancedjavawork.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        int result = userService.login(user);
        if (result == 0) return Result.fail();
        return Result.success();
    }

    @PostMapping("/register")
    public Result register(User user){
        if (user.getUserName() == null || user.getUserPassword() == null) return Result.fail();
        int result = userService.addUser(user);
        if (result == 0) return Result.fail();
        return Result.success();
    }
}
