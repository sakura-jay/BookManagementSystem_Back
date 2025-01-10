package com.advancedjavawork.controller;

import com.advancedjavawork.entity.Admin;
import com.advancedjavawork.service.IAdminService;
import com.advancedjavawork.utils.JWTUtils;
import com.advancedjavawork.utils.Result;
import jakarta.annotation.Resource;
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
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private IAdminService adminService;

    @PostMapping("/login")
    public Result login(Admin admin){
        if (admin.getAdminName() == null || admin.getAdminPassword() == null) return Result.fail();
        Admin result = adminService.login(admin);
        if (result == null) Result.fail();
        Map<String,String> map =new HashMap<>();
        map.put("adminName",admin.getAdminName());
        map.put("adminPassword",admin.getAdminPassword());
        String token = JWTUtils.getToken(map);
        result.setToken(token);
        result.setAdminPassword(null);
        return Result.success(result);
    }
}
