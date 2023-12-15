package com.wxapp.pojo;

import java.util.Date;

/**
 * @program: wxapp
 * @description:
 * @author: WONGhaiLONG
 * @create: 2023-12-13 19:43
 **/
public class EvaluateReq {
    private String scNo;
    private Date startTime;
    private Date endTime;
    private Integer pageNum;
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getScNo() {
        return scNo;
    }

    public void setScNo(String scNo) {
        this.scNo = scNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
