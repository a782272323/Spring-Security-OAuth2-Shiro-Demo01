package learn.lhb.security.springmvc.config;

import learn.lhb.security.springmvc.interceptor.SimpleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 用java代码替代xml文件
 * 本案例采用Servlet3.0无web.xml方式，的config包下定义WebConfig.java，它对应s对应于DispatcherServlet配 置。
 * 配置视图解析器
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 01:58
 */
@Configuration
@EnableWebMvc // todo
@ComponentScan(basePackages = "learn.lhb.security.springmvc"
        ,includeFilters =  {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {
    // todo WebMvcConfigurer 做个笔记

    @Autowired
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    // 配置视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver() {
        // new 一个bean
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        // 指定视图放置位置 或者 称谓前缀
        viewResolver.setPrefix("/WEB-INF/views");
        // 页面后缀
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * 把页面指向login.jsp
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    /**
     * 配置拦截器，匹配/r/** 的资源为受保护的系统资源，访问该资源的请求
     * 要进入到SimpleAuthenticationInterceptor拦截器中进行拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(simpleAuthenticationInterceptor);
    }
}
