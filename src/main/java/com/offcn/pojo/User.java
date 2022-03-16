package com.offcn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学员编号
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 注册时间
     */
    private LocalDateTime createtime;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 头像
     */
    private String picture;

    /**
     * 验证码  介绍前台传递验证码  不能查询数据库
     */
    @TableField(exist = false)
    private String code;


}
