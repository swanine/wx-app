package com.wxapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxapp.domain.EvaluateInfo;
import com.wxapp.pojo.EvaluateListRsp;
import com.wxapp.pojo.EvaluateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EvaluateInfoMapper extends BaseMapper<EvaluateInfo> {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluateInfo record);

    int insertSelective(EvaluateInfo record);

    EvaluateInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluateInfo record);

    int updateByPrimaryKey(EvaluateInfo record);

    IPage<EvaluateInfo> selectListPage(Page<EvaluateInfo> page, @Param("evaluateReq") EvaluateReq evaluateReq);

    int selectCountByParam(@Param("evaluateReq")EvaluateReq evaluateReq);
}