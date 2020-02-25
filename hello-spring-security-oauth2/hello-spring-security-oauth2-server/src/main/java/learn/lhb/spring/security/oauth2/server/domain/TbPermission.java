package learn.lhb.spring.security.oauth2.server.domain;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 梁鸿斌
 * @date 2020/2/23.
 * @time 23:54
 */
public class TbPermission implements Serializable {


    private static final long serialVersionUID = -7315873915081912949L;
    private Long id;
    private Long parent_id;
    private String name;
    private String enname;
    private String url;
    private String description;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
