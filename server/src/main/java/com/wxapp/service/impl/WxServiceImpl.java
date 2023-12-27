package com.wxapp.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wxapp.domain.EvaluateInfo;
import com.wxapp.domain.UserInfo;
import com.wxapp.mapper.EvaluateInfoMapper;
import com.wxapp.mapper.UserInfoMapper;
import com.wxapp.pojo.EvaluateListRsp;
import com.wxapp.pojo.EvaluateReq;
import com.wxapp.pojo.FeedBackReq;
import com.wxapp.pojo.UserInfoReq;
import com.wxapp.service.WxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: wxapp
 * @description:
 * @author: WONGhaiLONG
 * @create: 2023-12-13 19:24
 **/
@Service
public class WxServiceImpl implements WxAppService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private EvaluateInfoMapper evaluateInfoMapper;


    @Override
    public Boolean saveUserInfo(UserInfo userInfo) {
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setPhoneNumber(userInfo.getPhoneNumber());
        UserInfo userInfoTemp = userInfoMapper.selectByUserNameAndPhone(userInfoReq);
        int count = 0;
        if(ObjectUtil.isNotEmpty(userInfoTemp)){
            userInfo.setId(userInfoTemp.getId());
            count = userInfoMapper.updateByPrimaryKey(userInfo);

        }else {
            count = userInfoMapper.insert(userInfo);
        }
        return count>0;
    }

    @Override
    public UserInfo getUserInfo(UserInfoReq userInfoReq) {

        return userInfoMapper.selectByUserNameAndPhone(userInfoReq);
    }

    @Override
    public IPage<EvaluateInfo> getEvaluateList(EvaluateReq evaluateReq){
        Page<EvaluateInfo> page=new Page<>(evaluateReq.getPageNum(),evaluateReq.getPageSize());
        evaluateInfoMapper.selectListPage(page,evaluateReq);

        return page;
    }

    @Override
    public EvaluateInfo getEvaluateDetail(Integer id) {
        return evaluateInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean feedback(FeedBackReq req) {
        Date date = new Date();
        Integer id = req.getId();
        EvaluateInfo evaluateInfo = evaluateInfoMapper.selectByPrimaryKey(id);
        evaluateInfo.setState(2);
        evaluateInfo.setFeedbackTime(date);
        evaluateInfo.setServiceScore(req.getServiceScore());
        evaluateInfo.setResponseScore(req.getResponseScore());
        evaluateInfo.setIsTimeComplete(req.getIsTimeComplete());
        evaluateInfo.setFeedback(req.getFeedback());
        evaluateInfo.setCommitUserSign(req.getCommitUserSign());
        evaluateInfo.setContactPhone(req.getContactPhone());
        evaluateInfo.setServiceDone(req.getServiceDone());

        return evaluateInfoMapper.updateByPrimaryKeySelective(evaluateInfo)>0;
    }

    @Override
    public Boolean addEvaluate(EvaluateInfo evaluateInfo) {
        Date date = new Date();
        // 设置第一次提交的单子的状态
        evaluateInfo.setState(1);
        evaluateInfo.setCommitTime(date);

        return evaluateInfoMapper.insert(evaluateInfo)>0;
    }
}
