package com.offcn.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * 课程名称
     */
    @TableField("courseName")
    private String courseName;

    /**
     * 课程简介
     */
    private String descs;

    /**
     * 课程类型
     */
    @TableField("courseType")
    private Integer courseType;

    /**
     * 课程图片地址
     */
    @TableField("courseImage")
    private String courseImage;

    /**
     * 课程视频地址
     */
    @TableField("courseVideo")
    private String courseVideo;

    /**
     * 价格
     */
    @TableField("coursePrice")
    private BigDecimal coursePrice;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 上传时间
     */
    @TableField("createTime")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDateTime createTime;

    /**
     * 用来计算购买课程的人数
     */
    @TableField(exist = false)
    private int count;
}
