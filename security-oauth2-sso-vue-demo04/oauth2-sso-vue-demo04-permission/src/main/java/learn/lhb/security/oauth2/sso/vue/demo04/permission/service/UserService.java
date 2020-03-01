package learn.lhb.security.oauth2.sso.vue.demo04.permission.service;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/29.
 * @time 18:38
 */
public interface UserService {

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<String> findPermissionsByUserId(String userId);
}
