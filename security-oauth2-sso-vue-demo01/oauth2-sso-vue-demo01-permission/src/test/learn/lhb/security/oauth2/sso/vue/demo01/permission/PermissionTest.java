package learn.lhb.security.oauth2.sso.vue.demo01.permission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 梁鸿斌
 * @date 2020/2/29.
 * @time 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest("PermissionApplication.class")
public class PermissionTest {


    @Test
    public void secretTest() {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}
