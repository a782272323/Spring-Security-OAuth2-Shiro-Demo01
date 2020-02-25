package learn.lhb.spring.security.oauth2.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 梁鸿斌
 * @date 2020/2/23.
 * @time 18:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2ApplicationTest {

    @Test
    public void testPasswordEncoder()   {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));;
    }
}
