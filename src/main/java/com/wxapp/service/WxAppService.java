package com.wxapp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wxapp.domain.EvaluateInfo;
import com.wxapp.domain.UserInfo;
import com.wxapp.pojo.EvaluateListRsp;
import com.wxapp.pojo.EvaluateReq;
import com.wxapp.pojo.FeedBackReq;
import com.wxapp.pojo.UserInfoReq;

public interface WxAppService {
    Boolean saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo(UserInfoReq userInfoReq);

    Boolean addEvaluate(EvaluateInfo evaluateInfo);

    IPage<EvaluateInfo> getEvaluateList(EvaluateReq evaluateReq);

    EvaluateInfo getEvaluateDetail(Integer id);

    Boolean feedback(FeedBackReq req);
}
