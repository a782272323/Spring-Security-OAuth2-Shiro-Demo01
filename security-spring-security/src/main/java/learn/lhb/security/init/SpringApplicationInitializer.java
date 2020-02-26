package learn.lhb.security.init;

import learn.lhb.security.config.ApplicationConfig;
import learn.lhb.security.config.WebConfig;
import learn.lhb.security.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载Spring 容器
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 12:37
 */
public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 指定 rootContext的配置类
     * 加载 Spring 容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {
                // 加载spring
                ApplicationConfig.class,
                // Spring Security加载自定义的拦截器类
                WebSecurityConfig.class
        };
    }

    /**
     * 指定 servletContext的配置类
     * 路径的上下文
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {
                WebConfig.class
        };
    }

    /**
     * url-mapper，url的路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }


}
