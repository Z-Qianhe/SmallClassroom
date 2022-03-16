package com.offcn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置一个本地的文件对应一个url路径
        registry.addResourceHandler("/uploadFile/**")
                .addResourceLocations("file:E:\\学习\\JAVA\\project\\uploadfiles\\");
    }

    //添加一个项目首页的配置 访问/的时候需要我们跳转到那个页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //将  首页设置跳转到对应的页面
        registry.addViewController("/")
                //通过重定向  让跳转后可以展示全路径
                .setViewName("redirect:/xiaou/pages/loginAndRegister/login.html");
    }
}
