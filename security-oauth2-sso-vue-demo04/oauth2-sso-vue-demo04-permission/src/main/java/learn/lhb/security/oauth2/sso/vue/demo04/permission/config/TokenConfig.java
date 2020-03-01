package learn.lhb.security.oauth2.sso.vue.demo04.permission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 管理令牌配置
 *
 * @author 梁鸿斌
 * @date 2020/2/29.
 * @time 18:27
 */
@Configuration
@EnableResourceServer
public class TokenConfig {
    // 密钥
    private static final String SIGNING_KEY = "lhb123";

    /**
     * 基于JWT令牌存储方案
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对称密钥，资源服务器使用该密钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    /**
     * 基于内存
     * @return
     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
}
