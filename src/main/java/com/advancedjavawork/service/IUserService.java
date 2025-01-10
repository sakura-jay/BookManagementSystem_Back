package com.advancedjavawork.service;

import com.advancedjavawork.entity.User;
import com.advancedjavawork.vo.UserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    User login(User use);

    void setOnlineStatus(String userName,int status);

    boolean setUserStatus(int userId);

    Page<User> selectUserPage(UserVo vo);
}
