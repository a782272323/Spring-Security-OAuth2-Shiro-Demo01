package learn.lhb.shiro.jwt.demo01.config;

import org.apache.shiro.authc.AuthenticationToken;
/**
 * @Description  token 配置类
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 14:46
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
