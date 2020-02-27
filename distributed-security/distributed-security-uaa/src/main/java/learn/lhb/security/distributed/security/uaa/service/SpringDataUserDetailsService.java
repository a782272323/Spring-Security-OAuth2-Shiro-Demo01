package learn.lhb.security.distributed.security.uaa.service;

import learn.lhb.security.distributed.security.uaa.dao.UserDao;
import learn.lhb.security.distributed.security.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 11:15
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 连接数据库查询用户信息
        UserDto userDto = userDao.getUserByUsername(username);

        if(userDto == null) {
            // 如果用户为空(查不到数据)，返回null，异常抛出交给Spring Security框架的provider来抛出
            return null;
        }
        // 获取用户权限 String 数组
        String[] strings = getPermissionStringArray(userDto.getId());
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(strings).build();
        return userDetails;


        /**
         * 写死的模拟方式
         */
//        // 暂时采用模拟方式，后面要连接数据库去查询用户信息
//        System.out.println("username = "+username);
//        UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$ho5WewjBQOtFPiNYzlDH/OIF.1pC/j719fm3XzJ9qQ5qLZtumwv8C").authorities("p1").build();
//
//        return userDetails;
    }

    /**
     * 根据用户的id 查询用户的权限
     * 将permissions 转成数组
     * @param userId
     * @return
     */
    public String[] getPermissionStringArray(String userId)  {
        // 根据用户的id 查询用户的权限
        List<String> permissions = userDao.findPermissionsByUserId(userId);
        // 将permissions 转成数组, todo 这个用法做个笔记到 集合那里
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        return permissionArray;
    }



}
