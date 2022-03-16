package com.offcn.controller;


import com.offcn.pojo.CourseUser;
import com.offcn.service.CourseUserService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/course-user")
public class CourseUserController {

    @Autowired
    CourseUserService courseUserService;

    /**
     * 分页查询选课数据
     * @param pageResult
     * @return
     */
    @RequestMapping("/getCourseUserByPage")
    public BaseResult getCourseUserByPage(@RequestBody PageResult pageResult){
        try {
            //在业务层查询分页数据并返回分页工具类
            pageResult = courseUserService.getCourseUserByPage(pageResult);
            //如果获取到对应的数据
            return new BaseResult(true,"查询成功",pageResult);
        }catch (Exception e){
            e.printStackTrace();

            return new BaseResult(false,"分页数据查询失败");
        }
    }

    /**
     * 批量删除选课数据的方法
     * @param cids
     * @return
     */
    @RequestMapping("/delAll")
    public BaseResult delAll(String cids){
        //调用业务层进行批量删除
        BaseResult result = courseUserService.delAll(cids);
        //将删除结果返回
        return result;
    }

    /**
     * 修改选课数据的方法
     * @param courseUser
     * @return
     */
    @RequestMapping("/edit")
    public BaseResult edit(@RequestBody CourseUser courseUser){
        try{
            //调用业务层做修改功能
            courseUserService.editCourseUserById(courseUser);
            //修改成功
            return new BaseResult(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            //修改失败
            return new BaseResult(false,e.getMessage());
        }
    }

    /**
     * 课程购买后添加选课数据的方法
     * @param cid
     * @param uid
     * @param response
     * @throws IOException
     */
    @RequestMapping("/buy")
    public void buySource(Integer cid, Integer uid, HttpServletResponse response) throws IOException{
        //调用业务层完成添加订单数据的操作
        courseUserService.addCourseUser(cid,uid);
        //跳转到课程详情页面
        response.sendRedirect("/xiaou/pages/videoDetail.html?cid="+cid);
    }

    /**
     * 判断当前用户是否购买课程
     * @param cid
     * @param uid
     * @return
     */
    @RequestMapping("/buyOrStudy")
    public BaseResult buyOrStudy(Integer cid,Integer uid){
//调用业务层查询并返回结果
        return courseUserService.buyOrStudy(cid,uid);
    }

}

