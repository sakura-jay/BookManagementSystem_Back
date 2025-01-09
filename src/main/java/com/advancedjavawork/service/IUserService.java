package com.advancedjavawork.service;

import com.advancedjavawork.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qihui
 * @since 2025-01-08
 */
public interface IUserService extends IService<User> {

    Integer addUser(User user);

    Integer login(User use);

    void setOnlineStatus(String userName,int status);
}
