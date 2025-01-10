package com.advancedjavawork.service.impl;

import com.advancedjavawork.entity.Admin;
import com.advancedjavawork.mapper.AdminMapper;
import com.advancedjavawork.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qihui
 * @since 2025-01-08
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Resource
    private AdminMapper mapper;
    @Override
    public Admin login(Admin admin) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name",admin.getAdminName());
        wrapper.eq("admin_password",admin.getAdminPassword());
        return mapper.selectOne(wrapper);
    }
}
