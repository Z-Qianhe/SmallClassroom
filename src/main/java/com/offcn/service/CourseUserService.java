package com.offcn.service;

import com.offcn.pojo.CourseUser;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;

public interface CourseUserService {
    //分页查询选课数据
    PageResult getCourseUserByPage(PageResult pageResult);
    //批量删除选课数据
    BaseResult delAll(String cids);
    //修改选课数据
    void editCourseUserById(CourseUser courseUser);
    //添加选课数据的方法
    BaseResult addCourseUser(Integer cid, Integer uid);
    //查询登陆用户是否购买的方法
    BaseResult buyOrStudy(Integer cid, Integer uid);

}
