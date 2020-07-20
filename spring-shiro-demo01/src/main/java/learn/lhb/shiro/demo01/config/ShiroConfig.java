package learn.lhb.shiro.demo01.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description  shiro 相关配置类
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 09:28
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找web工程根目录下的"/login.jsp" 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 游客，开放权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        // 用户，需要角色权限 "user"
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        // 管理员，需要角色权限 "admin"
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        // 开放登录接口
        filterChainDefinitionMap.put("/login", "anon");
        // 其余接口统一拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro 拦截器工厂注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置 realm
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 自定义身份认证类
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     * @return
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }
}
