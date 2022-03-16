package com.offcn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer cid;

    private Integer uid;

    /**
     * 对应的课程信息记录
     */
    @TableField(exist = false)
    private Course course;

    /**
     * 对应的用户信息
     */
    @TableField(exist = false)
    private User user;

}
