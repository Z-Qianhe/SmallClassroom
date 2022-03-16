package com.offcn.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.offcn.pojo.User;
import com.offcn.service.UserService;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;
import com.offcn.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author syw
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 输出验证的方法
     * @param response  用来写出验证码
     * @param session   用来存放验证码数据
     */

    @RequestMapping("/createCode")
    public void createCode(HttpServletResponse response, HttpSession session){
        //调用验证码工具类生成4位的验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //将验证码存放到session中
        session.setAttribute("verifyCode",code);
        try{
            //将验证码生成内存图片写出
            VerifyCodeUtils.outputImage(100,30,response.getOutputStream(),code);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //后台管理员登陆的方法

    @RequestMapping("/loginCheck")
    public BaseResult loginCheck(User user,HttpSession session){
        //调用业务逻辑层类实现登陆的功能
        BaseResult baseResult = userService.adminLogin(user,session);
        //返回前台需要的登陆结果
        return baseResult;

    }

    /**
     * 普通用户登陆的方法
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/loginUser")
    public BaseResult loginUser(@RequestBody User user,HttpSession session){
        //调用业务逻辑层实现普通用户登陆
        BaseResult result = userService.userLogin(user,session);
        //返回结果
        return result;
    }

    /**
     * 获取当前登陆用户数据的方法
     * @param session
     * @return
     */
    @RequestMapping("/getLoginUser")
    private BaseResult getLoginUser(HttpSession session){
        //创建返回的结果
        BaseResult result = new BaseResult();
        //session中获取数据
        Object obj = session.getAttribute("user");
        //判断当前用户是否登陆
        if(obj != null){

            //用户已经登陆  将登陆数据存放到返回封装类
            result.setFlag(true);
            //设置登陆的UI想
            result.setData((User)obj);

        }else {
            //用户没有登陆，返回错误结果
            result.setFlag(false);
            //可以返回打印的数据
            result.setMessage("用户没有登陆，请登陆！");
        }
        //返回结果集封装
        return result;
    }

    /**
     * 用户退出登陆的方法
     * @param session
     * @return
     */
    @RequestMapping("/loginOut")
    public BaseResult loginOut(HttpSession session){
        //设置session失效
        session.invalidate();
        //创建返回的结果
        BaseResult result = new BaseResult();
        //添加返回的消息
        result.setMessage("成功退出");
        //返回结果
        return result;
    }


    /**
     * 分页查询的方法
     * @param pageResult
     * @return
     */
    @RequestMapping("/findByPage")
    public BaseResult findByPage(@RequestBody PageResult pageResult){
        //定义需要返回的数据
        BaseResult baseResult = new BaseResult();
        try {
            //调用业务层获取分页数据
            PageResult result = userService.findByPage(pageResult);
            //证明查询成功
            baseResult.setFlag(true);
            //添加数据
            baseResult.setData(result);
        }catch (Exception e){
            e.printStackTrace();
            //出现异常，告诉前台有问题
            baseResult.setFlag(false);
            //提示信息
            baseResult.setMessage("分页查询失败");
        }
        //返回数据
        return baseResult;
    }

    /**
     * 新增用户的方法
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public BaseResult addUser(@RequestBody User user){
        //调用业务层添加数据 并且返回封装的返回数据
        BaseResult result = userService.addUser(user);
        //返回结果
        return result;
    }

    /**
     * 批量删除用户数据的方法
     * @param uids 字符串  类似 1,2,3...
     * @return
     */
    @RequestMapping("/delAll")
    public BaseResult delAll(String uids){
        //调用业务层删除数据
        BaseResult result = userService.delAll(uids);
        //返回结果
        return result;
    }

    /**
     * 修改用户数据的方法
     * @param user
     * @return
     */
    @RequestMapping("/editUser")
    public BaseResult editUser(@RequestBody User user){
        //调用业务层修改数据
        BaseResult result = userService.editUser(user);
        //返回结果
        return result;
    }

    /**
     * 判断当前手机号是否是注册账号
     * @param phone
     * @return
     */
    @RequestMapping("/checkPhone")
    public BaseResult checkPhone(String phone){
        //通过业务层查询手机号是否已经注册
        BaseResult result = userService.checkPhone(phone);
        //返回查询的结果
        return result;
    }

    /**
     * 用户注册的方法
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public BaseResult register(@RequestBody User user){
        try {
            //调用业务层注册用户
            userService.register(user);
            //注册成功
            return new BaseResult(true,"用户注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            //注册失败 出现异常
            return new BaseResult(false,"用户注册失败！");
        }
    }
}

