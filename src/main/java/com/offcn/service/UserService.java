package com.offcn.service;

import com.offcn.pojo.User;
import com.offcn.utils.BaseResult;
import com.offcn.utils.PageResult;

import javax.servlet.http.HttpSession;

public interface UserService {
    //普通用户登录
    BaseResult userLogin(User user, HttpSession session);
    //管理员登陆
    BaseResult adminLogin(User user, HttpSession session);

    //查询分页数据的方法
    PageResult findByPage(PageResult pageResult);
    //添加数据的方法
    BaseResult addUser(User user);
    //批量删除数据的方法
    BaseResult delAll(String uids);
    //修改用户数据的方法
    BaseResult editUser(User user);
    //判断该手机号是否注册
    BaseResult checkPhone(String phone);
    //普通用户注册的方法
    void register(User user);
}
