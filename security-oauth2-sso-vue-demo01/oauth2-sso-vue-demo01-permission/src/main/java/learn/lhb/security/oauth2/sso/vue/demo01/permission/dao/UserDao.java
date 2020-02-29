package learn.lhb.security.oauth2.sso.vue.demo01.permission.dao;

import learn.lhb.security.oauth2.sso.vue.demo01.permission.entity.PermissionDto;
import learn.lhb.security.oauth2.sso.vue.demo01.permission.entity.UserDto;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 22:10
 */
@Repository
public interface UserDao {

    /**
     * 根据账户查询用户信息
     * @param username
     * @return
     */
//    List<UserDto> getUserByUsername(String username);
        UserDto getUserByUsername(String username);

    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    List<PermissionDto> findPermissionByUserId(String userId);

}
