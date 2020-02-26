package learn.lhb.security.springmvc.model;

import java.util.Set;

/**
 * 用户身份信息 实体类
 *
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 09:34
 */
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
    // 用于在session中存放用户信息的key
    public static final String SESSION_USER_KEY = "_user";
    /**
     * 用户权限
     * todo Set用法做个笔记
     */
    private Set<String> authorities;

    public UserDto(String id, String username, String password, String fullname, String mobile,Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mobile = mobile;
        this.authorities = authorities;
    }

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

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
}
