package learn.lhb.security.springmvc.init;

import learn.lhb.security.springmvc.config.ApplicationConfig;
import learn.lhb.security.springmvc.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 用java代码替代xml文件
 * 加载 Spring 容器
 * 在init包下定义Spring容器初始化类SpringApplicationInitializer，
 * 此类实现WebApplicationInitializer接口，
 * Spring容器启动时加载WebApplicationInitializer接口的所有实现类。
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 02:06
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
                ApplicationConfig.class
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
