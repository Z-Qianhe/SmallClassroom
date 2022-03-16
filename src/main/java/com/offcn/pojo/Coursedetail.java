package com.offcn.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Coursedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private String url;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private String startData;

    private Integer cid;


}
