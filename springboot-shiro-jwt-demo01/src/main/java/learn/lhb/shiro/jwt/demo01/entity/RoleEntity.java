package learn.lhb.shiro.jwt.demo01.entity;

import java.io.Serializable;

public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -90000010L;
    private Long id;
    private String role;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
