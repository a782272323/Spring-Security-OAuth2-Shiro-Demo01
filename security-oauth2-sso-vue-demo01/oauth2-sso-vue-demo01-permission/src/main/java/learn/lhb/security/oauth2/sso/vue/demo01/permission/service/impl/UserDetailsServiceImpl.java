package learn.lhb.security.oauth2.sso.vue.demo01.permission.service.impl;

import learn.lhb.security.oauth2.sso.vue.demo01.permission.dao.UserDao;
import learn.lhb.security.oauth2.sso.vue.demo01.permission.entity.UserDto;
import learn.lhb.security.oauth2.sso.vue.demo01.permission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 22:46
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null) {
            return null;
        }
        String[] strings = getPermissionsStringArray(userDto.getId());
        UserDetails userDetails = User.withUsername(userDto.getUsername())
                                        .password(userDto.getPassword())
                                        .authorities(strings)
                                        .build();

        return userDetails;
    }

    /**
     * 将 permissions 转成 String 数组
     * @param userId
     * @return
     */
    private String[] getPermissionsStringArray(String userId) {
        List<String> permissions = userService.findPermissionsByUserId(userId);
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        return permissionArray;
    }

}
