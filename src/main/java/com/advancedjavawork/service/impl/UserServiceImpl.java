package com.advancedjavawork.service.impl;

import com.advancedjavawork.entity.User;
import com.advancedjavawork.mapper.UserMapper;
import com.advancedjavawork.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper mapper;

    @Override
    public Integer addUser(User user) {
        return mapper.insert(user);
    }

    @Override
    public Integer login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        wrapper.eq("user_password",user.getUserPassword());
        User result = mapper.selectOne(wrapper);
        if (result == null) return 0;
        else return 1;
    }

    @Override
    public void setOnlineStatus(String userName,int status) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_name",userName);
        wrapper.set("online_status",status);
        mapper.update(wrapper);
    }

    @Override
    public boolean setUserStatus(int userId) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.set("status",0);
        if(mapper.update(wrapper) == 0) return false;
        return true;
    }
}
