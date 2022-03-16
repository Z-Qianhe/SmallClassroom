package com.offcn.service;

import com.offcn.pojo.Course;
import com.offcn.pojo.Coursedetail;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface CourseService {
    //分页查询数据的方法
    PageResult getCourseByPage(PageResult pageResult);
    //添加课程的方法
    BaseResult addCourse(Course course);
    //修改数据的方法
    BaseResult editCourse(Course course);
    //批量删除数据的方法
    BaseResult delAll(String cids);
    //添加课程详情数据的方法
    void courseService(Coursedetail coursedetail) throws Exception;
    //获取所有课程的方法
    List<Course> getAll();

    //根据类型和条数查询数据
    List<Course> getByCourseType(Integer type, Integer count);
    //根据课程的cid查询课程的方法
    Course getCourseByCid(Integer cid);
    //根据课程cid查询对应的课程详情数据  并且将数据封装到map
    Map<String,List<Coursedetail>> getCourseDetailByCid(Integer cid);
}
