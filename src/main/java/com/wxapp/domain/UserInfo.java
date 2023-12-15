package com.wxapp.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * user_info
 * @author 
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户类型
     */
    private Integer roleType;

    /**
     * 真实姓名
     */
    private String realName;

    private static final long serialVersionUID = 1L;
}