package com.wxapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxapp.domain.UserInfo;
import com.wxapp.pojo.UserInfoReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByUserNameAndPhone(@Param("userInfoReq")UserInfoReq userInfoReq);
}