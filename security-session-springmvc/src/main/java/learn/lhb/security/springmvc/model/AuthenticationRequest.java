package learn.lhb.security.springmvc.model;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 09:33
 */
public class AuthenticationRequest {

    // 认证请求的参数,账号，密码

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
