package learn.lhb.security.spring.boot.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 梁鸿斌
 * @date 2020/2/26.
 * @time 17:15
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestBCrypt {

    @Test
    public void testBCrypt()    {
        // 对密码进行加密,右边是加密规则可以自定义,俗称盐。。。
        String hashpw = BCrypt.hashpw("456", BCrypt.gensalt());
        System.out.println(hashpw);

        // 校验密码
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$BpKUP9VpqkGczNRCLDuhYelIUyghUwI7JSDEC8CbkYA3u2w.6tgw2");
        boolean checkpw1 = BCrypt.checkpw("123", "$2a$10$ho5WewjBQOtFPiNYzlDH/OIF.1pC/j719fm3XzJ9qQ5qLZtumwv8C");
        System.out.println("密码第一次是否正确: "+checkpw);
        System.out.println("密码第二次是否正确: "+checkpw1);
    }
}
