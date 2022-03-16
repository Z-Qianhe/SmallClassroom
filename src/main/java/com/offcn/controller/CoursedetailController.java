package com.offcn.controller;


import com.offcn.pojo.Course;
import com.offcn.pojo.Coursedetail;
import com.offcn.service.CourseService;
import com.offcn.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/coursedetail")
public class CoursedetailController {
    @Autowired
    CourseService courseService;

    /**
     * 添加课程详情的方法  通过调用业务层异常来查看是否添加成功
     * @param coursedetail
     * @return
     */

    @RequestMapping("/addCourseDetail")
    public BaseResult addCourseDetail(@RequestBody Coursedetail coursedetail){
        try {
            //调用业务层做添加的处理
            courseService.courseService(coursedetail);
            //如果没有异常，证明数据添加成功
            return new BaseResult(true,"课程详情添加成功");
        }catch (Exception e){
            e.printStackTrace();
            //如果抛出异常，出现问题 告诉前端，添加失败和原因
            return new BaseResult(false,e.getMessage());
        }
    }

    /**
     * 根据课程cid查询对应的课程详情数据  并且将数据封装到map
     * @param cid
     * @return
     */
    @RequestMapping("/getCourseDetailByCid")
    public BaseResult getCourseDetailByCid(Integer cid){
        try {
            //调用业务层重新对应课程详情数据
            Map<String, List<Coursedetail>> map = courseService.getCourseDetailByCid(cid);
            //如果查询成功将数据返回
            return new BaseResult(true,"成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败
            return new BaseResult(false,"查询失败");
        }
    }

}


