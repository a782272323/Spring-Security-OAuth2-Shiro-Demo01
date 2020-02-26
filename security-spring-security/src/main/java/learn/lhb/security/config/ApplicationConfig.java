package learn.lhb.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring 容器配置
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 12:31
 */
@Configuration
@ComponentScan(basePackages = "learn.lhb.security"
            ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class ApplicationConfig {
    // 在此配置除了Controller 的其他Bean ，例如： 数据库连接池，事务管理器，业务Bean等等.
}
