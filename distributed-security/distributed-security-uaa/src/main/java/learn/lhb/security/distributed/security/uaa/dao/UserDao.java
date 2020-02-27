package learn.lhb.security.distributed.security.uaa.dao;

import learn.lhb.security.distributed.security.uaa.model.PermissionDto;
import learn.lhb.security.distributed.security.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/27.
 * @time 10:46
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据账号查询用户信息
    public UserDto getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        //连接数据库查询用户
        List<UserDto> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        if(list !=null && list.size()==1){
//            return null;
            return list.get(0);
        }
        return null;
    }

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(String userId){
        String sql = "SELECT * FROM t_permission WHERE id IN(\n" +
                "\n" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(\n" +
                "  SELECT role_id FROM t_user_role WHERE user_id = ? \n" +
                ")\n" +
                ")\n";

        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
