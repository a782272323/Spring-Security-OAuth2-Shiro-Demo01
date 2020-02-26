package learn.lhb.security.config;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 用户信息安全认证
 * 自定义拦截器策略
 *
 * todo 把这个项目涉及到的注解补个笔记
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 13:38
 */
@EnableWebSecurity // 自定义拦截机制
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置用户信息(查询用户信息），这里暂时从内存查询
     * @return
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // 用户信息（账号，密码，权限），这里是基于内存，而基于JDBC的在后面项目会完善，还在学习稍等
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("ps").build());
        return  manager;
    }

    /**
     * 密码编码器,字符串比较
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder()    {
        return NoOpPasswordEncoder.getInstance();
    }



    /**
     * 配置安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 配置授权规则
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/r/r2").hasAnyAuthority("p2")
                // 所有/r/**的请求，必须认证通过
                .antMatchers("/r/**").authenticated()
                // 除了/r/**的路径请求，其他都可以访问
                .anyRequest().permitAll()
                .and()
                //允许表单登录
                .formLogin()
                // 自定义登录成功的页面地址
                .successForwardUrl("/login-success");
    }
}
