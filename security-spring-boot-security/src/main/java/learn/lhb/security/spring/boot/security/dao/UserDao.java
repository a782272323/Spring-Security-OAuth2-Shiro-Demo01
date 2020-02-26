package learn.lhb.security.spring.boot.security.dao;

import learn.lhb.security.spring.boot.security.model.PermissionDto;
import learn.lhb.security.spring.boot.security.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 18:27
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 从数据库查询名字
     * @param username
     * @return
     */
    public UserDto getUserByUsername(String username) {
        String sql = "select id,username,password,fullname from t_user where username = ?";
        // 连接数据库查询用户
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new
                BeanPropertyRowMapper<>(UserDto.class));
        if (list == null && list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据用户id，查询用户权限
     * @param userId
     * @return
     */
    public List<String> findPermissionByUserId(String userId)   {
        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "\tSELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                ")\n" +
                ")";

        List<PermissionDto> list = jdbcTemplate.query(sql,new Object[]{userId},new
                BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        // 遍历
//        list.iterator().forEachRemaining(c -> permissions.add(c.getCode()));
        // todo 这个用法百度
        // 把数据库查询的结果集的List遍历放到 permissions的List中，
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;


    }
}
