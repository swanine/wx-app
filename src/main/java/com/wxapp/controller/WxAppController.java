package com.wxapp.controller;

import com.wxapp.domain.EvaluateInfo;
import com.wxapp.domain.UserInfo;
import com.wxapp.pojo.EvaluateReq;
import com.wxapp.pojo.FeedBackReq;
import com.wxapp.pojo.UserInfoReq;
import com.wxapp.result.Result;
import com.wxapp.service.WxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: wxapp
 * @description: 评价信息小程序
 * @author: WONGhaiLONG
 * @create: 2023-12-13 19:18
 **/
@RestController
@RequestMapping(value ="/wx/evaluate/")
public class WxAppController {

    @Autowired
    private WxAppService wxAppService;

    @PostMapping("/saveUserInfo")
    public Result saveUserInfo(@RequestBody UserInfo userInfo){

        return Result.success(wxAppService.saveUserInfo(userInfo));
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody UserInfoReq userInfoReq){

        return Result.success(wxAppService.getUserInfo(userInfoReq));
    }

    @PostMapping("/addEvaluate")
    public Result addEvaluate(@RequestBody EvaluateInfo evaluateInfo){

        return Result.success(wxAppService.addEvaluate(evaluateInfo));
    }

    @PostMapping("/getEvaluateList")
    public Result getEvaluateList(@RequestBody EvaluateReq evaluateReq){

        return Result.success(wxAppService.getEvaluateList(evaluateReq));
    }

    @PostMapping("/getEvaluateDetail")
    public Result getEvaluateDetail(@RequestParam Integer id){

        return Result.success(wxAppService.getEvaluateDetail(id));
    }

    @PostMapping("/feedback")
    public Result feedback(@RequestBody FeedBackReq req){

        return Result.success(wxAppService.feedback(req));
    }

}
