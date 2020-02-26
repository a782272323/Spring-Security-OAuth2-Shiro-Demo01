package learn.lhb.security.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 用java代码替代xml文件
 * spring 容器配置
 *
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 01:55
 */
@Configuration
@ComponentScan(basePackages = "learn.lhb.security.springmvc"
            ,excludeFilters =  {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {

    //在此配置除了Controller的其它bean，比如：数据库链接池、事务管理器、业务bean等。

}
