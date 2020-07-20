package learn.lhb.shiro.demo01.domain.entity;

import java.io.Serializable;

/**
 * @Description
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 09:49
 */
public class UserEntity implements Serializable {


    private static final long serialVersionUID = -90000094L;
    private Long id;
    private String username;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
