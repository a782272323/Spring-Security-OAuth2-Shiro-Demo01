package learn.lhb.security.spring.boot.security.model;

import java.io.Serializable;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 18:25
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = 2221570526353729581L;

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
