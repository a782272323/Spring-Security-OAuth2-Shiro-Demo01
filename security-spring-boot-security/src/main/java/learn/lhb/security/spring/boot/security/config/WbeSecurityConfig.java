package learn.lhb.security.spring.boot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 自定义用户信息安全认证
 * 自定义拦截器策略
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 15:35
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)    // 开启注解
public class WbeSecurityConfig extends WebSecurityConfigurerAdapter {

//    /**
//     * 配置用户信息(查询用户信息），这里暂时从内存查询
//     * @return
//     */
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        // 用户信息（账号，密码，权限），这里是基于内存，而基于JDBC的在后面项目会完善，还在学习稍等
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("ps").build());
//        return  manager;
//    }

    /**
     * 密码编码器,字符串比较
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public PasswordEncoder passwordEncoder()    {
//        return NoOpPasswordEncoder.getInstance();
//    }



    /**
     * 配置安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 使用Spring Security自带到登录页面认证和授权
         */
//        http.authorizeRequests()
//                // 配置授权规则
//                .antMatchers("/r/r1").hasAnyAuthority("p1")
//                .antMatchers("/r/r2").hasAnyAuthority("p2")
//                // 所有/r/**的请求，必须认证通过
//                .antMatchers("/r/**").authenticated()
//                // 除了/r/**的路径请求，其他都可以访问
//                .anyRequest().permitAll()
//                .and()
//                //允许表单登录
//                .formLogin()
//                // 自定义登录成功的页面地址
//                .successForwardUrl("/login-success");

        /**
         * 自定义登录页面来实现认证和授权
         */
        // csrf().disable() 是屏蔽CSRF的控制,若不想屏蔽CSRF，则需要前端携带token来获取登录成功的权限
        http.csrf().disable()
                .authorizeRequests()
                // 配置授权规则
                .antMatchers("/r/r1").hasAnyAuthority("p1")
                .antMatchers("/r/r2").hasAnyAuthority("p2")
                // 所有/r/**的请求，都必须认证且通过
                .antMatchers("/r/**").authenticated()
                // 除了/r/**的路径请求，其他都可以访问,这个一般放在所有被保护的资源的url后面，不然，在它后面的
                // 权限保护是不起作用的
                .anyRequest().permitAll()
                .and()
                // 允许表单登录
                .formLogin()
                    // 自定义登录页面地址
                    .loginPage("/login-view")
                    .loginProcessingUrl("/login")
                    .successForwardUrl("/login-success")
                    .permitAll()
        .and()
                // 配置会话（session）何时创建以及和Spring Security怎么进行交互
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
                // 自定义退出
                .logout()
                // 退出的url
                .logoutUrl("/logout")
                // 退出后重定向到一个地址，一般都重定向到登录页面
                .logoutSuccessUrl("/login-view?logout");
    }

}
