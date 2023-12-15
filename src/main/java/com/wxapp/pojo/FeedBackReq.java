package com.wxapp.pojo;

import java.util.Date;

/**
 * @program: wxapp
 * @description:
 * @author: WONGhaiLONG
 * @create: 2023-12-13 22:29
 **/
public class FeedBackReq {
    private Integer id;
    /**
     * 服务态度
     */
    private Integer serviceScore;

    /**
     * 响应速度
     */
    private Integer responseScore;

    /**
     * 是否在规定时间内完成 0-否，1-是
     */
    private Integer isTimeComplete;

    /**
     * 意见及反馈
     */
    private String feedback;

    /**
     * 提交人签名
     */
    private String commitUserSign;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 本次服务是否完成 0-否，1-完成
     */
    private Integer serviceDone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Integer serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Integer getResponseScore() {
        return responseScore;
    }

    public void setResponseScore(Integer responseScore) {
        this.responseScore = responseScore;
    }

    public Integer getIsTimeComplete() {
        return isTimeComplete;
    }

    public void setIsTimeComplete(Integer isTimeComplete) {
        this.isTimeComplete = isTimeComplete;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCommitUserSign() {
        return commitUserSign;
    }

    public void setCommitUserSign(String commitUserSign) {
        this.commitUserSign = commitUserSign;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getServiceDone() {
        return serviceDone;
    }

    public void setServiceDone(Integer serviceDone) {
        this.serviceDone = serviceDone;
    }
}
