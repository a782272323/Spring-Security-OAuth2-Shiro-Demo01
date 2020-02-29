package learn.lhb.security.oauth2.sso.vue.demo01.permission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 19:06
 */
@Configuration
public class TokenConfig {

    private String SINGING_KEY = "lhb123";

    /**
     * 基于jwt 令牌存储方案
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 使用自定义的密钥来生产令牌
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SINGING_KEY);
        return converter;
    }

//    /**
//     * 内存存储令牌
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }
}
