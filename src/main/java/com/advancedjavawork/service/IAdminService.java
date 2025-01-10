package com.advancedjavawork.service;

import com.advancedjavawork.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qihui
 * @since 2025-01-08
 */
public interface IAdminService extends IService<Admin> {

    Admin login(Admin admin);
}
