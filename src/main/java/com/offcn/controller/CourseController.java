package com.offcn.controller;


import com.offcn.pojo.Course;
import com.offcn.service.CourseService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 课程表 前端控制器
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @RequestMapping("/getCourseByPage")

    /**
     * 查询课程分页的方法
     * @param pageResult 分页的参数
     * @return
     */

    public BaseResult getCourseByPage(@RequestBody PageResult pageResult){
        try{
            //调用业务层做分页数据查询的方法
            pageResult = courseService.getCourseByPage(pageResult);
            //如果没有错误，分页数据查询成功
            return new BaseResult(true,"分页查询成功",pageResult);

        }catch (Exception e){
            e.printStackTrace();
            //出现错误查询失败
            return new BaseResult(false,"分页查询失败");
        }

    }

    /**
     * 图片或者视频上传的方法
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public BaseResult fileUpload(MultipartFile file){
        //将返回的结果集创建
        BaseResult result = new BaseResult();
        //判断上传的文件是否为空
        if (file != null && file.getSize()>0){
            //获取当前上传文件的名称
            String originalFilename = file.getOriginalFilename();
            //获取文件的后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            //根据文件后缀判断上传的是图片还是视频
            if(suffix.contains("jpg") || suffix.contains("png") || suffix.contains("jpeg") || suffix.contains("gif")){
                //返回的状态设置图片
                result.setStatus(1);
            }else {
                //返回的状态设置视频
                result.setStatus(2);
            }
            //为当前文件随机创建一个名称
            String newName = UUID.randomUUID().toString()+suffix;
            //创建一个file类  用来描述放置文件的位置
            File dest = new File("E:\\学习\\JAVA\\project\\uploadfiles",newName);
            try {
                //文件上传
                file.transferTo(dest);
                //文件上传成功
                result.setFlag(true);
                //将图片或者视频的url地址放到数据
                result.setData("/uploadFile/"+newName);
            }catch (IOException e){
                e.printStackTrace();
                //上传失败
                result.setFlag(false);
                //添加消息
                result.setMessage(result.getStatus() == 1 ? "图片上传失败" : "视频上传失败");
            }
        }else {
            //上传的文件是空
            result.setFlag(false);
            //消息
            result.setMessage("必须有文件能上传");
        }
        return result;
    }


    /**
     * 根据提交的文件名删除文件的方法
     * @param fileName
     * @return
     */
    @RequestMapping("/removeFile")
    public BaseResult removeFile(String fileName){
        //返回的结果声明
        BaseResult result = new BaseResult();
        try {
            //得到的文件名是包含URL的  需要将地址去掉
            fileName = fileName.substring(fileName.lastIndexOf("/")+1);
            //创建一个file文件类  表示要删除的文件
            File file = new File("E:\\学习\\JAVA\\project\\uploadfiles",fileName);
            //判断一下当前的文件是否存在
            if (file.exists()){
                //如果有这个文件  删除掉这个文件
                file.delete();
                //添加返回的结果
                result.setMessage("文件删除成功");
            }else {
                //文件不存在
                result.setMessage("文件不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            //出现错误，删除失败
            result.setMessage("文件删除失败！");
        }
        //返回操作的结果
        return  result;
    }

    /**
     * 添加课程的方法
     * @param course
     * @return
     */
    @RequestMapping("/addCourse")
    public BaseResult addCourse(@RequestBody Course course){
        //调用业务层添加对应的数据
        BaseResult result = courseService.addCourse(course);
        //返回结果
        return result;
    }

    /**
     * 修改课程的方法
     * @param course
     * @return
     */
    @RequestMapping("/editCourse")
    public BaseResult editCourse(@RequestBody Course course){
        //调用业务层处理结果
        BaseResult result = courseService.editCourse(course);
        //返回结果
        return result;
    }

    /**
     * 根据传递的ids批量删除数据
     * @param cids
     * @return
     */
    @RequestMapping("/delAll")
    public BaseResult delAll(String cids){
        //调用业务层进行批量删除
        BaseResult result = courseService.delAll(cids);
        return result;
    }

    /**
     * 获取所有课程信息的方法
     * @return
     */
    @RequestMapping("/getAll")
    public BaseResult getAll(){
        try {
            //调用业务层获取所有的课程
            List<Course> courses = courseService.getAll();
            //将所有的课程传递到前端
            return new BaseResult(true,"获取成功",courses);
        }catch (Exception e){
            e.printStackTrace();
            //获取失败
            return new BaseResult(false,"获取失败");
        }
    }

    @RequestMapping("/getByCourseType")
    public List<Course> getByCourseType(Integer type,Integer count){
        //调用业务层查询对应的数据
        List<Course> list = courseService.getByCourseType(type,count);
        //返回结果
        return list;
    }

    /**
     * 根据课程cid查询课程信息的方法
     * @param cid
     * @return
     */
    @RequestMapping("/getCourseByCid")
    public BaseResult getCourseByCid(Integer cid){
        try {
            //调用业务层根据id查询数据
            Course course = courseService.getCourseByCid(cid);
            //如果没有错误
            return new BaseResult(true,"查询成功",course);
        }catch (Exception e){
            e.printStackTrace();
            //出现错误
            return new BaseResult(false,"查询失败");
        }
    }


}

