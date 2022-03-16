package com.offcn.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.offcn.mapper.CourseMapper;
import com.offcn.mapper.CourseUserMapper;
import com.offcn.mapper.UserMapper;
import com.offcn.pojo.Course;
import com.offcn.pojo.CourseUser;
import com.offcn.pojo.User;
import com.offcn.service.CourseUserService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseUserServiceImpl implements CourseUserService {
    @Resource
    CourseUserMapper courseUserMapper;
    @Resource
    CourseMapper courseMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public PageResult getCourseUserByPage(PageResult pageResult){
        //选课表查询的条件构造器
        LambdaQueryWrapper<CourseUser> qw = new LambdaQueryWrapper<>();
        //判断前端传递的数据中是否有查询条件的数据
        if (pageResult.getQueryString()!=null&&pageResult.getQueryString().trim().length()>0){
            //根据选课人名称模糊查询   使用user表进行查询
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            //添加查询条件  根据用户名模糊查询
            lqw.like(User::getName,pageResult.getQueryString());
            //设置需要查询的列
            lqw.select(User::getUid);
            //查询结果并将结果转化为一个list<integer>集合
            List<Integer> uids = userMapper.selectObjs(lqw)
                    .stream().map(o -> (Integer)o).collect(Collectors.toList());
            //判断如果根据模糊查询查询到数据
            if (!uids.isEmpty()){
                //将查询到的uids作为选课表查询条件
                qw.in(CourseUser::getUid,uids);
            }else {
                //使用模糊查询没有结果
                return pageResult;
            }

        }

        //创建一个分页的工具类
        Page<CourseUser> page = new Page<>(pageResult.getCurrentPage(),pageResult.getPageSize());
        //分页查询数据
        courseUserMapper.selectPage(page,qw);
        //查询类到结果 将结果获取
        List<CourseUser> records = page.getRecords();
        //遍历结果  在数据中添加对应的用户和课程数据
        for (CourseUser record:records){
            //获取到当前选课数据库中用户id
            Integer uid = record.getUid();
            //根据用户id获取用户的数据
            User user = userMapper.selectById(uid);
            //将用户数据放入到课程数据
            record.setUser(user);
            //获取当前选课数据中的课程id
            Integer cid = record.getCid();
            //根据课程id获取课程数据
            Course course = courseMapper.selectById(cid);
            //将课程放入到选课对应的数据中
            record.setCourse(course);

        }

        //将数据总数添加到工具类
        pageResult.setTotalCount(page.getTotal());
        //将分页数据放入到工具类
        pageResult.setRows(records);
        //返回分页的结果
        return pageResult;
    }


    @Override
    public BaseResult delAll(String cids){
        //定义返回值
        BaseResult result = new BaseResult();
        //添加一个错误结果
        result.setFlag(false);
        try {
            //将数据转化为数组
            String[] split = cids.split(",");
            //将数组转化为集合
            List asList = Arrays.asList(split);
            //根据主键删除数据
            courseUserMapper.deleteBatchIds(asList);
            //删除成功
            result.setFlag(true);
            //消息
            result.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            //添加删除失败
            result.setMessage("删除失败！");
        }
        //返回结果集
        return result;
    }

    @Override
    public void editCourseUserById(CourseUser courseUser) {
        //根据id查询原有的数据
        CourseUser selectById = courseUserMapper.selectById(courseUser.getId());
        //进行数据对比，如果修改的数据和原有的一样
        if (selectById.getCid().equals(courseUser.getCid())){
            //抛出异常
            throw new RuntimeException("课程没有改变");
        }
        //如果修改后的数据发生改变了  修改数据
        courseUserMapper.updateById(courseUser);
    }

    @Override
    public BaseResult addCourseUser(Integer cid,Integer uid){
        //创建返回值
        BaseResult result = new BaseResult();
        try {
            //创建选课实体类对象
            CourseUser cu = new CourseUser();
            //添加课程id
            cu.setCid(cid);
            //添加用户id
            cu.setUid(uid);
            //添加数据
            courseUserMapper.insert(cu);
            //返回正确结果
            result.setFlag(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFlag(false);
        }
        //返回结果
        return result;
    }

    @Override
    public BaseResult buyOrStudy(Integer cid, Integer uid) {
        //创建一个返回结果
        BaseResult result = new BaseResult();
        try {
            //创建一个查询的条件构造器
            LambdaQueryWrapper<CourseUser> lqw = new LambdaQueryWrapper<>();
            //添加条件
            lqw.eq(CourseUser::getCid,cid);
            lqw.eq(CourseUser::getUid,uid);
            //进行查询
            Integer count = courseUserMapper.selectCount(lqw);
            //根据查询的结果确定是否购买
            result.setFlag(count == 1);
        } catch (Exception e) {
            e.printStackTrace();
            result.setFlag(false);
        }
        return result;
    }


}
