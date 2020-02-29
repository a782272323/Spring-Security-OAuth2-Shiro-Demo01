package learn.lhb.security.oauth2.sso.vue.demo01.permission.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 19:03
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

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

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    /**
//     * 客户端信息从数据库获取
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public ClientDetailsService clientDetailsService(DataSource dataSource) {
//        ClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
//        ((JdbcClientDetailsService) clientDetailsService).setPasswordEncoder(passwordEncoder());
//        return clientDetailsService;
//    }

//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        // 自定义数据源，指定 HikariCP
//        return DataSourceBuilder.create().build();
//    }
//    @Bean
//    public ClientDetailsService jdbcClientDetailsService() {
//        // 基于jdbc实现，
//        return new JdbcClientDetailsService(dataSource());
//    }

    /**
     * 令牌设置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenService() {
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
        service.setAccessTokenValiditySeconds(2 * 60 * 60);
        // 刷新令牌默认有效期 3天
        service.setRefreshTokenValiditySeconds(3 * 24 * 60 * 60);
        return service;
    }

    /**
     * 授权码存取方式
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {

        /**
         * 数据库中存取
         */
        return new JdbcAuthorizationCodeServices(dataSource);

        /**
         * 内存中 存取
         */
//        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 配置客户端详细信息
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 从数据库获取客户端详细信息
        clients.withClientDetails(clientDetailsService);
//        clients.withClientDetails(jdbcClientDetailsService());



//        /**
//         * 使用内存存储
//         */
//        clients.inMemory()
//                .withClient("c1")
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                .redirectUris("res1")
//                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
//                .scopes("ROLE_ADMIN")
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.con");
    }


    /**
     * 令牌访问端点
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 认证管理器
                .authenticationManager(authenticationManager)
                // 授权码存取
                .authorizationCodeServices(authorizationCodeServices)
                // 令牌管理设置
                .tokenServices(tokenService())
                // 允许post请求访问令牌
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 令牌端点安全约束
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
                .allowFormAuthenticationForClients();
    }
}
