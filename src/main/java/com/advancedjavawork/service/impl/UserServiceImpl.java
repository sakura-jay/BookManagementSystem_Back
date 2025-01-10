package com.advancedjavawork.service.impl;

import com.advancedjavawork.entity.User;
import com.advancedjavawork.mapper.UserMapper;
import com.advancedjavawork.service.IUserService;
import com.advancedjavawork.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        wrapper.eq("user_password",user.getUserPassword());
        return mapper.selectOne(wrapper);
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

    @Override
    public Page<User> selectUserPage(UserVo vo) {
        Page<User> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (vo.getUserName()!=null&&!"".equals(vo.getUserName())){
            wrapper.like("user_name",vo.getUserName());
        }
        if (vo.getPhone()!=null&&!"".equals(vo.getPhone())){
            wrapper.eq("phone",vo.getPhone());
        }
        if (vo.getStartTIme()!=null&&!"".equals(vo.getStartTIme())){
            wrapper.between("create_time",vo.getStartTIme(),vo.getEndTIme());
        }
        return mapper.selectPage(page,wrapper);
    }
}
