package learn.lhb.security.oauth2.sso.vue.demo01.permission;

import learn.lhb.security.oauth2.sso.vue.demo01.permission.dao.UserDao;
import learn.lhb.security.oauth2.sso.vue.demo01.permission.entity.UserDto;
import learn.lhb.security.oauth2.sso.vue.demo01.permission.utils.MapperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁鸿斌
 * @date 2020/2/28.
 * @time 23:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest("PermissionApplication.class")
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUserByUsernameTest() {
        UserDto userDto = userDao.getUserByUsername("zhangsan");
//        System.out.println(userDao.getUserByUsername("zhangsan"));
//        try {
//            System.out.println(MapperUtils.obj2json(userDao.getUserByUsername("zhangsan")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println(userDto.getUsername());
    }

    @Test
    public void findPermissionsByUserIdTest() throws Exception  {
//        System.out.println(MapperUtils.obj2json(userDao.findPermissionByUserId("1")));
        List<String> permissions = new ArrayList<>();
        userDao.findPermissionByUserId("1").forEach(c -> permissions.add(c.getCode()));
        System.out.println(permissions);
    }
}
