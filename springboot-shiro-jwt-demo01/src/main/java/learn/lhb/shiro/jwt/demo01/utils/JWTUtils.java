package learn.lhb.shiro.jwt.demo01.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description  JWT 工具类
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 14:29
 */
public class JWTUtils {

    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    // 密钥
    private static final String SECRET = "LHB+SHIRO+JWT";

    /**
     * 生成 token 时，指定 token 过期时间 EXPIRE_TIME 和签名密钥 SECRET，
     * 然后将 date 和 username 写入 token 中，并使用带有密钥的 HS256 签名算法进行签名
     *
     * @param username
     * @return
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 生成含有username的jwt
            return JWT.create()
                    //到期时间
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param token
     * @param username
     * @return
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            // 验证token
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取 token 携带的信息
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(token).asString();
        } catch (Exception e) {
            return null;
        }
    }
}
