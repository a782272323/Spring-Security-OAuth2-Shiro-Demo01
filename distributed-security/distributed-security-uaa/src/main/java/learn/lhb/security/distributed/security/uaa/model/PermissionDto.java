package learn.lhb.security.distributed.security.uaa.model;

import java.io.Serializable;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 10:46
 */
public class PermissionDto implements Serializable {
    private static final long serialVersionUID = -851174446974813825L;

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
