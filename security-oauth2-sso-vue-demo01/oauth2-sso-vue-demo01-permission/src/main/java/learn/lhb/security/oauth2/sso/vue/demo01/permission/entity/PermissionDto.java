package learn.lhb.security.oauth2.sso.vue.demo01.permission.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 22:10
 */
public class PermissionDto implements Serializable {


    private static final long serialVersionUID = -2096921288062569865L;

    private String id;
    private String code;
    private String description;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
