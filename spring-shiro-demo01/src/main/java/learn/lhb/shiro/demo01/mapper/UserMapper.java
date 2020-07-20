package learn.lhb.shiro.demo01.mapper;

import org.springframework.stereotype.Repository;

/**
 * @Description
 * @author Herbie Leung
 * @date 2020/7/20
 * @time 09:59
 */
@Repository
public interface UserMapper {


    /**
     * 获得密码
     * @param username 用户名
     */
    String getPassword(String username);

    /**
     * 获得角色权限
     * @param username 用户名
     * @return user/admin
     */
    String getRole(String username);
}
