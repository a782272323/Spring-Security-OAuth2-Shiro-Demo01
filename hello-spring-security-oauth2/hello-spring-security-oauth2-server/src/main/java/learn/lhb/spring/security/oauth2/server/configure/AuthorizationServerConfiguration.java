package learn.lhb.spring.security.oauth2.server.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 配置认证服务器
 *
 * @author 梁鸿斌
 * @date 2020/2/23.
 * @time 17:32
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 重写一个dataSource，并以这个dataSource为准
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource()  {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }

    /**
     * token 令牌放入数据库中
     * @return
     */
    @Bean
    public TokenStore tokenStore()  {
        // 基于 JDBC 实现，令牌保存到数据库,即token放入数据库中
        return new JdbcTokenStore(dataSource());
    }

    /**
     * // 基于 JDBC 实现，需要事先在数据库配置客户端信息
     * @return
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService()  {
        return new JdbcClientDetailsService(dataSource());
    }

    /**
     * // 设置令牌存储模式
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }

    /**
     * 配置客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端配置
        clients.withClientDetails(jdbcClientDetailsService());

        /**
         * 下面是内存存令牌的配置
         */
//        // 配置客户端
//        clients
//                // 使用内存
//                .inMemory()
//                // client_id
//                .withClient("client")
//                // client_secret 加密，不允许明文
//                .secret(passwordEncoder.encode("secret"))
//                // 授权模式(这里是授权码模式)
//                .authorizedGrantTypes("authorization_code")
//                // 授权范围
//                .scopes("app")
//                // 注册回调地址，一般都是返回调用该认证服务器的地址，这里演示就回调给百度
//                .redirectUris("https://www.baidu.com");
    }
}
