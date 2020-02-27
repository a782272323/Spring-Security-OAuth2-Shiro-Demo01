package learn.lhb.security.distributed.security.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * 授权服务器配置
 *
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 08:44
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将客户端信息存储到数据库,
     * @param dataSource
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource) {
        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder());
        return clientDetailsService;
    }

    /**
     * 设置授权码模式存取方式
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        System.out.println("45455656565656132323232");
        System.out.println("45455656565656132323232");
        System.out.println("45455656565656132323232");
        System.out.println("45455656565656132323232");
        // 存数据库
        return new JdbcAuthorizationCodeServices(dataSource);
    }
    /**
     * 存在内存
     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices() {
//        System.out.println("23232323232323");
//        System.out.println("23232323232323");
//        System.out.println("23232323232323");
//        System.out.println("23232323232323");
//        System.out.println("23232323232323");
//        // 设置授权码模式的授权码如何存取，这里暂时采用内存的方式存取
//        return new InMemoryAuthorizationCodeServices();
//    }

    /**
     * 配置客户端详情信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // 将客户端信息存储到数据库
        clients.withClientDetails(clientDetailsService);

        /**
         * 基于内存存储客户端详细信息
         */
//        clients
//                // 使用内存存储
//                .inMemory()
//                // 客户端id（名字）
//                .withClient("c1")
//                // 客户端安全码（密钥）
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                // 客户端可以访问的资源列表
//                .resourceIds("res1")
//                // 客户端可以使用的授权类型
//                // authorization_code   授权码模式
//                // password 密码模式
//                // client_credentials 客户端模式
//                // implicit 简化模式
//                // refresh_token 刷新token令牌
//                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
//                // 客户端访问范围，允许的授权范围，写死的
//                .scopes("all")
//                // 是否开启自动认证， false 跳转授权页面手动发令牌
//                .autoApprove(true)
//                // 认证通过后，回调地址
//                .redirectUris("http://www.baidu.com");
    }

    /**
     * 令牌管理配置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices service = new DefaultTokenServices();
        // 客户端详情信息
        service.setClientDetailsService(clientDetailsService);
        // 是否支持刷新令牌
        service.setSupportRefreshToken(true);
        // 令牌存储策略
        service.setTokenStore(tokenStore);
        // 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        // 令牌默认有效期 2小时
        service.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期 3天
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }


    /**
     * 配置令牌访问端点
     * 注意：密码模式，授权码模式，令牌的管理对应的配置在本类上下文找
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 授权码存取方式
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理
                .tokenServices(tokenServices())
                // 端点允许post的请求来访问令牌
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }



    /**
     * 令牌访问端点的安全策略，安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 如果使用jwt令牌，提供公共密钥的端点
                // /oauth/token_key 公开
                .tokenKeyAccess("permitAll()")
                // 检测令牌，这里是不设置拦截
                // /oauth/check_token 公开
                .checkTokenAccess("permitAll()")
                // 允许表单验证（前端），申请令牌
                .allowFormAuthenticationForClients()
                ;
    }

//    /**
//     * 我也不知道干嘛的
//     * @return
//     */
//    @Bean
//    public AuthenticationManager authenticationManager()    {
//        return new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return null;
//            }
//        };
//    }
}
