package com.offcn.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusPageConfig {
    @Bean
    public MybatisPlusInterceptor createMybatisPlusInterceptor(){
        //创建一个拦截器对象
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //创建设置对象
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        //添加数据库的设置
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //返回设置结果
        return interceptor;

    }
}
