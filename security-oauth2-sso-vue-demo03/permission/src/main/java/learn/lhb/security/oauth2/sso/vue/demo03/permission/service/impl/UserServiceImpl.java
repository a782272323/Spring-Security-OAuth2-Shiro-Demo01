package learn.lhb.security.oauth2.sso.vue.demo03.permission.service.impl;

import learn.lhb.security.oauth2.sso.vue.demo03.permission.dao.UserDao;
import learn.lhb.security.oauth2.sso.vue.demo03.permission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/29.
 * @time 18:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户id查询权限
     * @param userId
     * @return
     */
    @Override
    public List<String> findPermissionsByUserId(String userId) {
        List<String> permissions = new ArrayList<>();
        userDao.findPermissionsByUserId(userId).forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
