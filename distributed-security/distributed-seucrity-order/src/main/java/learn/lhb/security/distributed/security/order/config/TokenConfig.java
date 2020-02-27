package learn.lhb.security.distributed.security.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 管理令牌配置
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 09:18
 */
@Configuration
public class TokenConfig {

    // 密钥
    private String SIGNING_KEY = "uaa123";

    /**
     * 基于JWT令牌存储方案
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
        // 对称密钥，资源服务器使用该密钥来验证
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }



}
