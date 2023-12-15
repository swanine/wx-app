package com.wxapp.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * evaluate_info
 * @author 
 */
@Data
public class EvaluateInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * sc编号
     */
    private String scNo;

    /**
     * 设备数量
     */
    private Integer deviceNum;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 人数
     */
    private Integer userNum;

    /**
     * 备注
     */
    private String remark;

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

    /**
     * 状态 1-待评价，2-评价完成
     */
    private Integer state;

    /**
     * 提交时间
     */
    private Date commitTime;

    /**
     * 反馈时间
     */
    private Date feedbackTime;

    private static final long serialVersionUID = 1L;
}