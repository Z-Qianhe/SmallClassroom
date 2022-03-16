package com.offcn.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.offcn.mapper.CourseMapper;
import com.offcn.mapper.CourseUserMapper;
import com.offcn.mapper.CoursedetailMapper;
import com.offcn.pojo.Course;
import com.offcn.pojo.CourseUser;
import com.offcn.pojo.Coursedetail;
import com.offcn.service.CourseService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class  CourseServiceImpl implements CourseService {
    @Resource
    CourseMapper courseMapper;
    @Resource
    CoursedetailMapper coursedetailMapper;
    @Resource
    CourseUserMapper courseUserMapper;


    @Override
    public PageResult getCourseByPage(PageResult pageResult) {
        //创建一个条件构造器
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        //根据入参的queryString进行判断 看是否需要做查询
        if(pageResult.getQueryString() != null &&pageResult.getQueryString().trim().length() > 0){
            //将查询数据获取到
            String queryString = pageResult.getQueryString();
            //根据课程名称进行模糊查询
            lqw.like(Course::getCourseName,queryString);
        }
        //判断是否按照类型做查询
        if(pageResult.getCourseType() != 0){
            //添加类型查询
            lqw.eq(Course::getCourseType,pageResult.getCourseType());
        }
        //创建分页查询的page对象
        Page<Course> page = new Page<>(pageResult.getCurrentPage(),pageResult.getPageSize());
        //使用分页查询的方法
        courseMapper.selectPage(page,lqw);
        //得到数据
        List<Course> courses = page.getRecords();
        //查询课程报名的人数
        //需要查询每一个课程中有多少人购买了
        for (Course cours:courses){
            //获取到课程的id
            Integer cid = cours.getCid();
            //创建中间表条件构造器
            LambdaQueryWrapper<CourseUser> clqw = new LambdaQueryWrapper<>();
            //添加cid的条件
            clqw.eq(CourseUser::getCid,cid);
            //查询结果
            Integer count1 = courseUserMapper.selectCount(clqw);
            //将结果放入到课程中
            cours.setCount(count1);
        }
        //将数据放入到分页工具类
        pageResult.setTotalCount(page.getTotal());
        pageResult.setRows(courses);
        //返回结果
        return pageResult;
    }

    @Override
    public BaseResult addCourse(Course course){
        //创建返回的值
        BaseResult result = new BaseResult();
            try {
                //处理图片的名称  将url去掉
                course.setCourseImage(course.getCourseImage().substring(course.getCourseImage().lastIndexOf("/")+1));
                //处理视频名称 将url去掉
                course.setCourseVideo(course.getCourseVideo().substring(course.getCourseVideo().lastIndexOf("/")+1));
                //添加创建时间
                course.setCreateTime(LocalDateTime.now());
                //添加数据
                courseMapper.insert(course);
                //添加成功 在返回值中添加结果
                result.setFlag(true);
                //添加消息
                result.setMessage("课程添加成功！");
            }catch (Exception e){
                e.printStackTrace();
                //如果有异常出现
                result.setFlag(false);
                //错误的信息
                result.setMessage("课程添加失败！");
            }
        //返回结果
        return result;
    }

    @Override
    public BaseResult editCourse(Course course){
        //判断图片中是否/  有的话去掉
        if (course.getCourseImage().indexOf("/")!=-1){
            //图片中是由/
            course.setCourseImage(course.getCourseImage().substring(course.getCourseImage().lastIndexOf("/")+1));
        }
        //判断视频中是否有/
        if(course.getCourseVideo().indexOf("/") != -1){
            //如果有 /  去掉
            course.setCourseVideo(course.getCourseVideo().substring(course.getCourseVideo().lastIndexOf("/")+1));
        }
        //创建返回的值
        BaseResult result = new BaseResult();
        try {
            //修改数据
            courseMapper.updateById(course);
            //添加成功 在返回值中添加结果
            result.setFlag(true);
            //添加消息
            result.setMessage("课程修改成功！");
        }catch (Exception e){
            e.printStackTrace();
            //如果有异常出现
            result.setFlag(false);
            //错误的信息
            result.setMessage("课程修改失败！");
        }
        //返回结果
        return result;
    }

    @Override
    public BaseResult delAll(String cids){
        //定义返回值
        BaseResult result = new BaseResult();
        //添加一个错误结果
        result.setFlag(false);
        try {
            //将数据转化为数组
            String[] splits = cids.split(",");
            //将数组转化为集合
            List asList = Arrays.asList(splits);
            //在删除主表数据的前面添加删除副表数据  创建条件构造器
            LambdaQueryWrapper<Coursedetail> lqw = new LambdaQueryWrapper<>();
            //条件需要删除的条件
            lqw.in(Coursedetail::getCid,asList);
            //调用删除的方法去删除课程详情的数据
            coursedetailMapper.delete(lqw);
            //同时删除课程和用户中间表数据
            LambdaQueryWrapper<CourseUser> clqw = new LambdaQueryWrapper<>();
            //添加条件
            clqw.in(CourseUser::getCid,asList);

            //根据课程的ids查询到对应的课程数据
            List<Course> list = courseMapper.selectBatchIds(asList);
            //遍历数据
            for (Course course : list) {
                //获取当前课程的图片地址
                String courseImage = course.getCourseImage();
                //判断文件在数据库是否存放的是null
                if (courseImage!=null&&courseImage.length()>0){
                    //根据图片的名称创建一个文件类
                    File file = new File("E:\\学习\\JAVA\\project\\uploadfiles",courseImage);
                    //判断当前文件是否存在
                    if (file.exists()){
                        //文件存在  将文件删除
                        file.delete();
                    }
                }
                //获取到当前课程的视频地址
                String courseVideo = course.getCourseVideo();
                if (courseVideo!=null&&courseVideo.length()>0){
                    //根据图片的名称创建一个文件类
                    File file = new File("E:\\学习\\JAVA\\project\\uploadfiles",courseVideo);
                    //判断当前文件是否存在
                    if(file.exists()){
                        //文件存在  将文件删除
                        file.delete();
                    }
                }
            }


            //调用批量删除的方法
            int i = courseMapper.deleteBatchIds(asList);
            //根据删除的结果判断是否删除成功
            if (i>0){
                //证明删除成功
                result.setFlag(true);
                //消息
                result.setMessage("批量删除成功！");
            }else {
                //失败
                result.setMessage("批量删除失败，没有删除数据！");
            }
        }catch (Exception e){
            e.printStackTrace();
            //设置错误的消息
            result.setMessage("出现错误，删除失败");
        }
        //返回结果
        return result;
    }

    @Override
    public void courseService(Coursedetail coursedetail) throws Exception{
        //判断当前提交课程详情名称
        if (coursedetail.getName()==null||coursedetail.getName().length()==0){
            //没有写课程详情的名称
            throw new Exception("课程名称没有填写");
        }
        //判断课程对应的章节是否选择
        if (coursedetail.getType()==null||coursedetail.getType().length()==0||coursedetail.getType().equals("0")){
            //没有选择课程的章节
            throw new Exception("课程的章节没有选择");
        }else {
            //将章节选择的1,2,3转化为第一章、第二章、第三章
            switch (coursedetail.getType()){
                case "1" : coursedetail.setType("第一章");
                case "2" : coursedetail.setType("第二章");
                case "3" : coursedetail.setType("第三章");
                default:
            }
        }
        //对开课时间进行判断
        if (coursedetail.getStartData()==null||coursedetail.getStartData().length()==0){
            //没有设置时间
            throw new Exception("课程开课时间没有添加");
        }
        //判断课程的视频是否添加
        if(coursedetail.getUrl()==null||coursedetail.getUrl().length()==0){
            //没有添加视频
            throw new Exception("课程详情的视频必须添加");
        }else {
            //将hip地址中的url前缀去掉
            coursedetail.setUrl(coursedetail.getUrl().replace("/uploadFile/",""));
        }
        //正常的添加数据
        coursedetailMapper.insert(coursedetail);

    }

    @Override
    public List<Course> getAll(){
        //获取所有的课程数据
        List<Course> courses = courseMapper.selectList(null);
        //返回数据
        return courses;
    }

    @Override
    public  List<Course> getByCourseType(Integer type,Integer count){
        //创建课程条将构造器
        LambdaQueryWrapper<Course> clqw = new LambdaQueryWrapper<>();
        //添加课程对应的信息
        if(type == 1 || type == 2 || type == 3){
            //添加课程条件
            clqw.eq(Course::getCourseType,type);
        }
        //设置查询的数据条数
        clqw.last("limit "+ count);
        //查询结果
        List<Course> courses = courseMapper.selectList(clqw);
        //需要查询每一个课程中有多少人购买了
        for (Course cours : courses) {
            //获取到课程的id
            Integer cid = cours.getCid();
            //创建中间表条件构造器
            LambdaQueryWrapper<CourseUser> lqw = new LambdaQueryWrapper<>();
            //添加cid的条件
            lqw.eq(CourseUser::getCid,cid);
            //查询结果
            Integer count1 = courseUserMapper.selectCount(lqw);
            //将结果放入到课程中
            cours.setCount(count1);
        }
        //返回查询结果
        return courses;
    }

    @Override
    public Course getCourseByCid(Integer cid) {
        //根据cid查询课程信息
        Course course = courseMapper.selectById(cid);
        //返回数据
        return course;
    }

    @Override
    public Map<String,List<Coursedetail>> getCourseDetailByCid(Integer cid) {
        //创建返回数据容器
        Map<String, List<Coursedetail>> map = new HashMap<>();
        //创建一个mybatis-plus的条件构造器
        QueryWrapper<Coursedetail> qw = new QueryWrapper<>();
        //添加根据课程cid查询
        qw.eq("cid",cid);
        //根据类型查询 去重
        qw.select("distinct type");
        //查询结果
        List<Coursedetail> coursedetails = coursedetailMapper.selectList(qw);
        //判断当前课程是否有目录
        if(!coursedetails.isEmpty()){
            //遍历数据
            for (Coursedetail coursedetail : coursedetails) {
                //创建一个条件构造器
                qw = new QueryWrapper<>();
                //添加课程cid条件
                qw.eq("cid",cid);
                //添加章节条件
                qw.eq("type",coursedetail.getType());
                //查询结果
                List<Coursedetail> selectList = coursedetailMapper.selectList(qw);
                //将数据封装到map中
                map.put(coursedetail.getType(),selectList);
            }
        }
        //返回最终结果
        return map;
    }
}
