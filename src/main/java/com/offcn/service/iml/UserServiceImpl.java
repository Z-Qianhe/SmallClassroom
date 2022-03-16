package com.offcn.service.iml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.offcn.mapper.UserMapper;
import com.offcn.pojo.User;
import com.offcn.service.UserService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service //将当前类放入到spring的容器
public class UserServiceImpl implements UserService {
    @Resource //调用持久层功能
    UserMapper userMapper;

    @Override
    public BaseResult userLogin(User user, HttpSession session){
        //创建一个返回的结果集
        BaseResult result = new BaseResult();
        //将登陆结果提前添加到状态中
        result.setFlag(false);
        //数据库查询  通过条件构造器完成查找user
        QueryWrapper<User> qw = new QueryWrapper<>();
        //添加条件   后台使用username进行道路
        if(user.getUsername() != null){
            //添加根据账户查询的方法
            qw.eq("username",user.getUsername());
        }
        //如果使用phone进行登陆
        if(user.getPhone() != null){
            //添加根据手机号进行查询的方法
            qw.eq("phone",user.getPhone());
        }
        //查询数据库，查看当前用户是否有对应的数据   确保数据库中username和phone  不能重复
        User loginUser = userMapper.selectOne(qw);
        //根据查询到user 查看是否有用户
        if(loginUser == null){
            //没有这个用户 告诉前台  登陆错误原因  没有这个用户
            result.setMessage("该用户不存在");
            //返回结果
            return result;
        }
        //如果有这个用户判断密码是否正确
        if(!loginUser.getPassword().equals(user.getPassword())){
            //密码不正确 告诉前台  登陆错误原因  没有这个用户
            result.setMessage("密码错误");
            //返回结果
            return result;
        }
        //判断当前用户是否被禁用
        if(loginUser.getStatus() != 1){
            //账户被禁用 告诉前台  登陆错误原因  没有这个用户
            result.setMessage("当前账户被禁用！");
            //返回结果
            return result;
        }
        //证明登陆成功  将用户数据放入到返回值
        result.setData(loginUser);
        //设置登陆状态
        result.setFlag(true);
        //告诉前台登陆成功的信息
        result.setMessage("登陆成功！");
        //将数据存放到session
        session.setAttribute("user",loginUser);
        //返回数据
        return result;

    }

    @Override
    public BaseResult adminLogin(User user,HttpSession session){

        //创建一个返回的结果集
        BaseResult result = new BaseResult();
        //将登陆结果提前添加到状态中
        result.setFlag(false);
        //从session中获取已经存放的验证码数据
        String verifyCode = (String) session.getAttribute("verifyCode");
        //使用前端提交的验证码数据和session中的进行比较
        if(user.getCode() != null && !user.getCode().toUpperCase().equals(verifyCode)) {
            //证明验证码是错误   告诉前台 验证码输入错误  设置result
            result.setMessage("验证码错误！");
            //跳出对应的方法
            return result;
        }
        //调用普通用户登陆的方法
        result = userLogin(user,session);
        //判断当前登陆的用户是否是管理员
        if(result.isFlag()){
            //如果登陆成功 取出存放的用户数据
            User userLogin = (User) result.getData();
            //判断当前的用户是否是管理员
            if(userLogin.getRole() == 3){
                //不是管理员  修改登陆的结果
                result.setFlag(false);
                //消息也重新设置
                result.setMessage("权限不足，请联系管理员！");
            }
        }
        //返回结果
        return result;
    }

    @Override
    public PageResult findByPage(PageResult pageResult){
        //创建一个user的条件构造器
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //判断前台是否传递查询条件
        if (pageResult.getQueryString() != null && pageResult.getQueryString().trim().length()>0){
            //添加真实姓名模糊查询
            lqw.like(User::getName,pageResult.getQueryString());
        }
        //创建一个分页查询的工具类
        Page<User>page = new Page<>(pageResult.getCurrentPage(),pageResult.getPageSize());
        //查询结果
        userMapper.selectPage(page,lqw);
        //将查询到数据存放到自定义分页工具类对象中
        pageResult.setTotalCount(page.getTotal());
        pageResult.setRows(page.getRecords());
        //将结果返回
        return pageResult;
    }

    @Override
    public BaseResult addUser(User user){
        //创建一个需要返回的结果
        BaseResult result = new BaseResult();
        //添加一个错误的状态
        result.setFlag(false);
        try {
            //校验用户名是否重复  创建条件构造器
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
            //添加用户查询的条件
            lqw.eq(User::getUsername,user.getUsername());
            //查询结果
            User selectOne = userMapper.selectOne(lqw);
            //判断是用户名重复
            if (selectOne != null){
                //证明用户名重复  不能继续添加
                result.setMessage("用户名重复，请修改");
                //返回结果  跳出方法
                return result;
            }
            //添加另外一个条件
            lqw.or().eq(User::getPhone,user.getPhone());
            //查询结果
            selectOne = userMapper.selectOne(lqw);
            //判断手机是否重复
            if (selectOne!=null){
                //证明手机号重复 不能继续添加
                result.setMessage("手机号重复，请修改");
                //返回结果  跳出方法
                return result;
            }
            //添加一个用户的创建时间
            user.setCreatetime(LocalDateTime.now());
            //调用mapper添加user数据
            userMapper.insert(user);
            //如果添加成功
            result.setFlag(true);
            //设置添加成功的消息
            result.setMessage("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            //添加失败
            result.setMessage("添加失败，未知错误");
        }
        //返回添加结果
        return result;
    }

    @Override
    public BaseResult delAll(String uids){
        //定义返回值
        BaseResult result = new BaseResult();
        //添加一个错误结果
        result.setFlag(false);

        try {
            //将数据转化为数组
            String[] split = uids.split(",");
            //将数组转化为集合
            List asList = Arrays.asList(split);
            //调用批量删除的方法
            int i = userMapper.deleteBatchIds(asList);
            //根据删除的结果判断是否删除成功
            if(i > 0){
                //证明删除成功
                result.setFlag(true);
                //消息
                result.setMessage("批量删除成功！");
            }else {
                //失败
                result.setMessage("批量删除失败，没有删除数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            //设置错误的消息
            result.setMessage("出现错误，删除失败");
        }

        return result;
    }

    @Override
    public BaseResult editUser(User user){
        //创建一个需要返回的结果集
        BaseResult result = new BaseResult();
        //默认设置一个修改失败
        result.setFlag(false);
        try {
            //校验用户名是否重复  创建条件构造器
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            //添加用户查询的条件
            lqw.eq(User::getUsername,user.getUsername());
            //添加条件  id不能等于修改的数据
            lqw.ne(User::getUid,user.getUid());
            //查询结果
            User selectOne = userMapper.selectOne(lqw);
            //判断是用户名重复
            if(selectOne!=null){
                //证明用户名重复  不能继续添加
                result.setMessage("用户名重复，请修改");
                //返回结果  跳出方法
                return result;
            }
            //将添加构造器重置
            lqw = new LambdaQueryWrapper<>();
            //添加另外一个条件
            lqw.eq(User::getPhone,user.getPhone());
            //添加条件  id不能等于修改的数据
            lqw.ne(User::getUid,user.getUid());
            //查询结果
            selectOne = userMapper.selectOne(lqw);
            //判断手机是否重复
            if(selectOne != null){
                //证明手机号重复 不能继续添加
                result.setMessage("手机号重复，请修改");
                //返回结果  跳出方法
                return result;
            }
            //修改数据
            userMapper.updateById(user);
            //修改成功
            result.setFlag(true);
            //添加消息
            result.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            //出现异常  修改失败
            result.setMessage("出现错误，修改失败");
        }

        return result;
    }

    @Override
    public BaseResult checkPhone(String phone) {
        //创建条件构造器
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        //在条件构造器中添加手机号等于的条件
        lqw.eq(User::getPhone,phone);
        //查询对应结果
        Integer count = userMapper.selectCount(lqw);
        //进行判断 是否有手机号对应用户
        if (count>0){
            //证明当前手机号在数据库表中已经存在
            return new BaseResult(false,"当前手机号已注册");
        }else {
            //证明手机号在数据库表中不存在
            return new BaseResult(true,"手机号未注册");
        }
    }

    @Override
    public void register(User user){
        //添加用户的状态  注册后就可以使用
        user.setStatus(1);
        //添加用户的创建时间
        user.setCreatetime(LocalDateTime.now());
        //添加用户的角色  普通用户3
        user.setRole(3);
        //添加数据
        userMapper.insert(user);
    }

}
