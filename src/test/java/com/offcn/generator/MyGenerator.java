package com.offcn.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGenerator {
    //主程序实现代码生成器
    public static void main(String[] args){
        //创建代码生成器的主类
        AutoGenerator mpg = new AutoGenerator();

        //添加全局的设置
        GlobalConfig gc = new GlobalConfig();
        //获取当前项目的路径
        String propertyPath = System.getProperty("user.dir");
        //设置代码生成后将代码放到哪个路径
        gc.setOutputDir(propertyPath+"/src/main/java");
        //可以设置作者
        gc.setAuthor("syw");
        //设置生成新的代码后是否覆盖原有的代码
        gc.setFileOverride(true);
        //生成后是否打开文件
        gc.setOpen(false);
        //实体类属性中添加注释swagger2
        gc.setSwagger2(false);
        //将全局设置添加到代码生成器主类中
        mpg.setGlobalConfig(gc);


        //设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        //设置驱动、url、用户名、密码
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUrl("jdbc:mysql:///uedu");
        dsc.setUsername("root");
        dsc.setPassword("1234");
        //设置数据库的类型
        dsc.setDbType(DbType.MYSQL);
        //将数据库设置添加到生成器主类
        mpg.setDataSource(dsc);

        //设置package配置
        PackageConfig pc = new PackageConfig();
        //设置总包的名称
        pc.setParent("com.offcn");
        //设置控制层对应的包路径
        pc.setController("controller");
        //设置mapper对应包路径
        pc.setMapper("mapper");
        //设置实体类对应的包路径
        pc.setEntity("pojo");
        //将package设置添加到生成器主类
        mpg.setPackageInfo(pc);


        //添加一个模板配置
        TemplateConfig tc = new TemplateConfig();
        //默认关闭不需要生成的内容  业务层接口
        tc.setService(null);
        //业务层实现类
        tc.setServiceImpl(null);
        //将模板设置添加到生成器主类
        mpg.setTemplate(tc);

        //设置生成策略配置
        StrategyConfig sc = new StrategyConfig();
        //通过字符串设置哪些表需要生成代码
        String tableNames = "course,course_user,coursedetail,user";
        //设置哪些表需要生成代码
        sc.setInclude(tableNames.split(","));
        //设置实体类命名的策略 驼峰命名法
        sc.setNaming(NamingStrategy.underline_to_camel);
        //设置在生成表的时候将表名的前缀去掉
        sc.setTablePrefix(pc.getModuleName()+"_");
        //设置属性的命名策略
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置使用lombok插件
        sc.setEntityLombokModel(true);
        //设置result风格
        sc.setRestControllerStyle(true);
        //设置url中驼峰命名转连接符
        sc.setControllerMappingHyphenStyle(true);
        //将策略配置添加到生成器主类
        mpg.setStrategy(sc);

        //执行代码生成器
        mpg.execute();

    }
}
