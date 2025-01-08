package com.advancedjavawork.controller;

import com.advancedjavawork.entity.Admin;
import com.advancedjavawork.service.IAdminService;
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
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private IAdminService adminService;

    @PostMapping("/login")
    public Result login(Admin admin){
        if (admin.getAdminName() == null || admin.getAdminPassword() == null) return Result.fail();
        int result = adminService.login(admin);
        if (result == 0) Result.fail();
        return Result.success();
    }
}
