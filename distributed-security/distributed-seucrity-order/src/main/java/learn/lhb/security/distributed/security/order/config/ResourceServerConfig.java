package learn.lhb.security.distributed.security.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器配置
 *
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 13:09
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    public static final String RESOURCE_ID = "res1";


    @Autowired
    private TokenStore tokenStore;

    /**
     * 不使用jwt令牌的话，就要远程调用认证服务来校验token
     */
//    /**
//     * 资源服务令牌解析
//     * @return
//     */
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        // 使用远程服务请求授权服务器校验token，必须指定校验token的url，client_id,client_secret
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
//        services.setClientId("c1");
//        services.setClientSecret("secret");
//        return services;
//    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                //资源 id
                .resourceId(RESOURCE_ID)
                // 自动校验jwt令牌
                .tokenStore(tokenStore)
//                //验证令牌的服务,全程调用认证服务器来认证
//                .tokenServices(tokenServices())
                .stateless(true);
    }


    /**
     * 安全拦截配置
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 校验令牌的访问范围是不是all
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
                .and().csrf().disable()
                // 会话设置
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
