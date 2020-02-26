package learn.lhb.security.spring.boot.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 视图解析器配置
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 15:33
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置根目录 "/"， 默认跳转到Spring Security提供到登录
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 这个重定向是定位到Spring Security自带的登录页面
//        registry.addViewController("/").setViewName("redirect:/login");

        // 自定义登录页面
        // 先定义到自定义的url，然后再自定义到自定义页面
        registry.addViewController("/").setViewName("redirect:/login-view");
        registry.addViewController("/login-view").setViewName("login");
    }
}
