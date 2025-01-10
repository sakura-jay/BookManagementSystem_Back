package com.advancedjavawork.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qihui
 * @since 2025-01-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    @ApiModelProperty("账号状态 1正常 0封禁")
    private Integer status;

    @ApiModelProperty("在线状态 1在线 0离线")
    private Integer onlineStatus;

    @ApiModelProperty("注册时间 ")
    private LocalDate createTime;

    @ApiModelProperty("手机号 ")
    private String phone;

    @TableField(exist = false)
    private String token;
}
