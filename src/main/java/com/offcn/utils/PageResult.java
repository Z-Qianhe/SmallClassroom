package com.offcn.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    //当前页数
    private Integer currentPage;
    //每页的条数
    private Integer pageSize;
    //查询条件
    private String queryString;
    //总条数
    private Long totalCount;
    //当前分页的数据
    private List rows;
    //查询的课程类型
    private int courseType;
}
