package learn.lhb.security.oauth2.sso.vue.demo01.permission.service;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 22:10
 */
public interface UserService {

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    List<String> findPermissionsByUserId(String userId);
}
